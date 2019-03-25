package adrar.battleship;

import java.util.ArrayList;
import java.util.List;

public class Square {
	private int y;
	private int x;
	private SquareStatus status;
	public static final int MIN_SQUARE_VALUE = 0;
	public static final int MAX_SQUARE_VALUE = 10;

	// Constructor
	public Square(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Methods
	public static int getSquareSlotsNumber() {
		return (MAX_SQUARE_VALUE) * (MAX_SQUARE_VALUE);
	}

	public List<Integer> getSquareCoordinates() {
		List<Integer> coordinatesList = new ArrayList<>();
		coordinatesList.add(x);
		coordinatesList.add(y);
		return coordinatesList;
	}

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

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public static int getMinSquareValue() {
		return MIN_SQUARE_VALUE;
	}

	public static int getMaxSquareValue() {
		return MAX_SQUARE_VALUE;
	}
}
