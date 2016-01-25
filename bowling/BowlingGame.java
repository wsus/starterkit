package bowling;

public class BowlingGame {
	private int[] rolls = new int[21]; //rolls table
	private int current = 0; //current roll
	private int score; //current score
	private int frame; //current frame
	private boolean secondThrow; //true if currently on the second throw of the frame
	private int pinsLeft; //pins left standing
	
	public void roll (int pinsHit) { //register a roll in the rolls table
		rolls[current] = pinsHit;
		current++;
	}
	
	public int score () { //calculate current score
		score = 0;
		frame = 1;
		secondThrow = false;
		pinsLeft = 10;
		for (int i = 0; i < current; i++) { //loop through the rolls table
			//System.out.println ("Frame " + frame + ".");
			score += rolls[i]; //add points from the current roll
			if (frame != 10) { // for all frames except the 10th
				if (rolls[i] == 10) { // strike: add points from the next two throws and begin a new frame
					score += rolls[i+1] + rolls[i+2];
					frame++;
				}
				else {
					pinsLeft -= rolls[i];
					if (secondThrow) { //second throw: begin a new frame
						if (pinsLeft == 0) { // spare: add points from the next throw
							score += rolls[i+1];
						}
						pinsLeft = 10;
						frame++;
						secondThrow = false;
					}
					else //first throw: mark the next throw as the second throw
					{
						secondThrow = true;
					}
				}
			}
			//System.out.println ("Current score: " + score + ".");
		}
		return score;
	}
}
