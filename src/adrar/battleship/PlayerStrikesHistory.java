package adrar.battleship;

import java.util.ArrayList;
import java.util.List;

import adrar.battleship.interfaces.PlayerStrikesHistoryInterface;

public class PlayerStrikesHistory implements PlayerStrikesHistoryInterface {
	private List<Coordinate> strikesHistory;
	private List<Square> hitStrikes;

	public PlayerStrikesHistory() {
		strikesHistory = new ArrayList<>();
		hitStrikes = new ArrayList<>();
	}

	public void addStrikeToHitList(Square square) {
		hitStrikes.add(square);
	}

	public void clearHitList() {
		hitStrikes.clear();
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

	public List<Square> getHitStrikes() {
		return hitStrikes;
	}

	public void setHitStrikes(List<Square> hitStrikes) {
		this.hitStrikes = hitStrikes;
	}

}
