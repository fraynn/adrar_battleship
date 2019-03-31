package adrar.battleship;

public class Main {

	public static void main(String[] args) {
		StrikeDecisionMaker ia = new StrikeDecisionMaker();
		ia.populatePossibleTargetList();
//		System.out.println(ia.getAvailableTargetsList().size());
//
		ia.updateListsBasedOnStrikeResult(SquareStatus.Hit);
//		ia.setCurrentStrikeAttempt(new Coordinate('B', 3));
//		ia.updateListsBasedOnStrikeResult(SquareStatus.Sunk);
//		System.out.println(ia.targetAdjacentVerticalSquare());
//		ia.setCurrentStrikeAttempt(new Coordinate('B', 4));
//		ia.updateListsBasedOnStrikeResult(SquareStatus.Sunk);
		System.out.println(ia.getStrikesHistory());
		System.out.println(ia.getHitSquaresList());
		System.out.println(ia.getShipsSunkSizes());
		System.out.println(ia.getAvailableTargetsList());
		System.out.println(ia.getCurrentlyTargetedShipDirection());
		System.out.println(ia.findAdjacentTargets());
		System.out.println(ia.targetSquare());

	}

}
