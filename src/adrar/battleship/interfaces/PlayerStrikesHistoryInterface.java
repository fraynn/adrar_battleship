package adrar.battleship.interfaces;

import adrar.battleship.Square;

public interface PlayerStrikesHistoryInterface {
	public void addStrike(Square square);

	public boolean checkIfStrikeExists(Square square);
}
