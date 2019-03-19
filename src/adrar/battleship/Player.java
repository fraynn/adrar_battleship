package adrar.battleship;

public class Player {
	private String name;
	private PlayerFleet playerFleet;

	public Player(String name) {
		this.name = name;
		playerFleet = new PlayerFleet();
	}

	public void placeShip(Ship ship) {
		// TODO
//		ship.setShipCoordinates();
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
