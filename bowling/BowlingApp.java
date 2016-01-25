package bowling;

import java.util.*;

public class BowlingApp {
	
	private ArrayList<Round> rounds = new ArrayList<Round>();
	private BowlingCalc calc = new BowlingCalc(); 
	
	public BowlingApp() { // constructor
		rounds.add(new Round(this));
	}
	
	public ArrayList<Round> getRoundList() { // getRoundList: returns the round list.
		return rounds;
	}
	
	private Round getLastRound() { // getLastRound: returns the reference to the last round object.
		return rounds.get(rounds.size() - 1);
	}
	
	public void roll(int pinsHit) { // roll: registers a roll with a given number of pins hit.
		Round round = getLastRound();
		if (round.isFinished()) {
			rounds.add(new Round(this));
			round = getLastRound();
		}
		round.addRoll(pinsHit);
	}
	
	public int getScore() { // getScore: calculate the current overall score.
		return calc.calculateScore(rounds);
	}
	
	public boolean isGameOver() {
		if (rounds.size() == 10 && rounds.get(9).isFinished()) {
			return true;
		}
		return false;
	}
	
}

