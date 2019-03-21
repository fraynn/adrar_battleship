package adrar.battleship;

import java.util.List;

import adrar.battleship.interfaces.PlayerStrikesHistoryInterface;

public class PlayerStrikesHistory implements PlayerStrikesHistoryInterface {
	private List<Square> strikesHistory;

	@Override
	public void addStrike(Square square) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkIfStrikeExists(Square square) {
		// TODO Auto-generated method stub
		return false;
	}

	// Get + Set
	public List<Square> getStrikesHistory() {
		return strikesHistory;
	}

	public void setStrikesHistory(List<Square> strikesHistory) {
		this.strikesHistory = strikesHistory;
	}

}
