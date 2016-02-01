package pokerHands;
import java.util.*;

public class pokerGame {
	private List<Hand> player1Hands = new ArrayList<Hand>();
	private List<Hand> player2Hands = new ArrayList<Hand>();
	
	public void AddCards(List<Hand> hands, Integer amount, Integer value, boolean hasFlush) {
		hands.add(new Hand(amount, value, hasFlush));
	}
	
	
}
