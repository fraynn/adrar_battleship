package adrar.battleship;

import java.util.ArrayList;
import java.util.List;

import adrar.battleship.interfaces.StrikeDecisionMakerInterface;

public class StrikeDecisionMaker implements StrikeDecisionMakerInterface {
	public static final int GRID_SIZE = 10;

	private PlayerStrikesHistory strikesHistory;
	private List<Square> availableTargetsList;
	private Coordinate currentStrikeAttempt;
	private Direction currentlyTargetedShipDirection;

	public StrikeDecisionMaker() {
		strikesHistory = new PlayerStrikesHistory();
		availableTargetsList = new ArrayList<>();
		currentStrikeAttempt = new Coordinate('B', 2);
		currentlyTargetedShipDirection = null;
	}

	// Methods
	@Override
	public Square targetSquare() {
		if (getHitSquaresList().size() > 0) {
			return targetAroundHitSquare();
		} else {
			return targetRandomSquare();
		}
	}

	// TODO :
//	- clear hit list if sunk
//	- strike strategy if 1 square hit
//	- strike strategy when ship direction is known
//	- general strategy otherwise
//	- update currentStrikeAttempt
//	- remove strikes all around the hit ship from availableTargets

	private Square targetRandomSquare() {
		return null;
	}

	private Square targetAroundHitSquare() {

		return null;
	}

	public ArrayList<Square> targetAdjacentVerticalSquare() {
		ArrayList<Square> potentialTargets = new ArrayList<>();
		for (Square square : getHitSquaresList()) {
			potentialTargets.add(new Square(square.getLeftSquare().getY(), square.getLeftSquare().getX()));
			potentialTargets.add(new Square(square.getRightSquare().getY(), square.getRightSquare().getX()));
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
		if (result.equals(SquareStatus.Miss)) {
			strikesHistory.addStrikeToHistory(currentStrikeAttempt);
			removeTargetFromAvailableTargets(currentStrikeAttempt);
		} else if (result.equals(SquareStatus.Hit)) {
			strikesHistory.addStrikeToHitList(new Square(currentStrikeAttempt.getY(), currentStrikeAttempt.getX()));
			removeTargetAndDiagonalSquaresFromAvailableTargets(currentStrikeAttempt);
		} else if (result.equals(SquareStatus.Sunk)) {
			removeTargetAndDiagonalSquaresFromAvailableTargets(currentStrikeAttempt);
		}
	}

	private void removeTargetAndDiagonalSquaresFromAvailableTargets(Coordinate target) {
		availableTargetsList
				.removeIf(square -> square.isDiagonalCoordinate(target) || square.getCoordinates().equals(target));
	}

	private void removeTargetFromAvailableTargets(Coordinate target) {
		availableTargetsList.removeIf(square -> square.getCoordinates().equals(target));
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

}
