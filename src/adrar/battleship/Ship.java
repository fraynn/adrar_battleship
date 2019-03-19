package adrar.battleship;

import java.util.List;

public class Ship {
	private ShipType shipType;
	private int size;
	private List<Square> shipCoordinates;

	public Ship(ShipType shipType) {
		this.shipType = shipType;
		size = shipType.getValue();
	}

	// Get + Set
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<Square> getShipCoordinates() {
		return shipCoordinates;
	}

	public void setShipCoordinates(List<Square> shipCoordinates) {
		this.shipCoordinates = shipCoordinates;
	}

	public ShipType getShipType() {
		return shipType;
	}

	@Override
	public String toString() {
		return "Ship [shipType=" + shipType + ", size=" + size + ", shipCoordinates=" + shipCoordinates + "]";
	}

}
