package adrar.battleship;

import java.util.ArrayList;
import java.util.List;

import adrar.battleship.interfaces.PlayerStrikesHistoryInterface;

public class PlayerStrikesHistory implements PlayerStrikesHistoryInterface {
	// Full strike history
	private List<Coordinate> strikesHistory;
	// Squares where a ship was Hit
	// Cleared when the ship is Sunk
	private List<Square> hitStrikesList;
	// Squares with a strike result equals to Missed
	private List<Square> missedStrikesList;

	// Constructor
	public PlayerStrikesHistory() {
		strikesHistory = new ArrayList<>();
		hitStrikesList = new ArrayList<>();
		missedStrikesList = new ArrayList<>();
	}

	// Methods
	@Override
	public void addStrikeToHistory(Coordinate coordinate) {
		if (checkIfStrikeExists(coordinate) == false) {
			strikesHistory.add(coordinate);
		} else {
			System.out.println("you already targeted that square");
		}
	}

	@Override
	public boolean checkIfStrikeExists(Coordinate coordinate) {
		boolean exist = false;
		for (Coordinate currentCoordinate : strikesHistory) {
			if (currentCoordinate.equals(coordinate)) {
				exist = true;
				return exist;
			}
		}
		return exist;
	}

	public List<Square> findAdjacentHorizontalTargets() {
		List<Square> potentialTargets = new ArrayList<>();
		for (Square square : hitStrikesList) {
			potentialTargets.add(new Square(square.getLeftSquare().getY(), square.getLeftSquare().getX()));
			potentialTargets.add(new Square(square.getRightSquare().getY(), square.getRightSquare().getX()));
		}
		return potentialTargets;
	}

	public List<Square> findAdjacentVerticalTargets() {
		List<Square> potentialTargets = new ArrayList<>();
		for (Square square : hitStrikesList) {
			potentialTargets.add(new Square(square.getTopSquare().getY(), square.getTopSquare().getX()));
			potentialTargets.add(new Square(square.getBottomSquare().getY(), square.getBottomSquare().getX()));
		}
		return potentialTargets;
	}

	public Direction determineTargetedShipDirection() {
		Direction shipDirection;
		if (hitStrikesList.size() < 2) {
			shipDirection = Direction.Unknown;
		} else if (hitStrikesList.get(0).getY() == hitStrikesList.get(1).getY()) {
			shipDirection = Direction.Horizontal;
		} else {
			shipDirection = Direction.Vertical;
		}
		return shipDirection;
	}

	public void addStrikeToHitList(Square square) {
		hitStrikesList.add(square);
	}

	public void addStrikeToMissedList(Square square) {
		missedStrikesList.add(square);
	}

	public void clearHitList() {
		hitStrikesList.clear();
	}

	// Get + Set
	public List<Coordinate> getStrikesHistory() {
		return strikesHistory;
	}

	public void setStrikesHistory(List<Coordinate> strikesHistory) {
		this.strikesHistory = strikesHistory;
	}

	public List<Square> getHitStrikesList() {
		return hitStrikesList;
	}

	public void setHitStrikesList(List<Square> hitStrikes) {
		hitStrikesList = hitStrikes;
	}

	public List<Square> getMissedStrikesList() {
		return missedStrikesList;
	}

	public void setMissedStrikesList(List<Square> missedStrikesList) {
		this.missedStrikesList = missedStrikesList;
	}

}
