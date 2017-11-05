package project2;

/**
 * Created by alexis on 10/30/17.
 */
public class Trick extends GroupOfCards{
	private int winner;
	private Card winningCard;
	private boolean hearts = false;
	private boolean queen = false;

	public Trick(int players){
		super(2 * players - 1);
	}

	public void update(int playerID, Card card) {
		if (isWinner(card)) {
			winner = playerID;
			winningCard = card;
			if (card.getSuit().equals(Suit.HEART)) {
				hearts = true;
			}
			if (card.getSuit().equals(Suit.SPADE) && card.getNum() == 12) {
				queen = true;
			}
		}
	}

	private boolean isWinner(Card card) {
		if (winningCard != null){
			if (card.getSuit() != winningCard.getSuit() || card.getNum() < winningCard.getNum()){
				return false;
			}
		}
		return true;
	}

	public int getWinner() {
		return winner;
	}

	public Card getWinningCard() {
		return winningCard;
	}

	public boolean getHearts() {
		return hearts;
	}

	public boolean getQueen() {
		return queen;
	}
}
