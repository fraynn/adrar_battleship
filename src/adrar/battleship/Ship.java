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
	public SquareStatus updateHitSquareStatus(Square square) {
		if (shipCoordinates.contains(square)) {
			updateHitSquare(square);
			return tellStatus(SquareStatus.Hit);
		} else {
			return tellStatus(SquareStatus.Miss);
		}
	}

	private void updateShipSunk() {
		boolean sunk = true;
		for (Square square : shipCoordinates) {
			if (SquareStatus.Miss.equals(square.getStatus()) || square.getStatus() == null) {
				sunk = false;
			}
		}
		setSunk(sunk);
	}

	private SquareStatus tellStatus(SquareStatus status) {
		if (!sunk) {
			updateShipSunk();
		}

		if (sunk) {
			return SquareStatus.Sunk;
		} else {
			return status;
		}
	}

	private void updateHitSquare(Square square) {
		int squareIndex = shipCoordinates.indexOf(square);
		shipCoordinates.get(squareIndex).setStatus(SquareStatus.Hit);
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
