package adrar.battleship.interfaces;

import java.util.List;

import adrar.battleship.Ship;
import adrar.battleship.ShipType;
import adrar.battleship.Square;

public interface PlayerFleetInterface {
	public Ship createShip(List<Square> coordinatesList, ShipType type);

	public void addShipToFleet(Ship ship);

	public boolean checkShipCoordinates(List<Square> coordinatesList);

	public List<Square> generateShipCoordinates(int shipSize);
}
