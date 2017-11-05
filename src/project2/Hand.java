package project2;

import java.util.List;

/**
 * Created by alexis on 10/30/17.
 */
public class Hand extends GroupOfCards {
	public final int NUM;
	private int shortest = 0;

	public Hand(int playerNum, int numOfCards) {
		super(numOfCards);
		NUM = playerNum;
	}

	public void sort() {
		for (int unsorted = this.getCurrentSize(); unsorted > 0; unsorted--) {
			int maxIndex = unsorted - 1;
			for (int i = 0; i < unsorted; i++) {
				int currentValue = this.getCard(i).getSuit().getNumber() * 13 + this.getCard(i).getNum();
				int maxValue = this.getCard(maxIndex).getSuit().getNumber() * 13 + this.getCard(maxIndex).getNum();
				if (currentValue > maxValue)
					maxIndex = i;
			}
			Card card = this.removeCard(maxIndex);
			this.addCard(card);
		}
	}

	public void setShortest() {

		int min = Integer.MAX_VALUE;
		if (findCount(Suit.CLUB) < min && findCount(Suit.CLUB) != 0) {
			min = findCount(Suit.CLUB);
			shortest = Suit.CLUB.getNumber();
		}
		if (findCount(Suit.DIAMOND) < min && findCount(Suit.DIAMOND) != 0) {
			min = findCount(Suit.DIAMOND);
			shortest = Suit.DIAMOND.getNumber();
		}

		if (findCount(Suit.SPADE) < min && findCount(Suit.SPADE) != 0 && find(12, Suit.SPADE) == -1 && find(13, Suit.SPADE) == -1 && find(14, Suit.SPADE) == -1) {
			min = findCount(Suit.SPADE);
			shortest = Suit.SPADE.getNumber();
		}

		if (findCount(Suit.CLUB) == 0 && findCount(Suit.DIAMOND) == 0 && findCount(Suit.HEART) != 0) {
			shortest = Suit.HEART.getNumber();
		}

		if (findCount(Suit.CLUB) == 0 && findCount(Suit.DIAMOND) == 0 && findCount(Suit.HEART) == 0 && findCount(Suit.SPADE) != 0) {
			shortest = Suit.SPADE.getNumber();
		}
	}

	public Suit getShortest() {
		return Suit.getSuit(shortest);
	}

	public Card playACard(Game game, Trick trick) {
		// play card according to winner suit
		Suit suit = trick.getWinningCard().getSuit();

		int index = 0;
		if ((trick.getCurrentSize() == 0) && (this.findCount(suit) > -1) && (index = this.findHighest(suit)) > -1) {
			index = (findHighest(suit) == -1 ? findLowest(suit) : findHighest(suit));
		} else if ((trick.getCurrentSize() == 0) && (this.findCount(suit) == 0) && (index = this.findLowest(game)) >= 0) {
		} else if ((trick.getCurrentSize() == game.PLAYERS - 1) && !trick.getHearts() && !trick.getQueen()
				&& (index = findLastHigh(trick.getWinningCard().getSuit())) >= 0) {
		} else if ((index = findHighestBelow(trick.getWinningCard())) >= 0) {
		} else if ((index = findMiddleHigh(game, trick.getWinningCard().getSuit())) >= 0) {
		} else if ((index = find(12, Suit.SPADE)) >= 0) ; // queen of Spades
		else if ((index = find(14, Suit.SPADE)) >= 0) ; // Ace of Spades
		else if ((index = find(13, Suit.SPADE)) >= 0) ; // King of Spades
		else if ((index = findHighest(Suit.HEART)) >= 0) ; // heart
		else {
			index = findHighest();
		}
		Card card = this.removeCard(index);
		trick.update(NUM, card);
		game.updateHeartsAndQueen(card);
		return card;
	}

	// Find counts of a particular suit
	private int findCount(Suit suit) {
		int count = 0;
		for (int i = 0; i < this.getCurrentSize(); i++) {
			if (this.getCard(i).getSuit().equals(suit)) {
				count++;
			}
		}
		return count;
	}

	// Find index of a particular card with number and suit
	private int find(int num, Suit suit) {
		return getCards().indexOf(new Card(num, suit));
	}

	// Find min card of a suit
	public int findLowest(Suit suit) {
		int lowestNum = Integer.MAX_VALUE;
		for (Card card : getCards()) {
			if (card.getSuit().equals(suit)) {
				lowestNum = card.getNum() < lowestNum ? card.getNum() : lowestNum;
			}
		}
		return getCards().indexOf(new Card(lowestNum, suit));
	}

	// Find max card of a suit
	private int findHighest(Suit suit) {
		int highestNum = Integer.MIN_VALUE;
		for (Card card : getCards()) {
			if (card.getSuit().equals(suit)) {
				highestNum = card.getNum() > highestNum ? card.getNum() : highestNum;
			}
		}
		return getCards().indexOf(new Card(highestNum, suit));
	}

	private int findLowest(Game game) {
		int lowestIndex = -1;
		int lowestNum = Integer.MAX_VALUE;
		if (game.getHearts() == false && findCount(Suit.HEART) == getCurrentSize())
			return lowestIndex;
		if (game.getHearts() == true) {
			for (int i = 0; i < getCurrentSize(); i++)
				if (getCard(i).getNum() < lowestNum) {
					lowestNum = getCard(i).getNum();
					lowestIndex = i;
				}
		} else {
			for (int i = 0; i < getCurrentSize(); i++) {
				if (getCard(i).getNum() < lowestNum && getCard(i).getSuit().getNumber() != 2) {
					lowestNum = getCard(i).getNum();
					lowestIndex = i;
				}
			}
		}
		return lowestIndex;
	}

	private int findLastHigh(Suit suit) {
		if (suit.getNumber() != 3) {
			return findHighest(suit);
		} else {
			int highest = -1;
			int highestNum = Integer.MIN_VALUE;
			for (int i = 0; i < this.getCurrentSize(); i++) {
				if (getCard(i).getSuit().equals(suit))
					if (getCard(i).getNum() > highestNum && getCard(i).getNum() < 12) {
						highest = i;
						highestNum = getCard(i).getNum();
					}
			}
			return highest;
		}
	}

	private int findHighestBelow(Card winningCard) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i) == null) {
				break;
			}
			if (getCard(i).getSuit() != winningCard.getSuit()) {
				continue;
			}
			if (getCard(i).getNum() < winningCard.getNum()) {
				max = Math.max(max, getCard(i).getNum());
			}
		}
		return max == Integer.MIN_VALUE ? -1 : find(max, winningCard.getSuit());
	}

	private int findMiddleHigh(Game game, Suit suit) {
		if (suit.equals(Suit.SPADE) && !game.getQueenOfSpades()) {
			return findHighestBelow(new Card(11, Suit.getSuit(3)));
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i) == null) {
				break;
			}
			if (!getCard(i).getSuit().equals(suit)) {
				continue;
			}
			max = Math.max(max, getCard(i).getNum());
		}
		return max == Integer.MIN_VALUE ? -1 : find(max, suit);
	}

	private int findHighest() {
		int index = 0;
		int max = 1;
		for (int i = 0; i < getCurrentSize(); i++) {
			if (getCard(i).getNum() > max) {
				max = getCard(i).getNum();
				index = i;
			}
		}
		return index;
	}
}
