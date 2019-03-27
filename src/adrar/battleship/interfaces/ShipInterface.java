package adrar.battleship.interfaces;

import adrar.battleship.Square;
import adrar.battleship.SquareStatus;

public interface ShipInterface {
	public SquareStatus updateHitSquareStatus(Square square);
}
