package adrar.battleship;

import adrar.battleship.interfaces.StrikeDecisionMakerInterface;

public class StrikeDecisionMaker implements StrikeDecisionMakerInterface {
	private PlayerStrikesHistory strikeHistory;

	public StrikeDecisionMaker() {
		strikeHistory = new PlayerStrikesHistory();
	}

	@Override
	public Square targetSquare() {
		// TODO Auto-generated method stub
		return null;
	}

	private Square targetRandomSquare() {

	}
}
