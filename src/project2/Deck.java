package project2;

import java.util.List;
import java.util.Random;

/**
 * Created by alexis on 10/30/17.
 */
public class Deck extends GroupOfCards{
	// default number of cards is 52
	public static final int TOTAL_CARDS = 52;

	public Deck() {
		super(TOTAL_CARDS);
	}

	// shuffle cards
	public void shuffle() {
		Random random = new Random();
		random.nextInt();
		int n = getCards().size();
		for (int i = 0; i < n; i++) {
			int change = i + random.nextInt(n - i);
			swap(getCards(), i, change);
		}
		for (int i = getCurrentSize() - 1; i >= 0; i--) {
			addCard(removeCard((int) (Math.random() * (getCurrentSize() - 1))));
		}
	}

	// deal a card
	public Card dealCard() {
		return removeCard(0);
	}

	// swap helper
	private static void swap(List<Card> cards, int i, int change) {
		Card helper = cards.get(i);
		cards.set(i, cards.get(change));
		cards.set(change, helper);
	}
}
