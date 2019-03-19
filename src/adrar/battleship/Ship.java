package adrar.battleship;

public class Ship {
	private ShipType shipType;
	private int size;
	private boolean sunk;

	// Get + Set
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isSunk() {
		return sunk;
	}

	public void setSunk(boolean sunk) {
		this.sunk = sunk;
	}

}
