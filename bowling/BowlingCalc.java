package bowling;

import java.util.*;

public class BowlingCalc {
	
	/**
	 * Returns the score of the game, calculated by summing up scores from all the rounds. The list of rounds
	 * is brought from the parent game object in the first parameter.
	 * 
	 * @param rounds	the list of rounds from the parent game object
	 * @return 			the score of the game
	 */
	public int calculateScore (ArrayList<Round> rounds) {
		int score = 0;
		for (Round round : rounds) {
			score += round.getRoundScore();
		}
		return score;
	}
}

