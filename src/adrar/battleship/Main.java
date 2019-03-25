package adrar.battleship;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		PlayerStrikesHistory playerStrikesHistory = new PlayerStrikesHistory();
		Square square = new Square('D', 3);
		System.out.println(square.getStatus());
		List<Square> strikeHistory = new ArrayList<>();
//		System.out.println(playerStrikesHistory.checkIfStrikeExists(square));
		playerStrikesHistory.addStrike(square);
//		System.out.println(plastrikeHistory);

		// System.out.println(playerStrikesHistory.checkIfStrikeExists(square));
		// playerStrikesHistory.addStrike(square);
	}

}
