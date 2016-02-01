package pokerHands;
import java.util.*;

public class CardGroup implements Comparable<CardGroup> {
	private Integer cardAmount;
	private Integer cardValue;
	
	public CardGroup(Integer amount, Integer Value) {
		this.cardAmount = amount;
		this.cardValue = Value;
	}
	
	public int compareTo(CardGroup group) {
		if (cardAmount != group.getAmount()) {
			return group.getAmount().compareTo(cardAmount);
		}
		return group.getValue().compareTo(cardValue);
	}
	
	public Integer getAmount() {
		return cardAmount;
	}
	
	public Integer getValue() {
		return cardValue;
	}
}
