package adrar.battleship;

import java.util.LinkedList;
import java.util.List;

import adrar.battleship.interfaces.PlayerFleetInterface;

public class PlayerFleet implements PlayerFleetInterface {
	private List<Ship> shipList;

	// Constructor
	public PlayerFleet() {
		shipList = new LinkedList<>();
	}

	// Methods

	@Override
	public Ship createShip(List<Square> coordinatesList) {
		Ship ship = shipList.get(0);
		return ship;
	}

	@Override
	public void addShipToFleet(Ship ship) {
		if (hasCorrectCoordinates(ship)) {
			shipList.add(ship);
		}
	}

	@Override
	public boolean checkShipCoordinates(List<Square> coordinatesList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Square> generateShipCoordinates(int shipSize) {
		// TODO Auto-generated method stub
		return null;
	}

	// Check if necessary
	private boolean hasCorrectCoordinates(Ship ship) {
		return hasCorrectNumberOfCoordinates(ship) && hasConsecutiveCoordinates(ship);
	}

	private boolean hasCorrectNumberOfCoordinates(Ship ship) {
		return ship.getShipCoordinates().size() == ship.getShipType().getValue();
	}

	private boolean hasConsecutiveCoordinates(Ship ship) {
		return areHorizontalConsecutive(ship.getShipCoordinates().get(0),
				ship.getShipCoordinates().get(ship.getShipCoordinates().size()))
				|| areVerticalConsecutive(ship.getShipCoordinates().get(0),
						ship.getShipCoordinates().get(ship.getShipCoordinates().size()));
	}

	private boolean areHorizontalConsecutive(Square a, Square b) {
		return a.getX() == b.getX();
	}

	private boolean areVerticalConsecutive(Square a, Square b) {
		return a.getY() == b.getY();
	}

	// Get + Set
	public List<Ship> getShipList() {
		return shipList;
	}

	public void setShipList(List<Ship> shipList) {
		this.shipList = shipList;
	}

}
