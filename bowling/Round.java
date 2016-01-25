package bowling;

import java.util.*;

public class Round {
	private ArrayList<Roll> rolls = new ArrayList<Roll>(3);
	public Round(BowlingApp game) { // constructor
		this.parentGame = game;
	}
	private BowlingApp parentGame;
	
	/**
	 * Adds a new roll with the given number of pins knocked down to the round.
	 * 
	 * @param pinsHit	number	the number of pins knocked down
	 */
	public void addRoll(int pinsHit) {
		rolls.add(new Roll(pinsHit));
		//System.out.println ("Adding roll; score = " + pinsHit + ".");
	}
	
	/**
	 * Used to get the current list of rolls from the round.
	 * 
	 * @return	list of rolls from the current round
	 */
	public ArrayList<Roll> getRollList() {
		return rolls;
	}
	
	/**
	 * Returns the sum of points from all the rolls within the current round, without adding any bonuses
	 * from strikes or spares.
	 * 
	 * @return	the sum of points of the current round.
	 */
	public int getLocalSum () {
		int sum = 0;
		for (Roll roll : rolls) {
			sum += roll.getValue();
		}
		return sum;
	}
	
	/**
	 * Checks if the round is finished, analyzing each possible case. Works for the tenth round as well.
	 * 
	 * @return	true if the round is finished, otherwise false.
	 */
	public boolean isFinished() {
		if (rolls.size() > 0) {
			if (getFrameNumber() != 9 && rolls.get(0).getValue() == 10) { //strike, ordinary frame
				return true;
			}
			if (getFrameNumber() != 9 && rolls.size() == 2) { //two rolls, ordinary frame
				return true;
			}
			if (getFrameNumber() == 9 && getLocalSum() >= 10 && rolls.size() == 3) { // strike or spare, 10th frame
				return true;
			}
			if (getFrameNumber() == 9 && getLocalSum() < 10 && rolls.size() == 2) { // no strike or spare, 10th frame
				return true;
			}
		}
			return false;
	}
	
	/**
	 * Returns the number of the current round (first round being 0 and so on).
	 * 
	 *  @return	the number of the current round
	 */
	public int getFrameNumber() {
		return parentGame.getRoundList().indexOf(this);
	}
	
	/**
	 * Checks if the current round is CURRENTLY the last round in the game.
	 * 
	 * @return	true if the current round is currently the last round, otherwise false.
	 */
	public boolean IsLastFrame() {
		if (parentGame.getRoundList().size() - 1 == getFrameNumber()) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the next round, providing it exists.
	 * 
	 * @return	the next round
	 */
	public Round getNextFrame() {
		if (!IsLastFrame());
		{
			return parentGame.getRoundList().get(getFrameNumber()+1);
		}
	}
	
	/**
	 * Returns the overall score from the round, including bonuses from next rounded awarded
	 * by getting a strike or a spare.
	 * 
	 * @return the overall score from the round.
	 */
	public int getRoundScore() {
		int score = 0;
		int localSum = getLocalSum();
		score = localSum;
		if (rolls.get(0).getValue() == 10) { //strike
			score += getNextTwoRolls();
		}
		if (rolls.size() == 2 && localSum == 10) { //spare
			score += getNextRoll();
		}
		if (getFrameNumber() == 9) { //10th frame: special case, sum only the value within the frame
			//System.out.println ("Current round score: " + localSum + ".");
			return localSum;
		}
		//System.out.println ("Current round score: " + score + ".");
		return score;
	}
	
	/**
	 * Returns the value of the first roll in the next round, if it exists.
	 * 
	 * @return the value of the first roll in the next round, otherwise 0.
	 */
	public int getNextRoll() { 
		if (!IsLastFrame()) {
			return getNextFrame().getRollList().get(0).getValue();
		}
		return 0;
	}
	
	/**
	 * Returns the sum of the values of the closest two rolls in following round(s).
	 * 
	 * @return	the value of the closest two rolls in following round(s). If there's only one such roll,
	 * 			returns its value. If there are no such rolls, returns 0.
	 */
	public int getNextTwoRolls() {
		if (!IsLastFrame()) {
			if (getNextFrame().getRollList().size() == 2) {
				return getNextFrame().getRollList().get(0).getValue() + getNextFrame().getRollList().get(1).getValue();
			}
			if (getNextFrame().getRollList().size() == 1) {
				return getNextFrame().getRollList().get(0).getValue() + getNextFrame().getNextRoll();
			}
		}
		return 0;
	}
}
