package adrar.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import adrar.battleship.interfaces.StrikeDecisionMakerInterface;

public class StrikeDecisionMaker implements StrikeDecisionMakerInterface {
	public static final int GRID_SIZE = 10;
	private static final Random RANDOM = new Random();

	private PlayerStrikesHistory strikesHistory;
	private List<Square> availableTargetsList;
	private Coordinate currentStrikeAttempt;
	private Direction currentlyTargetedShipDirection;
	private List<Integer> shipsSunkSizes;

	public StrikeDecisionMaker() {
		strikesHistory = new PlayerStrikesHistory();
		availableTargetsList = new ArrayList<>();
		currentStrikeAttempt = new Coordinate('B', 2);
		currentlyTargetedShipDirection = Direction.Unknown;
		shipsSunkSizes = new ArrayList<>();
	}

	// Methods
	@Override
	public Coordinate targetSquare() {
		if (getHitSquaresList().size() > 0) {
			return targetAroundHitSquare();
		} else {
			return targetRandomSquare();
		}
	}

	// TODO :
//	- general strategy otherwise
//	- update currentStrikeAttempt
//	- privatise methods set to public for testing
//	- comment code and reason for methods existence
//	- clean unneeded setters & getters
//	- improve readability of if/else logic blocks
//	- check the need for a status on squares

	private Coordinate targetRandomSquare() {
		return null;
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

	public ArrayList<Square> findAdjacentTargets() {
		ArrayList<Square> potentialTargets = new ArrayList<>();

		potentialTargets.addAll(findAdjacentHorizontalTargets());
		potentialTargets.addAll(findAdjacentVerticalTargets());

		return potentialTargets;
	}

	public ArrayList<Square> findAdjacentHorizontalTargets() {
		ArrayList<Square> potentialTargets = new ArrayList<>();
		for (Square square : getHitSquaresList()) {
			potentialTargets.add(new Square(square.getLeftSquare().getY(), square.getLeftSquare().getX()));
			potentialTargets.add(new Square(square.getRightSquare().getY(), square.getRightSquare().getX()));
		}
		potentialTargets.removeIf(square -> !availableTargetsList.contains(square));

		return potentialTargets;
	}

	public ArrayList<Square> findAdjacentVerticalTargets() {
		ArrayList<Square> potentialTargets = new ArrayList<>();
		for (Square square : getHitSquaresList()) {
			potentialTargets.add(new Square(square.getTopSquare().getY(), square.getTopSquare().getX()));
			potentialTargets.add(new Square(square.getBottomSquare().getY(), square.getBottomSquare().getX()));
		}
		potentialTargets.removeIf(square -> !availableTargetsList.contains(square));

		return potentialTargets;
	}

	public void populatePossibleTargetList() {
		for (char i = 65; i < GRID_SIZE + 65; i++) {
			for (int j = 1; j < GRID_SIZE + 1; j++) {
				Square square = new Square(i, j);
				availableTargetsList.add(square);
			}
		}
	}

	public void updateListsBasedOnStrikeResult(SquareStatus result) {
		strikesHistory.addStrikeToHistory(currentStrikeAttempt);
		if (result.equals(SquareStatus.Miss)) {
			removeTargetFromAvailableTargets(currentStrikeAttempt);
		} else if (result.equals(SquareStatus.Hit) || result.equals(SquareStatus.Sunk)) {
			strikesHistory.addStrikeToHitList(new Square(currentStrikeAttempt.getY(), currentStrikeAttempt.getX()));
			removeTargetAndDiagonalSquaresFromAvailableTargets(currentStrikeAttempt);
			determineTargetedShipDirection();
		}
		if (result.equals(SquareStatus.Sunk)) {
			removeRemainingAdjacentTargets();
			clearHitListAndLogSunkShipSize();
			setCurrentlyTargetedShipDirection(Direction.Unknown);
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

	public List<Square> getHitSquaresList() {
		return strikesHistory.getHitStrikes();
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
