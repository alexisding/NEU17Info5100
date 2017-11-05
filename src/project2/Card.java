package project2;

/**
 * Created by alexis on 10/30/17.
 */
public class Card {
	private int num;
	private Suit suit;

	public Card(int num, Suit suit) {
		this.num = num;
		this.suit = suit;
	}

	public void display() {

		switch (num) {
			case 11:
				System.out.print("Jack");
				break;
			case 12:
				System.out.print("Queen");
				break;
			case 13:
				System.out.print("King");
				break;
			case 14:
				System.out.print("Ace");
				break;
			default:
				System.out.print(num);
				break;
		}

		switch (suit) {
			case CLUB:
				System.out.print(" of clubs");
				break;
			case DIAMOND:
				System.out.print(" of diamonds");
				break;
			case HEART:
				System.out.print(" of hearts");
				break;
			case SPADE:
				System.out.print(" of spades");
				break;
		}
		System.out.println();
	}

	public int getNum() {
		return num;
	}

	public Suit getSuit() {
		return suit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		result = prime * result + suit.getNumber();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card card = (Card) obj;
		if (num != card.num)
			return false;
		if (suit.getNumber() != card.getSuit().getNumber())
			return false;
		return true;
	}
}
