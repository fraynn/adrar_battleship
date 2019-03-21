package adrar.battleship.interfaces;

import adrar.battleship.Player;

public interface GameControllerInterface {
	public Player defineWinner();

	public void changeCurrentPlayer();
}
