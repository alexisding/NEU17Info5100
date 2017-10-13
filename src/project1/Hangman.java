package project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by alexis on 10/5/17.
 */
public class Hangman {

	public static final int MAX_WRONG_GUESS = 8;

	private List<String> words;
	private List<Character> correctList;
	private List<Character> wrongList;
	private int maxWrongGuess;
	private String guessesSoFar;

	public Hangman(List<String> words) {
		this.words = words;
		correctList = new ArrayList<Character>();
		wrongList = new ArrayList<Character>();
		maxWrongGuess = MAX_WRONG_GUESS;
		guessesSoFar = "";
	}

	public String chooseWord(List<String> words) {
		Random random = new Random();
		return words.get(random.nextInt(words.size()));
	}

	public StringBuilder displayDash(String word) {
		StringBuilder dash = new StringBuilder(word.length());
		for(int i = 0; i<word.length();i++) {
			dash.append("-");
		}
		return dash;
	}

	public void displayWord(String word, StringBuilder dash, char letter) {
		for (int i = 0; i < word.length(); i++) {
			if (word.charAt(i) == letter) {
				dash.setCharAt(i, letter);
			}
		}
		System.out.println();
	}

	public void handleGuess(String word, char letter) {
		if (word.indexOf(letter) >= 0) {
			correctList.add(letter);
		} else {
			wrongList.add(letter);
			maxWrongGuess--;
		}
		guessesSoFar += letter;
	}

	public boolean gameWon(String word, StringBuilder dash) {
		return word.equals(dash.toString());
	}

	public void gameOver() {
		System.exit(1);
	}

	public void playGame() {
		System.out.println("Welcome to Hangman game!");
	}

	public void printHangman() {
		System.out.println("---------------");
		System.out.println("|             |");

		if (wrongList.size() == 1) {
			System.out.println("|             O");
		}
		if (wrongList.size() == 2) {
			System.out.println("|             O");
			System.out.println("|             |");
		}
		if (wrongList.size() == 3) {
			System.out.println("|             O");
			System.out.println("|             |");
			System.out.println("|          --- ");
		}
		if (wrongList.size() == 4) {
			System.out.println("|             O");
			System.out.println("|             |");
			System.out.println("|          --- ---");
		}
		if (wrongList.size() == 5) {
			System.out.println("|             O");
			System.out.println("|             |");
			System.out.println("|          --- ---");
			System.out.println("|            /");
			System.out.println("|           /");
		}
		if (wrongList.size() == 6) {
			System.out.println("|             O");
			System.out.println("|             |");
			System.out.println("|          --- ---");
			System.out.println("|            / \\");
			System.out.println("|           /   \\");
		}
		if (wrongList.size() == 7) {
			System.out.println("|             O");
			System.out.println("|             |");
			System.out.println("|          --- ---");
			System.out.println("|            / \\");
			System.out.println("|           /   \\");
			System.out.println("|         --");
		}
		if (wrongList.size() == 8) {
			System.out.println("|             O");
			System.out.println("|             |");
			System.out.println("|          --- ---");
			System.out.println("|            / \\");
			System.out.println("|           /   \\");
			System.out.println("|         --     --");
			System.out.println("--------------------");
			System.out.println("YOU FAILED! GAME OVER!");
		}
	}

	public List<Character> getCorrectList() {
		return correctList;
	}

	public List<Character> getWrongList() {
		return wrongList;
	}

	public String getGuessesSoFar() {
		return guessesSoFar;
	}

	public int getMaxWrongGuess() {
		return maxWrongGuess;
	}

	public static void main(String[] args) {

		List<String> words = new ArrayList<String>();
		words.add("shrimp");
		words.add("crab");
		words.add("oyster");
		words.add("clam");
		words.add("salmon");

		Hangman hm = new Hangman(words);
		// welcome the user
		hm.playGame();
		System.out.println();

		// select the secret word
		String word = hm.chooseWord(words);

		// display the word with dashes
		StringBuilder dash = hm.displayDash(word);

		Scanner sc = new Scanner(System.in);

		while(!hm.gameWon(word, dash) && hm.getMaxWrongGuess() > 0) {

			System.out.println("Here is your secret word: " + dash);
			System.out.println("Your guesses so far: " + hm.getGuessesSoFar());
			// ask the user to guess a letter
			System.out.print("Please enter a letter: ");

			String input = sc.nextLine();

			if(input.length() == 1) {
				char letter = input.charAt(0);
				// to see if the letter is in the word
				hm.handleGuess(word, letter);

				if (hm.getCorrectList().contains(letter)) {
					System.out.println("\n");
					System.out.println("CORRECT! Good guess!");
					// update the word and display previously guessed letters
					hm.displayWord(word, dash, letter);

					if (hm.gameWon(word, dash)) {
						System.out.println("Congratulations! YOU WON!");
					}
				} else {
					System.out.println("\n");
					System.out.println("Sorry, WRONG letter! You only have " + hm.getMaxWrongGuess() + " retries left!");
					// print hangman if guessed wrong
					hm.printHangman();
					System.out.println("\n");
				}
			} else {
				System.out.println("Please enter one letter at a time!");
			}
		}

		System.out.println("Your secret word is : " + word);
		// close scanner
		sc.close();
		// exit game
		hm.gameOver();
	}
}
