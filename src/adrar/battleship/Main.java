package adrar.battleship;

public class Main {

	public static void main(String[] args) {
		PlayerFleet pFleet = new PlayerFleet();
		pFleet.makeUnavailableAllSquareSlotsAroundSquareSlot(40);
		pFleet.getAvailableSquareSlots();
	}

}
