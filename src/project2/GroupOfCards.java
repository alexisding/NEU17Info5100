package project2;

import com.sun.deploy.util.ArrayUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexis on 10/30/17.
 */
public class GroupOfCards {
	private List<Card> cards;
	private int currentSize;

	public GroupOfCards(int numOfPlayers) {
		this.cards = new ArrayList<Card>();
		currentSize = 0;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public Card removeCard(int index) {
		Card removedCard = cards.get(index);
		cards.remove(index);
		return removedCard;
	}

	public Card getCard(int i) {
		return cards.get(i);
	}

	public int getCurrentSize() {
		return cards.size();
	}

	public List<Card> getCards() {
		return cards;
	}

	public void display() {
		cards.forEach(card -> card.display());
	}
}
