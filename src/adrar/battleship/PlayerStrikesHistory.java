package adrar.battleship;

import java.util.ArrayList;
import java.util.List;

import adrar.battleship.interfaces.PlayerStrikesHistoryInterface;

public class PlayerStrikesHistory implements PlayerStrikesHistoryInterface {
	private List<Coordinate> strikesHistory;
	private List<Square> hitStrikesList;
	private List<Square> missedStrikesList;

	public PlayerStrikesHistory() {
		strikesHistory = new ArrayList<>();
		hitStrikesList = new ArrayList<>();
		missedStrikesList = new ArrayList<>();
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
		this.hitStrikesList = hitStrikes;
	}

	public List<Square> getMissedStrikesList() {
		return missedStrikesList;
	}

	public void setMissedStrikesList(List<Square> missedStrikesList) {
		this.missedStrikesList = missedStrikesList;
	}

}
