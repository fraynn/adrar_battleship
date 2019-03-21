package adrar.battleship;

public class Player {
	private String name;
	private PlayerFleet shipList;

	public Player(String name) {
		this.name = name;
		shipList = new PlayerFleet();
	}

	public void placeShip(Ship ship) {
		// TODO
//		ship.setShipCoordinates();
	}

	// Get + Set
	public PlayerFleet getShipList() {
		return shipList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
