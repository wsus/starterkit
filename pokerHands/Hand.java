package pokerHands;
import java.util.*;

public class Hand implements Comparable<Hand> {
	/*private List<Integer> player1Amounts = new LinkedList<Integer>();
	private List<Integer> player1Types = new LinkedList<Integer>();
	private List<Integer> player2Amounts = new LinkedList<Integer>();
	private List<Integer> player2Types = new LinkedList<Integer>();*/
	private LinkedList<CardGroup> hand = new LinkedList<CardGroup>();
	private boolean flush;
	private Integer advRank = 0;
	
	public Hand (Integer amount, Integer value, boolean hasFlush) {
		this.hand.add(new CardGroup(amount, value));
		this.flush = hasFlush;
		/*p1Hand.add(new CardGroup(1,3));
		p1Hand.add(new CardGroup(1,4));
		p1Hand.add(new CardGroup(1,5));
		p1Hand.add(new CardGroup(1,6));
		p1Hand.add(new CardGroup(1,7));
		p2Hand.add(new CardGroup(1,6));
		p2Hand.add(new CardGroup(1,5));
		p2Hand.add(new CardGroup(1,4));
		p2Hand.add(new CardGroup(1,7));
		p2Hand.add(new CardGroup(1,3));*/
		Collections.sort(hand);

		for (CardGroup group : hand) {
			System.out.println(group.getAmount() + "," + group.getValue());
		}
		System.out.println("");
	}
	
	public List<CardGroup> getList() {
		return hand;
	}
	
	public Integer getAdvRank() {
		return advRank;
	}
	
	public boolean hasFlush() {
		return flush;
	}
	
	private boolean hasStraight () {
		return (hand.size() == 5 && hand.getFirst().getValue() - hand.getLast().getValue() == 4);
	}
	
	private Integer checkAdvancedRanks() {
		if (hand.size() == 5) {
			if (flush && hasStraight()) { //straight flush
				return 1;
			}
			if (flush) { //flush
				return 2;
			}
			if (hasStraight()) { //straight
				return 2;
			}
			return 0;
		}
		return 0;
	}
	
	private Integer CompareTwoNormalRanks(List<CardGroup> p2Hand) {
		for (CardGroup group : hand) {
			Integer index = hand.indexOf(group);
			if (group.getAmount() != p2Hand.get(index).getAmount()) {
				return group.getAmount().compareTo(p2Hand.get(index).getAmount());
			}
		}
		for (CardGroup group : hand) {
			Integer index = hand.indexOf(group);
			if (group.getValue() != p2Hand.get(index).getValue()) {
				return group.getValue().compareTo(p2Hand.get(index).getValue());
			}
		}
		return 0;
	}
	
	private Integer CompareAdvancedVsNormalRank (Integer first, Integer second) {
		if (second > first) {
			return 1;
		}
		return -1;
	}
	
	private Integer CompareTwoAdvancedRanks (Hand p2Hand, Integer first, Integer second, boolean flush1, boolean flush2) {
		if (second != first) {
			return second.compareTo(first);
		}
		if (first == 2) {
			if (flush1 && !flush2) {
				return 1;
			}
			if (flush1 && !flush2) {
				return -1;
			}
		}
		return CompareTwoNormalRanks(p2Hand.getList());
	}
	
	public int compareTo(Hand p2Hand) {
		Integer advP1Rank = checkAdvancedRanks();
		Integer advP2Rank = p2Hand.checkAdvancedRanks();
		Integer p1Size = new Integer(hand.size());
		Integer p2Size = new Integer(p2Hand.getList().size());
		System.out.println("Sizes = " + p1Size + ", " + p2Size);
		System.out.println("Ranks = " + advP1Rank + ", " + advP1Rank);
		if (!p1Size.equals(p2Size) && advP1Rank == 0 && advP2Rank == 0) {
			System.out.println("Both players have normal ranks. Comparison by rank size.");
			return p2Size.compareTo(p1Size); //smaller wins
		}
		if (p1Size.equals(p2Size) && advP1Rank == 0 && advP2Rank == 0) {
			System.out.println("Both players have normal ranks.");
			return CompareTwoNormalRanks(p2Hand.getList());
		}
		if (advP1Rank != 0 && advP2Rank == 0) {
			System.out.println("Player 1 has an advanced rank.");
			return CompareAdvancedVsNormalRank(advP1Rank, p2Size);
		}
		if (advP1Rank == 0 && advP2Rank != 0) {
			System.out.println("Player 2 has an advanced rank.");
			return CompareAdvancedVsNormalRank(p1Size, advP2Rank);
		}
		if (advP1Rank != 0 && advP2Rank != 0) {
			System.out.println("Both players have an advanced rank.");
			return CompareTwoAdvancedRanks(p2Hand, advP1Rank, advP2Rank, hasFlush(), p2Hand.hasFlush());
		}
		return 0;
	}
	
	public static void main(String args[]) {
		Hand hand = new Hand();
		hand.AddCards();
		//int test = hand.DetermineWinner();
		//System.out.println(test);
	}
	}