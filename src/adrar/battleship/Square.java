package adrar.battleship;

public class Square {
	private Coordinate coordinates;
	private SquareStatus status;
	private Coordinate topLeftSquare;
	private Coordinate topRightSquare;
	private Coordinate bottomLeftSquare;
	private Coordinate bottomRightSquare;
	private Coordinate leftSquare;
	private Coordinate topSquare;
	private Coordinate rightSquare;
	private Coordinate bottomSquare;

	// Constructor
	public Square(char y, int x) {
		coordinates = new Coordinate(y, x);
		status = null;
		setupAdjacentAndDiagonalSquares();
	}

	// Methods
	public boolean isDiagonalCoordinate(Coordinate coordinate) {
		return coordinate.equals(topLeftSquare) || coordinate.equals(topRightSquare)
				|| coordinate.equals(bottomLeftSquare) || coordinate.equals(bottomRightSquare);
	}

	@Override
	public boolean equals(Object obj) {
		return getY() == ((Square) obj).getY() && getX() == ((Square) obj).getX();
	}

	@Override
	public String toString() {
		return "Square [y=" + getY() + ", x=" + getX() + ", status=" + status + "]";
	}

	private void setupAdjacentAndDiagonalSquares() {
		topLeftSquare = new Coordinate((char) (getY() - 1), getX() - 1);
		topRightSquare = new Coordinate((char) (getY() - 1), getX() + 1);
		bottomLeftSquare = new Coordinate((char) (getY() + 1), getX() - 1);
		bottomRightSquare = new Coordinate((char) (getY() + 1), getX() + 1);
		leftSquare = new Coordinate(getY(), getX() - 1);
		topSquare = new Coordinate((char) (getY() - 1), getX());
		rightSquare = new Coordinate(getY(), getX() + 1);
		bottomSquare = new Coordinate((char) (getY() + 1), getX());
	}

	// Get + Set
	public SquareStatus getStatus() {
		return status;
	}

	public void setStatus(SquareStatus status) {
		this.status = status;
	}

	public Coordinate getCoordinates() {
		return coordinates;
	}

	public int getX() {
		return coordinates.getX();
	}

	public char getY() {
		return coordinates.getY();
	}

	public Coordinate getTopLeftSquare() {
		return topLeftSquare;
	}

	public Coordinate getTopRightSquare() {
		return topRightSquare;
	}

	public Coordinate getBottomLeftSquare() {
		return bottomLeftSquare;
	}

	public Coordinate getBottomRightSquare() {
		return bottomRightSquare;
	}

	public Coordinate getLeftSquare() {
		return leftSquare;
	}

	public Coordinate getTopSquare() {
		return topSquare;
	}

	public Coordinate getRightSquare() {
		return rightSquare;
	}

	public Coordinate getBottomSquare() {
		return bottomSquare;
	}
}
