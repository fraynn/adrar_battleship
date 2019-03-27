package adrar.battleship.interfaces;

import adrar.battleship.Coordinate;

public interface PlayerStrikesHistoryInterface {
	public void addStrikeToHistory(Coordinate coordinate);

	public boolean checkIfStrikeExists(Coordinate coordinate);
}
