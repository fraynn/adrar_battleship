package adrar.battleship;

import java.util.List;

import adrar.battleship.interfaces.ShipInterface;

public class Ship implements ShipInterface {
	private ShipType shipType;
	private List<Square> shipCoordinates;
	private boolean sunk;

	public Ship(ShipType shipType) {
		this.shipType = shipType;
		sunk = false;
	}

	// Methods
	@Override
	public SquareStatus isHit(Square square) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateShipStatus() {
		// TODO Auto-generated method stub
		return false;
	}

	// Get + Set
	public List<Square> getShipCoordinates() {
		return shipCoordinates;
	}

	public void setShipCoordinates(List<Square> shipCoordinates) {
		this.shipCoordinates = shipCoordinates;
	}

	public boolean isSunk() {
		return sunk;
	}

	public void setSunk(boolean sunk) {
		this.sunk = sunk;
	}

	public ShipType getShipType() {
		return shipType;
	}

	@Override
	public String toString() {
		return "Ship [shipType=" + shipType + ", size=" + getShipType().getValue() + ", shipCoordinates="
				+ shipCoordinates + "]";
	}

}
