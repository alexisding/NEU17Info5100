package project2;

/**
 * Created by alexis on 11/4/17.
 */
public enum Suit {
	CLUB(0), DIAMOND(1), HEART(2), SPADE(3);

	private int number;

	Suit(int number) {
		this.number = number;
	}
	public int getNumber() {
		return number;
	}

	public static Suit getSuit(int number) {
		Suit suit = null;
		switch (number) {
			case 0:
				suit = Suit.CLUB;
				break;
			case 1:
				suit = Suit.DIAMOND;
				break;
			case 2:
				suit = Suit.HEART;
				break;
			case 3:
				suit = Suit.SPADE;
				break;
			default:
				System.out.println("Number must between 0 to 3!");
		}
		return suit;
	}
}
