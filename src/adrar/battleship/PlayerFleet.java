package adrar.battleship;

import java.util.LinkedList;
import java.util.List;

public class PlayerFleet {
	private List<Ship> shipList;

	public PlayerFleet() {
		shipList = new LinkedList<>();
		populateFleet();
	}

	private void populateFleet() {
		for (ShipType shipType : ShipType.values()) {
			final Ship newShip = new Ship(shipType);
			shipList.add(newShip);
		}
	}

	// Get + Set
	public List<Ship> getShipList() {
		return shipList;
	}

	public void setShipList(List<Ship> shipList) {
		this.shipList = shipList;
	}
}
