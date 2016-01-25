package bowling;

import java.util.*;

public class BowlingCalc {
	
	public int calculateScore (ArrayList<Round> rounds) {
		int score = 0;
		for (Round round : rounds) {
			score += round.getRoundScore();
		}
		return score;
	}
}

