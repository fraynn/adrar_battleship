package adrar.battleship;

import java.util.ArrayList;
import java.util.List;

import adrar.battleship.interfaces.PlayerStrikesHistoryInterface;

public class PlayerStrikesHistory implements PlayerStrikesHistoryInterface {
	private List<Square> strikesHistory;

	public PlayerStrikesHistory() {
		strikesHistory = new ArrayList<>();
	}

	@Override
	public void addStrike(Square square) {
		if (checkIfStrikeExists(square) == true) {
			strikesHistory.add(square);
		}
	}

	@Override
	public boolean checkIfStrikeExists(Square square) {
		if (square.getStatus() != SquareStatus.valueOf(null)) {
			return true;

		} else {
			System.out.println("you already targeted that square");
			return false;
		}
	}

	// Get + Set
	public List<Square> getStrikesHistory() {
		return strikesHistory;
	}

	public void setStrikesHistory(List<Square> strikesHistory) {
		this.strikesHistory = strikesHistory;
	}

}
