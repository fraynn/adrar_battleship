package adrar.battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import adrar.battleship.interfaces.StrikeDecisionMakerInterface;

public class StrikeDecisionMaker implements StrikeDecisionMakerInterface {
	public static final int GRID_SIZE = 10;

	// This number of targets has an effect on the number of strikes needed to win
	// the game
	// Test it to find the best average
	private static final int TARGETS_UNTIL_NON_RANDOM_MODE = 10;

	private final Random RANDOM;

	private PlayerStrikesHistory strikesHistory;
	private List<Square> availableTargetsList;
	private Coordinate currentStrikeAttempt;
	private Direction currentlyTargetedShipDirection;
//	Not used right now, see TODO
	private List<Integer> shipsSunkSizes;

	// Constructor
	public StrikeDecisionMaker() {
		RANDOM = new Random();
		currentlyTargetedShipDirection = Direction.Unknown;
		strikesHistory = new PlayerStrikesHistory();
		availableTargetsList = new ArrayList<>();
		populateAvailableTargetList();
		// Not used right now, see TODO
		shipsSunkSizes = new ArrayList<>();
	}

	// TODO :
//	- use shipsSunkSizes list to filter available targets list

	// Methods

	// Two different targetting modes, depending on whether a ship was found or not
	@Override
	public Coordinate targetSquare() {
		boolean aShipHasBeenHit = strikesHistory.getHitStrikesList().size() > 0;
		if (aShipHasBeenHit) {
			return targetAroundHitSquare();
		} else {
			return targetRandomSquare();
		}
	}

	@Override
	public void updateListsBasedOnStrikeResult(SquareStatus strikeResult) {
		Square targetedSquare = new Square(currentStrikeAttempt.getY(), currentStrikeAttempt.getX());

		strikesHistory.addStrikeToHistory(currentStrikeAttempt);

		if (strikeResult.equals(SquareStatus.Miss)) {
			strikesHistory.addStrikeToMissedList(targetedSquare);
			removeTargetFromAvailableTargets(currentStrikeAttempt);
		} else if (strikeResult.equals(SquareStatus.Hit) || strikeResult.equals(SquareStatus.Sunk)) {
			strikesHistory.addStrikeToHitList(targetedSquare);
			removeTargetAndDiagonalSquaresFromAvailableTargets(currentStrikeAttempt);
			currentlyTargetedShipDirection = strikesHistory.determineTargetedShipDirection();
		}
		if (strikeResult.equals(SquareStatus.Sunk)) {
			removeRemainingAdjacentTargets();
			clearHitListAndLogSunkShipSize();
			currentlyTargetedShipDirection = Direction.Unknown;
		}
	}

	private Coordinate targetRandomSquare() {
		List<Square> bestPossibleTargetSquares = findDiagonalSquaresFromMissedTargets();
		boolean firstStrikeAttempt = strikesHistory.getMissedStrikesList().size() == 0;
		boolean diagonalTargetPoolTooSmall = bestPossibleTargetSquares.size() < TARGETS_UNTIL_NON_RANDOM_MODE;

		// Avoid aiming at two adjacent coordinates, as the minimum ship size is 2
		if (firstStrikeAttempt || diagonalTargetPoolTooSmall) {
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

	private List<Square> findDiagonalSquaresFromMissedTargets() {
		List<Square> missedSquares = strikesHistory.getMissedStrikesList();
		List<Square> diagonalFromMissedSquaresList = new ArrayList<>();
		for (Square square : missedSquares) {
			diagonalFromMissedSquaresList.addAll(square.getDiagonalSquaresList());
		}
		diagonalFromMissedSquaresList.retainAll(availableTargetsList);
		return diagonalFromMissedSquaresList;
	}

	private List<Square> findAdjacentTargets() {
		List<Square> potentialTargets = new ArrayList<>();

		potentialTargets.addAll(findAdjacentHorizontalTargets());
		potentialTargets.addAll(findAdjacentVerticalTargets());

		return potentialTargets;
	}

	private List<Square> findAdjacentHorizontalTargets() {
		List<Square> potentialTargets = strikesHistory.findAdjacentHorizontalTargets();
		potentialTargets.removeIf(square -> !availableTargetsList.contains(square));

		return potentialTargets;
	}

	private List<Square> findAdjacentVerticalTargets() {
		List<Square> potentialTargets = strikesHistory.findAdjacentVerticalTargets();
		potentialTargets.removeIf(square -> !availableTargetsList.contains(square));

		return potentialTargets;
	}

	private void populateAvailableTargetList() {
		for (char i = 65; i < GRID_SIZE + 65; i++) {
			for (int j = 1; j < GRID_SIZE + 1; j++) {
				Square square = new Square(i, j);
				availableTargetsList.add(square);
			}
		}
	}

	private void clearHitListAndLogSunkShipSize() {
//		shipSize not currently used, see TODO
		int shipSize = strikesHistory.getHitStrikesList().size();
		shipsSunkSizes.add(shipSize);
		strikesHistory.clearHitList();
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

	// Get + Set
	public Coordinate getCurrentStrikeAttempt() {
		return currentStrikeAttempt;
	}

	public Direction getCurrentlyTargetedShipDirection() {
		return currentlyTargetedShipDirection;
	}
}
