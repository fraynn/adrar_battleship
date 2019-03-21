package adrar.battleship;

public enum ShipType {
	CARRIER(5), BATTLESHIP(4), CRUISER(3), SUBMARINE(3), DESTROYER(2);

	private int size;

	ShipType(int size) {
		this.size = size;
	}

	public int getValue() {
		return size;
	}
}
