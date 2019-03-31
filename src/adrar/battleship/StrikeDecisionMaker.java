package adrar.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import adrar.battleship.interfaces.StrikeDecisionMakerInterface;

public class StrikeDecisionMaker implements StrikeDecisionMakerInterface {
	public static final int GRID_SIZE = 10;
	private final Random RANDOM;

	private PlayerStrikesHistory strikesHistory;
	private List<Square> availableTargetsList;
	private Coordinate currentStrikeAttempt;
	private Direction currentlyTargetedShipDirection;
	private List<Integer> shipsSunkSizes;

	public StrikeDecisionMaker() {
		RANDOM = new Random();
		strikesHistory = new PlayerStrikesHistory();
		availableTargetsList = new ArrayList<>();
		currentStrikeAttempt = new Coordinate('C', 3);
		currentlyTargetedShipDirection = Direction.Unknown;
		shipsSunkSizes = new ArrayList<>();
		populatePossibleTargetList();
	}

	// TODO :
//	- privatise methods set to public for testing
//	- comment code and reason for methods existence
//	- clean unneeded setters & getters
//	- improve readability of if/else logic blocks

	// Methods
	@Override
	public Coordinate targetSquare() {
		boolean aShipHasBeenHit = getHitSquaresList().size() > 0;
		if (aShipHasBeenHit) {
			return targetAroundHitSquare();
		} else {
			return targetRandomSquare();
		}
	}

	@Override
	public void updateListsBasedOnStrikeResult(SquareStatus result) {
		Square targetedSquare = new Square(currentStrikeAttempt.getY(), currentStrikeAttempt.getX());

		strikesHistory.addStrikeToHistory(currentStrikeAttempt);

		if (result.equals(SquareStatus.Miss)) {
			strikesHistory.addStrikeToMissedList(targetedSquare);
			removeTargetFromAvailableTargets(currentStrikeAttempt);
		} else if (result.equals(SquareStatus.Hit) || result.equals(SquareStatus.Sunk)) {
			strikesHistory.addStrikeToHitList(targetedSquare);
			removeTargetAndDiagonalSquaresFromAvailableTargets(currentStrikeAttempt);
			determineTargetedShipDirection();
		}
		if (result.equals(SquareStatus.Sunk)) {
			removeRemainingAdjacentTargets();
			clearHitListAndLogSunkShipSize();
			setCurrentlyTargetedShipDirection(Direction.Unknown);
		}
	}

	private Coordinate targetRandomSquare() {
		boolean firstStrikeAttempt = strikesHistory.getMissedStrikesList().size() == 0;
		List<Square> bestPossibleTargetSquares = findDiagonalSquaresFromMissedTargets();

		if (firstStrikeAttempt || bestPossibleTargetSquares.size() < 10) {
			currentStrikeAttempt = randomTarget(availableTargetsList);
		} else {
			currentStrikeAttempt = randomTarget(bestPossibleTargetSquares);
		}
		return currentStrikeAttempt;
	}

	private Coordinate targetAroundHitSquare() {
		switch (currentlyTargetedShipDirection) {

		case Vertical:
			currentStrikeAttempt = randomTarget(findAdjacentVerticalTargets());
			break;

		case Horizontal:
			currentStrikeAttempt = randomTarget(findAdjacentHorizontalTargets());
			break;

		case Unknown:
			currentStrikeAttempt = randomTarget(findAdjacentTargets());
			break;
		}

		return currentStrikeAttempt;
	}

	private Coordinate randomTarget(List<Square> squareList) {
		int randomIndex = RANDOM.nextInt(squareList.size());
		return squareList.get(randomIndex).getCoordinates();
	}

	public List<Square> findDiagonalSquaresFromMissedTargets() {
		List<Square> missedSquares = strikesHistory.getMissedStrikesList();
		List<Square> diagonalFromMissedSquaresList = new ArrayList<>();
		for (Square square : missedSquares) {
			diagonalFromMissedSquaresList.addAll(square.getDiagonalSquaresList());
		}
		diagonalFromMissedSquaresList.retainAll(availableTargetsList);
		return diagonalFromMissedSquaresList;
	}

