package adrar.battleship;

public class Square {
	private int y;
	private char x;
	private SquareStatus status;

	// Get + Set

	public SquareStatus getStatus() {
		return status;
	}

	public void setStatus(SquareStatus status) {
		this.status = status;
	}

	public int getY() {
		return y;
	}

	@Override
	public String toString() {
		return "Square [y=" + y + ", x=" + x + ", status=" + status + "]";
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getX() {
		return x;
	}

	public void setX(char x) {
		this.x = x;
	}

}
