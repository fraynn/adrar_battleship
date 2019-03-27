package adrar.battleship;

public class Coordinate {
	private char y;
	private int x;

	public Coordinate(char y, int x) {
		this.y = y;
		this.x = x;
	}

	@Override
	public boolean equals(Object obj) {
		return y == ((Coordinate) obj).getY() && x == ((Coordinate) obj).getX();
	}

	// Get + Set

	public char getY() {
		return y;
	}

	public void setY(char y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}
