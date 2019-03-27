package adrar.battleship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import adrar.battleship.interfaces.PlayerFleetInterface;

public class PlayerFleet implements PlayerFleetInterface {
	private List<Ship> shipList;
	private List<Integer> availableSquareSlotsList;
	private List<Integer> availablePotentialLastShipSlotsList;
	private List<Integer> currentShipSquareSlotsList;

	// Constructor
	public PlayerFleet() {
		shipList = new LinkedList<>();
		availableSquareSlotsList = new LinkedList<>();
		availablePotentialLastShipSlotsList = new ArrayList<>();
		currentShipSquareSlotsList = new ArrayList<>();
		fillAvailableSquareSlotsList();
//		for (ShipType actualShipType : ShipType.values()) {
//			availablePotentialLastShipSlotsList.clear();
//			currentShipSquareSlotsList.clear();
//			Ship newShip = createShip(generateShipCoordinates(actualShipType.getValue()), actualShipType);
//			addShipToFleet(newShip);
////			System.out.println(listOfShipTypes);
////			System.out.println(listOfShipTypes.getValue());
//		}
//		getAvailableCoordinates();
	}

	// Methods
	// Fill the available slots list with slots identifiers
	public void fillAvailableSquareSlotsList() {
		for (int i = 0; i < Square.getSquareSlotsNumber(); i++) {
			availableSquareSlotsList.add(i);
		}
	}

	// Fill the list of the available ship last slots
	public void fillAvailablePotentialLastShipSlotsList(int slot, int size) {
		int RightLastSlot = slot + (size - 1);
		int LeftLastSlot = slot - (size - 1);
		int BottomLastSlot = slot + Square.MAX_SQUARE_VALUE * (size - 1);
		int UpLastSlot = slot - Square.MAX_SQUARE_VALUE * (size - 1);
		availablePotentialLastShipSlotsList.add(RightLastSlot);
		availablePotentialLastShipSlotsList.add(LeftLastSlot);
		availablePotentialLastShipSlotsList.add(BottomLastSlot);
		availablePotentialLastShipSlotsList.add(UpLastSlot);
	}

	// Shuffle the list of the available ship last slots
	public void shuffleAvailablePotentialLastShipSlotsList() {
		Collections.shuffle(availablePotentialLastShipSlotsList);
	}

	// Set a beginning slot for the ship and return it
	public int setShipFirstSlot() {
		int randomSlot = getRandomAvailableSquareSlot();
		System.out.println(randomSlot);
		return randomSlot;
	}

	// Set the ending slot for the ship
	// TODO
	public int setShipLastSlot(int slot, int size) {
		fillAvailablePotentialLastShipSlotsList(slot, size);
		shuffleAvailablePotentialLastShipSlotsList();
		while (!isInAvailableSquareSlotsList(availablePotentialLastShipSlotsList.get(0))) {
			availablePotentialLastShipSlotsList.remove(0);
		}
		return availablePotentialLastShipSlotsList.get(0);
	}

	// Set the inner slots for the ship
	public void fillCurrentShipSquareSlotsList(int firstSlot, int lastSlot) {
		if (lastSlot - firstSlot < Square.MAX_SQUARE_VALUE) {
			for (int i = firstSlot; i <= lastSlot; i++) {
				currentShipSquareSlotsList.add(i);
				System.out.println(i);
			}
		} else {
			for (int i = firstSlot; i <= lastSlot; i += Square.MAX_SQUARE_VALUE) {
				currentShipSquareSlotsList.add(i);
				System.out.println(i);
			}
		}
	}

	// Get a random integer
	public int getRandomInteger(int maxIntValue) {
		return (int) (Math.random() * maxIntValue);
	}

	// Get a random slot into the list of the unused and available slots
	public int getRandomAvailableSquareSlot() {
		int index = getRandomInteger(availableSquareSlotsList.size());
		System.out.println(index);
		return availableSquareSlotsList.get(index);
	}

	// Convert and get square from slot
	public Square getSquareFromSquareSlot(int slot) {
		int y = slot % Square.getMaxSquareValue();
		int x = Square.getMaxSquareValue() - y;
		Square square = new Square(x, y);
		return square;
	}

	// Make unavailable all the square slots around a square given and the square
	// given
	public void makeUnavailableAllSquareSlotsAroundSquareSlot(int startingSlot) {
		int offset = 0;
		availableSquareSlotsList
				.remove(startingSlot - offset + Square.MAX_SQUARE_VALUE * booleanToInt(!isDown(startingSlot)));
		offset += booleanToInt(!isDown(startingSlot));
		availableSquareSlotsList
				.remove(startingSlot - offset - Square.MAX_SQUARE_VALUE * booleanToInt(!isUp(startingSlot)));
		offset += booleanToInt(!isUp(startingSlot));
		availableSquareSlotsList.remove(startingSlot - offset + 1 * booleanToInt(!isRight(startingSlot)));
		availableSquareSlotsList.remove(startingSlot - offset - 1 * booleanToInt(!isLeft(startingSlot)));
		availableSquareSlotsList.remove(startingSlot - offset + (Square.MAX_SQUARE_VALUE + 1)
				* booleanToInt(!isDown(startingSlot)) * booleanToInt(!isRight(startingSlot)));
		availableSquareSlotsList.remove(startingSlot - offset + (Square.MAX_SQUARE_VALUE - 1)
				* booleanToInt(!isDown(startingSlot)) * booleanToInt(!isLeft(startingSlot)));
		availableSquareSlotsList.remove(startingSlot - offset - (Square.MAX_SQUARE_VALUE + 1)
				* booleanToInt(!isUp(startingSlot)) * booleanToInt(!isRight(startingSlot)));
		availableSquareSlotsList.remove(startingSlot - offset - (Square.MAX_SQUARE_VALUE - 1)
				* booleanToInt(!isUp(startingSlot)) * booleanToInt(!isLeft(startingSlot)));
		availableSquareSlotsList.remove(startingSlot - offset);
	}

	// Make unavailable all the squares slots around a ship given and the ship slots
	// themselves
	public void makeUnavailableAllSquareSlotsAroundShip() {
		for (Integer slot : currentShipSquareSlotsList) {
			makeUnavailableAllSquareSlotsAroundSquareSlot(slot);
		}
	}

	// Get available empty coordinates
	public List<Integer> getAvailableSquareSlots() {
		for (int i = 0; i < availableSquareSlotsList.size(); i++) {
			System.out.println(availableSquareSlotsList.get(i));
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
		int firstSlot = setShipFirstSlot();
		int lastSlot = setShipLastSlot(firstSlot, shipSize);
		fillCurrentShipSquareSlotsList(firstSlot, lastSlot);
		makeUnavailableAllSquareSlotsAroundShip(firstSlot, shipSize);
		// TODO Auto-generated method stub
		return null;
	}

	// Convert boolean to integer
	public static int booleanToInt(boolean value) {
		// Convert true to 1 and false to 0.
		return value ? 1 : 0;
	}

	// Check if necessary
	// Slots
	public boolean isRight(int slot) {
		return (slot - 9) % 10 == 0;
	}

	public boolean isLeft(int slot) {
		return slot % 10 == 0;
	}

	public boolean isUp(int slot) {
		return slot >= 0 && slot < Square.MAX_SQUARE_VALUE;
	}

	public boolean isDown(int slot) {
		return slot >= 90 && slot < Square.MAX_SQUARE_VALUE * Square.MAX_SQUARE_VALUE;
	}

	// Coordinates
	private boolean isInAvailableSquareSlotsList(int slot) {
		return availableSquareSlotsList.contains(slot);
	}

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
