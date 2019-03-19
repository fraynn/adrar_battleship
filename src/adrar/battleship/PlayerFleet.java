package adrar.battleship;

import java.util.LinkedList;
import java.util.List;

public class PlayerFleet {
	private List<Ship> shipList;

	// Constructor
	public PlayerFleet() {
		shipList = new LinkedList<>();
	}

	// Methods
	public void addShip(Ship ship) {
		if (hasCorrectCoordinates(ship)) {
			shipList.add(ship);
		}
	}

	private boolean hasCorrectCoordinates(Ship ship) {
		return hasCorrectNumberOfCoordinates(ship) && hasConsecutiveCoordinates(ship);
	}

	private boolean hasCorrectNumberOfCoordinates(Ship ship) {
		return ship.getShipCoordinates().size() == ship.getSize();
	}

	private boolean hasConsecutiveCoordinates(Ship ship) {
		return false;
		// TODO
	}

	private boolean areHorizontalConsecutive(Coordinates a, Coordinates b) {
		return a.toString().charAt(0) == b.toString().charAt(0);
	}

	private boolean areVerticalConsecutive(Coordinates a, Coordinates b) {
		return a.toString().charAt(1) == b.toString().charAt(1);
	}

	// Get + Set
	public List<Ship> getShipList() {
		return shipList;
	}

	public void setShipList(List<Ship> shipList) {
		this.shipList = shipList;
	}
}
