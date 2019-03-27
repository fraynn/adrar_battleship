package adrar.battleship;

public class Square {
	private char y;
	private int x;
	private SquareStatus status;

	// Constructor
	public Square(char y, int x) {
		this.y = y;
		this.x = x;
		status = null;
	}

	// Methods
	@Override
	public boolean equals(Object obj) {
		return y == ((Square) obj).getY() && x == ((Square) obj).getX();
	}

	@Override
	public String toString() {
		return "Square [y=" + y + ", x=" + x + ", status=" + status + "]";
	}

	// Get + Set
	public SquareStatus getStatus() {
		return status;
	}

	public void setStatus(SquareStatus status) {
		this.status = status;
	}

	public int getX() {
		return x;
	}

	public char getY() {
		return y;
	}

}
