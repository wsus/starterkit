package bowling;

import java.util.*;

public class BowlingApp {
	
	private ArrayList<Round> rounds = new ArrayList<Round>();
	private BowlingCalc calc = new BowlingCalc(); 
	
	public BowlingApp() { // constructor
		rounds.add(new Round(this));
	}
	
	/**
	 * Used to get the current list of rounds from the game.
	 * 
	 * @return	list of rounds from the current game
	 */
	public ArrayList<Round> getRoundList() {
		return rounds;
	}
	
	/**
	 * Fetches the last round object.
	 * 
	 * @return	last round object
	 */
	private Round getLastRound() {
		return rounds.get(rounds.size() - 1);
	}
	
	/**
	 * Registers a new roll with the given number of pins knocked down. If the last round is not finished, 
	 * it and adds the new roll to it; otherwise it creates a new, empty round, and adds the new roll to it.
	 * 
	 * @param	pinsHit	number		the number of pins knocked down
	 */
	public void roll(int pinsHit) {
		Round round = getLastRound();
		if (round.isFinished()) {
			rounds.add(new Round(this));
			round = getLastRound();
		}
		round.addRoll(pinsHit);
	}
	
	/**
	 * Calculates the overall score of the game using the BowlingCalc object.
	 * 
	 * @return	the score of the game
	 */
	public int getScore() {
		return calc.calculateScore(rounds);
	}
	
	/**
	 * Checks is the current game is over. This is true is there has been ten rounds
	 * and the tenth round is finished.
	 * 
	 * @return true if the game is over, otherwise false
	 */
	public boolean isGameOver() {
		if (rounds.size() == 10 && rounds.get(9).isFinished()) {
			return true;
		}
		return false;
	}
	
}

