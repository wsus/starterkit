package bowling;

import java.util.*;

public class Round {
	private ArrayList<Roll> rolls = new ArrayList<Roll>(3);
	public Round(BowlingApp game) { // constructor
		this.parentGame = game;
	}
	private BowlingApp parentGame;
	
	public void addRoll(int pinsHit) { // addRoll: add a new roll to the round.
		rolls.add(new Roll(pinsHit));
		//System.out.println ("Adding roll; score = " + pinsHit + ".");
	}
	
	public ArrayList<Roll> getRollList() { // getRollList: returns the list of rolls in the round.
		return rolls;
	}
	
	public int getLocalSum () {
		int sum = 0;
		for (Roll roll : rolls) {
			sum += roll.getValue();
		}
		return sum;
	}
	
	public boolean isFinished() { // isFinished: returns true if the round is finished
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
	
	public int getFrameNumber() { // getFrameNumber: returns the index of the current frame (starting from 0)
		return parentGame.getRoundList().indexOf(this);
	}
	
	public boolean IsLastFrame() { // isLastFrame: returns true if the current frame is currently the last frame on the list 
		if (parentGame.getRoundList().size() - 1 == getFrameNumber()) {
			return true;
		}
		return false;
	}

	public Round getNextFrame() { // getNextFrame: returns the reference to the following frame, if it exists.
		if (!IsLastFrame());
		{
			return parentGame.getRoundList().get(getFrameNumber()+1);
		}
	}
	
	public int getRoundScore() { // getRoundScore: returns the overall score from the round, including bonuses from strike and spare.
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
	
	public int getNextRoll() { //get one follow
		if (!IsLastFrame()) {
			return getNextFrame().getRollList().get(0).getValue();
		}
		return 0;
	}
	
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