	public List<Square> findAdjacentTargets() {
		List<Square> potentialTargets = new ArrayList<>();

		potentialTargets.addAll(findAdjacentHorizontalTargets());
		potentialTargets.addAll(findAdjacentVerticalTargets());

		return potentialTargets;
	}

	public List<Square> findAdjacentHorizontalTargets() {
		List<Square> potentialTargets = new ArrayList<>();
		for (Square square : getHitSquaresList()) {
			potentialTargets.add(new Square(square.getLeftSquare().getY(), square.getLeftSquare().getX()));
			potentialTargets.add(new Square(square.getRightSquare().getY(), square.getRightSquare().getX()));
		}
		potentialTargets.removeIf(square -> !availableTargetsList.contains(square));

		return potentialTargets;
	}

	public List<Square> findAdjacentVerticalTargets() {
		List<Square> potentialTargets = new ArrayList<>();
		for (Square square : getHitSquaresList()) {
			potentialTargets.add(new Square(square.getTopSquare().getY(), square.getTopSquare().getX()));
			potentialTargets.add(new Square(square.getBottomSquare().getY(), square.getBottomSquare().getX()));
		}
		potentialTargets.removeIf(square -> !availableTargetsList.contains(square));

		return potentialTargets;
	}

	private void populatePossibleTargetList() {
		for (char i = 65; i < GRID_SIZE + 65; i++) {
			for (int j = 1; j < GRID_SIZE + 1; j++) {
				Square square = new Square(i, j);
				availableTargetsList.add(square);
			}
		}
	}

	private void clearHitListAndLogSunkShipSize() {
		int shipSize = getHitSquaresList().size();
		strikesHistory.clearHitList();
		shipsSunkSizes.add(shipSize);
	}

	private void removeTargetAndDiagonalSquaresFromAvailableTargets(Coordinate target) {
		availableTargetsList
				.removeIf(square -> square.isDiagonalCoordinate(target) || square.getCoordinates().equals(target));
	}

	private void removeTargetFromAvailableTargets(Coordinate target) {
		availableTargetsList.removeIf(square -> square.getCoordinates().equals(target));
	}

	private void removeRemainingAdjacentTargets() {
		List<Square> remainingTargets = new ArrayList<>();
		if (currentlyTargetedShipDirection.equals(Direction.Unknown)) {
			return;
		} else if (currentlyTargetedShipDirection.equals(Direction.Vertical)) {
			remainingTargets.addAll(findAdjacentVerticalTargets());
		} else {
			remainingTargets.addAll(findAdjacentHorizontalTargets());
		}
		availableTargetsList.removeAll(remainingTargets);
	}

	private void determineTargetedShipDirection() {
		if (getHitSquaresList().size() < 2) {
			return;
		} else if (getHitSquaresList().get(0).getY() == getHitSquaresList().get(1).getY()) {
			currentlyTargetedShipDirection = Direction.Horizontal;
		} else {
			currentlyTargetedShipDirection = Direction.Vertical;
		}
	}

	// Get + Set
	public List<Coordinate> getStrikesHistory() {
		return strikesHistory.getStrikesHistory();
	}

	public List<Square> getMissedSquaresList() {
		return strikesHistory.getMissedStrikesList();
	}

	public List<Square> getHitSquaresList() {
		return strikesHistory.getHitStrikesList();
	}

	public Coordinate getCurrentStrikeAttempt() {
		return currentStrikeAttempt;
	}

	public void setCurrentStrikeAttempt(Coordinate currentStrikeAttempt) {
		this.currentStrikeAttempt = currentStrikeAttempt;
	}

	public List<Square> getAvailableTargetsList() {
		return availableTargetsList;
	}

	public List<Integer> getShipsSunkSizes() {
		return shipsSunkSizes;
	}

	public Direction getCurrentlyTargetedShipDirection() {
		return currentlyTargetedShipDirection;
	}

	public void setCurrentlyTargetedShipDirection(Direction currentlyTargetedShipDirection) {
		this.currentlyTargetedShipDirection = currentlyTargetedShipDirection;
	}

}
