/* Good WOrk
 * SCore 19
 * Your setShortest method should set diamonds as shortest if they are lessthan or equal to clubs but it sets clubs as shortest
if they are equal
 */
package project2;

import java.util.Scanner;

/**
 * Created by alexis on 10/30/17.
 */
public class GameDriver {

	public static void initializeCards(Deck deck) {

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

	public static void restart() {
		// Start a new game of 5 players
		Game newGame = new Game(5);
		newGame.playAGame();

		Scanner sc = new Scanner(System.in);
		System.out.println("Play another game? (Y/N)?");
		String input = sc.nextLine();

		if (input.equalsIgnoreCase("y")) {
			restart();
		} else {
			System.exit(1);
		}
	}

	public static void main(String[] args) {
		Deck deck = new Deck();

		// Start a new game of 5 players
		Game newGame = new Game(5);
		newGame.playAGame();

		Scanner sc = new Scanner(System.in);
		System.out.println("Play another game? (Y/N)?");
		String input = sc.nextLine();

		if (input.equalsIgnoreCase("y")) {
			restart();
		} else {
			System.exit(1);
		}
	}
}
