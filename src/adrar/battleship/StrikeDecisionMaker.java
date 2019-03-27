package adrar.battleship;

import java.util.ArrayList;
import java.util.List;

import adrar.battleship.interfaces.StrikeDecisionMakerInterface;

public class StrikeDecisionMaker implements StrikeDecisionMakerInterface {
	public static final int GRID_SIZE = 10;

	private PlayerStrikesHistory missedStrikesList;
	private PlayerStrikesHistory hitSquaresList;
	private List<Square> possibleTargetsList;

	public StrikeDecisionMaker() {
		missedStrikesList = new PlayerStrikesHistory();
		hitSquaresList = new PlayerStrikesHistory();
		possibleTargetsList = new ArrayList<>();
	}

	@Override
	public Square targetSquare() {
		// TODO Auto-generated method stub
		return null;
	}

	private Square targetRandomSquare() {

	}

	private void populatePossibleTargetList() {

	}
}
