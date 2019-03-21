package adrar.battleship.interfaces;

import adrar.battleship.Square;
import adrar.battleship.SquareStatus;

public interface PlayerInterface {
	public SquareStatus receiveAttack(Square square);

	public Square attack();

	public boolean hasShipsLeft();
}
