package adrar.battleship.interfaces;

import adrar.battleship.Coordinate;
import adrar.battleship.SquareStatus;

public interface StrikeDecisionMakerInterface {
	public Coordinate targetSquare();

	public void updateListsBasedOnStrikeResult(SquareStatus status);
}
