package assignment4;

/**
 * Created by alexis on 10/4/17.
 */

// Question 2
class Tool {

	protected int strength;
	protected char type;

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public boolean fight(Tool t) {

		if (this.strength > t.strength) {
			return true;
		} else {
			return false;
		}
	}
}

// Implement class Scissors
class Scissors extends Tool {

	public Scissors(int strength) {
		this.strength = strength;
		this.type = 's';
	}

	@Override
	public boolean fight(Tool t) {
		if (t.type == 'p') {
			strength *= 2;
		}
		if (t.type == 'r') {
			strength /= 2;
		}
		return super.fight(t);
	}
}

// Implement class Paper
class Paper extends Tool {

	public Paper(int strength) {
		this.strength = strength;
		this.type = 'p';
	}

	@Override
	public boolean fight(Tool t) {
		if (t.type == 'r') {
			strength *= 2;
		}
		if (t.type == 's') {
			strength /= 2;
		}
		return super.fight(t);
	}
}

// Implement class Rock
class Rock extends Tool {

	public Rock(int strength) {
		this.strength = strength;
		this.type = 'r';
	}

	@Override
	public boolean fight(Tool t) {
		if (t.type == 's') {
			strength *= 2;
		}
		if (t.type == 'p') {
			strength /= 2;
		}
		return super.fight(t);
	}
}

class RockPaperScissorsGame {

	public static void main(String[] args) {

		Scissors s = new Scissors(5);
		Paper p = new Paper(7);
		Rock r = new Rock(15);

		System.out.println(s.fight(p) + " , " + p.fight(s));
		System.out.println(p.fight(r) + " , " + r.fight(p));
		System.out.println(r.fight(s) + " , " + s.fight(r));
	}
}