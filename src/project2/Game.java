package project2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis on 10/30/17.
 */
public class Game {

	public static int PLAYERS;
	private Deck deck;
	private Hand[] hands;
	private Trick[] tricks;
	private int numberOfTricks = 0;
	private boolean hearts = false;
	private boolean queenOfSpades = false;

	public Game(int numOfPlayers) {
		super();
		deck = new Deck();
		PLAYERS = numOfPlayers;
		hands = new Hand[numOfPlayers];
		for (int i = 0; i < hands.length; i++) {
			hands[i] = new Hand(i, deck.TOTAL_CARDS / PLAYERS);
		}
		tricks = new Trick[deck.TOTAL_CARDS / PLAYERS];
		numberOfTricks = 0;
	}

	public void initializeCards(Deck deck) {
		for (int j = 2; j <= 14; j++) {
			Card card = new Card(j, Suit.CLUB);
			deck.addCard(card);
		}

		for (int j = 2; j <= 14; j++) {
			Card card = new Card(j, Suit.DIAMOND);
			deck.addCard(card);
		}

		for (int j = 2; j <= 14; j++) {
			Card card = new Card(j, Suit.HEART);
			deck.addCard(card);
		}

		for (int j = 2; j <= 14; j++) {
			Card card = new Card(j, Suit.SPADE);
			deck.addCard(card);
		}
	}

	public void playAGame() {
		deck = new Deck();
		initializeCards(deck);

		int cardsLeft = deck.TOTAL_CARDS % PLAYERS;

		deck.shuffle();

		for (int i = 0; i < tricks.length; i++) {
			for (int j = 0; j < PLAYERS; j++) {
				hands[j].addCard(deck.dealCard());
			}
		}

		// Get undelt cards
		List<Card> UndeltCards = new ArrayList<>();
		while (deck.getCards().size() > 0) {
			UndeltCards.add(deck.dealCard());
		}

		int playerID = 0;
		int temp = 15;
		for (int i = 0; i < PLAYERS; i++) {
			hands[i].setShortest();
			hands[i].sort();
			System.out.println("        Player " + i + " shortest = " + hands[i].getShortest());
			hands[i].display();
			if (hands[i].getCard(tricks.length - 1).getSuit().getNumber() == 0 && hands[i].getCard(tricks.length - 1).getNum() < temp) {
				temp = hands[i].getCard(tricks.length - 1).getNum();
				playerID = i;
			}
			System.out.println();
		}
		// Print out undelt cards
		System.out.println("undelt cards: ");
		UndeltCards.forEach(card -> card.display());
		System.out.println();

		for (int i = 0; i < tricks.length; i++) {
			tricks[i] = new Trick(PLAYERS);
			numberOfTricks++;
			Card card;
			// first trick
			if (i == 0) {
				int index = hands[playerID].getCurrentSize() - 1;
				card = hands[playerID].removeCard(index);
				// record trick i winning card
				tricks[i].update(playerID, card);

			} else {
				// get last winner playerID
				playerID = tricks[i - 1].getWinner();
				// update last winner player shortest suit
				hands[playerID].setShortest();
				// get shortest suit
				Suit suit = hands[playerID].getShortest();
				// get lowest in shortest suit
				int index = hands[playerID].findLowest(suit);
				// get card from index
				card = hands[playerID].getCards().get(index);
				tricks[i].update(playerID, card);
				card = hands[playerID].playACard(this, tricks[i]);
			}
			// add card to tricks
			tricks[i].addCard(card);
			System.out.print("player " + playerID + "      ");
			card.display();

			for (int j = 1; j < PLAYERS; j++) {
				playerID = (playerID + 1) % PLAYERS;
				card = hands[playerID].playACard(this, tricks[i]);
				// add card to tricks
				tricks[i].addCard(card);
				System.out.print("player " + playerID + "      ");
				card.display();
			}
			playerID = tricks[i].getWinner();
			System.out.println("Winner player in round " + i + " = " + playerID);
			System.out.println();
		}

		// Compute scores for each player
		for (int i = 0; i < PLAYERS; i++) {
			System.out.println("players " + i + "  score = " + computePoints(i));
		}
	}

	public void updateHeartsAndQueen(Card card) {
		if (card.getSuit().equals(Suit.HEART) && hearts == false) {
			System.out.println("Hearts is now broken.");
			hearts = true;
		}
		if (card.getSuit().equals(Suit.SPADE) && card.getNum() == 12) {
			queenOfSpades = true;
		}
	}

	// Calculate scores
	private int computePoints(int playerID) {
		int point = 0;

		for (int i = 0; i < numberOfTricks; i++) {
			// if the player wins the trick
			if (tricks[i].getWinner() == playerID) {
				for (int j = 0; j < tricks[i].getCurrentSize(); j++) {
					if (tricks[i].getCard(j).getSuit().equals(Suit.HEART)) {
						point += 1;
					}
					if (tricks[i].getCard(j).getSuit().equals(Suit.SPADE) && tricks[i].getCard(j).getNum() == 12) {
						point += 13;
					}
				}
			}
		}
		return point;
	}

	public boolean getHearts() {
		return hearts;
	}

	public boolean getQueenOfSpades() {
		return queenOfSpades;
	}

	public int getNumberOfTricks() {
		return numberOfTricks;
	}
}
