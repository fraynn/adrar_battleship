package adrar.battleship;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import adrar.battleship.interfaces.PlayerFleetInterface;

public class PlayerFleet implements PlayerFleetInterface {
	private List<Ship> shipList;
	private List<Integer> availableSquareSlotsList;

	// Constructor
	public PlayerFleet() {
		shipList = new LinkedList<>();
		availableSquareSlotsList = new LinkedList<>();
		fillAvailableSquareSlotsList();
		for (ShipType actualShipType : ShipType.values()) {
			Ship newShip = createShip(generateShipCoordinates(actualShipType.getValue()), actualShipType);
			addShipToFleet(newShip);
//			System.out.println(listOfShipTypes);
//			System.out.println(listOfShipTypes.getValue());
		}
	}

	/*-creer un bateau depuis l'enum
	-determiner la premiere coordonnée aleatoirement
	-verifier qu'il n'y a rien sur cette case et près de cette case
	-déterminer la 2e coordonnée aleatoirement
	-verifier qu'il n'y a rien sur cette case et près de cette case
	-déterminer chaque case du bateau en fonction des 2 cases début et fin
	-verifier qu'il n'y a rien près de toutes les cases du bateau
	-passer au bateau suivant dans l'enum
	*/

	// Methods
	// Fill the available slots list with slots identifiers
	public void fillAvailableSquareSlotsList() {
		for (int i = 0; i < Square.getSquareSlotsNumber(); i++) {
			availableSquareSlotsList.add(i);
			System.out.println(i);
		}
	}

	// Set a beginning slot for the ship
	public void setShipFirstSlot() {
		int randomSlot = getRandomAvailableSquareSlot();
		makeUnavailableAllSquareSlotsAroundSquare(randomSlot);
	}

	// Set the ending slot for the ship
	public void setShipLastSlot() {

	}

	// Set the inner slots for the ship
	public void setShipInnerSlots() {

	}

	// Get a random slot into the list of the unused and available slots
	public int getRandomAvailableSquareSlot() {
		int index = (int) (Math.random() * availableSquareSlotsList.size());
		System.out.println(index);
		return availableSquareSlotsList.get(index);
	}

	// Convert and get square from slot
	public Square getSquareFromSquareSlot(int slotIdentifier) {
		int y = slotIdentifier % Square.getMaxSquareValue();
		int x = Square.getMaxSquareValue() - y;
		Square square = new Square(x, y);
		return square;
	}

	// Make unavailable all the square slots around a square given
	public void makeUnavailableAllSquareSlotsAroundSquare(int slotIdentifier) {
		int maxSlotIdToRemove = slotIdentifier + Square.MAX_SQUARE_VALUE + 1;
		int minSlotIdToRemove = slotIdentifier - Square.MAX_SQUARE_VALUE - 1;
//		System.out.println(maxSlotIdToRemove);
//		System.out.println(minSlotIdToRemove);
		for (Iterator<Integer> iterator = availableSquareSlotsList.iterator(); iterator.hasNext();) {
			Integer integer = iterator.next();
			if (integer <= maxSlotIdToRemove && integer >= minSlotIdToRemove) {
				iterator.remove();
			}
		}
	}

	// Get available empty coordinates
	public List<Integer> getAvailableCoordinates() {
		for (int i = 0; i < availableSquareSlotsList.size(); i++) {
			System.out.println(i);
		}
		return availableSquareSlotsList;
	}

	@Override
	public Ship createShip(List<Square> coordinatesList, ShipType type) {
		// TODO
		Ship ship = new Ship(type);
		return ship;
	}

	@Override
	public void addShipToFleet(Ship ship) {
		// TODO
		shipList.add(ship);
	}

	@Override
	public boolean checkShipCoordinates(List<Square> coordinatesList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Square> generateShipCoordinates(int shipSize) {
		setShipFirstSlot();
		// TODO Auto-generated method stub
		return null;
	}

	//

	// Check if necessary
	private boolean hasCorrectCoordinates(Ship ship) {
		return hasCorrectNumberOfCoordinates(ship) && hasConsecutiveCoordinates(ship);
	}

	private boolean hasCorrectNumberOfCoordinates(Ship ship) {
		return ship.getShipCoordinates().size() == ship.getShipType().getValue();
	}

	private boolean hasConsecutiveCoordinates(Ship ship) {
		return areHorizontalConsecutive(ship.getShipCoordinates().get(0),
				ship.getShipCoordinates().get(ship.getShipCoordinates().size()))
				|| areVerticalConsecutive(ship.getShipCoordinates().get(0),
						ship.getShipCoordinates().get(ship.getShipCoordinates().size()));
	}

	private boolean areHorizontalConsecutive(Square a, Square b) {
		return a.getX() == b.getX();
	}

	private boolean areVerticalConsecutive(Square a, Square b) {
		return a.getY() == b.getY();
	}

	// Get + Set
	public List<Ship> getShipList() {
		return shipList;
	}

	public void setShipList(List<Ship> shipList) {
		this.shipList = shipList;
	}

}
