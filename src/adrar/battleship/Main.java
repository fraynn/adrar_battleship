package adrar.battleship;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		List<Integer> scores = new ArrayList<>();
		for (int i = 0; i < 100; i++) {
			scores.add(playGame());
		}
//		scores.removeIf(score -> score >= 40);
		System.out.println(scores);
	}

	public static int playGame() {
		StrikeDecisionMaker ia = new StrikeDecisionMaker();

		List<Ship> enemyShipsList = new ArrayList<>();

		Ship bigShip = new Ship(ShipType.CARRIER);
		List<Square> bigShipSquares = new ArrayList<>();
		bigShipSquares.add(new Square('A', 9));
		bigShipSquares.add(new Square('B', 9));
		bigShipSquares.add(new Square('C', 9));
		bigShipSquares.add(new Square('D', 9));
		bigShipSquares.add(new Square('E', 9));
		bigShip.setShipCoordinates(bigShipSquares);

		Ship secondBiggestShip = new Ship(ShipType.BATTLESHIP);
		List<Square> secondBiggestShipSquares = new ArrayList<>();
		secondBiggestShipSquares.add(new Square('G', 6));
		secondBiggestShipSquares.add(new Square('G', 7));
		secondBiggestShipSquares.add(new Square('G', 8));
		secondBiggestShipSquares.add(new Square('G', 9));
		secondBiggestShip.setShipCoordinates(secondBiggestShipSquares);

		Ship cruiserShip = new Ship(ShipType.CRUISER);
		List<Square> cruiserSquares = new ArrayList<>();
		cruiserSquares.add(new Square('B', 2));
		cruiserSquares.add(new Square('B', 3));
		cruiserSquares.add(new Square('B', 4));
		cruiserShip.setShipCoordinates(cruiserSquares);

		Ship submarineShip = new Ship(ShipType.SUBMARINE);
		List<Square> submarineSquares = new ArrayList<>();
		submarineSquares.add(new Square('E', 2));
		submarineSquares.add(new Square('E', 3));
		submarineSquares.add(new Square('E', 4));
		submarineShip.setShipCoordinates(submarineSquares);

		Ship destroyerShip = new Ship(ShipType.DESTROYER);
		List<Square> destroyerSquare = new ArrayList<>();
		destroyerSquare.add(new Square('I', 2));
		destroyerSquare.add(new Square('J', 2));
		destroyerShip.setShipCoordinates(destroyerSquare);

		enemyShipsList.add(bigShip);
		enemyShipsList.add(secondBiggestShip);
		enemyShipsList.add(cruiserShip);
		enemyShipsList.add(submarineShip);
		enemyShipsList.add(destroyerShip);

		int remainingShips = 5;
		int shotsNumber = 0;

		while (remainingShips > 0) {

			Coordinate targetCoordinate = ia.targetSquare();
			List<SquareStatus> shotResponseList = new ArrayList<>();

			for (Ship ship : enemyShipsList) {
				SquareStatus shotResponse = ship
						.updateHitSquareStatus(new Square(targetCoordinate.getY(), targetCoordinate.getX()));
				shotResponseList.add(shotResponse);
			}

			SquareStatus biggestResponse = shotResponseList.get(0);
			for (int i = 1; i < shotResponseList.size(); i++) {
				if (biggestResponse.compareTo(shotResponseList.get(i)) <= 0) {
					biggestResponse = shotResponseList.get(i);
				}
			}
			if (biggestResponse == SquareStatus.Sunk) {
				remainingShips--;
			}

			enemyShipsList.removeIf(ship -> ship.isSunk());
			ia.updateListsBasedOnStrikeResult(biggestResponse);

			System.out.println(targetCoordinate + biggestResponse.toString());

			shotsNumber++;
		}

		return shotsNumber;

	}

}
