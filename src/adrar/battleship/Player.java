package adrar.battleship;

import adrar.battleship.interfaces.PlayerInterface;

public class Player implements PlayerInterface {
	private String name;
	private PlayerFleet playerFleet;

	public Player(String name) {
		this.name = name;
		playerFleet = new PlayerFleet();
	}

	@Override
	public SquareStatus receiveAttack(Square square) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Square targetSquare() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasShipsLeft() {
		boolean hasShipsLeft = false;
		for (Ship ship : playerFleet.getShipList()) {
			if (!ship.isSunk()) {
				hasShipsLeft = true;
				return hasShipsLeft;
			}
		}
		return hasShipsLeft;
	}

	@Override
	public SquareStatus provideShotResponse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void startNewGame() {
		// TODO Auto-generated method stub

	}

	// Get + Set
	public PlayerFleet getShipList() {
		return playerFleet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
