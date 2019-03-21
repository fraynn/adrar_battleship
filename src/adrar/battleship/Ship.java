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
		if (shipCoordinates.contains(square)) {
			int squareIndex = shipCoordinates.indexOf(square);
			shipCoordinates.get(squareIndex).setStatus(SquareStatus.Hit);
			return tellStatus(SquareStatus.Hit);
		} else {
			return tellStatus(SquareStatus.Miss);
		}
	}
	// TODO : rename isHit + change return type updateShipStatus
	// TODO : override equals method + test it

	@Override
	public boolean updateShipStatus() {
		boolean sunk = true;
		for (Square square : shipCoordinates) {
			if (square.getStatus().equals(SquareStatus.Miss) || square.getStatus() == null) {
				sunk = false;
			}
		}
		return sunk;
	}

	private SquareStatus tellStatus(SquareStatus status) {
		if (sunk) {
			return SquareStatus.Sunk;
		} else {
			return status;
		}
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
