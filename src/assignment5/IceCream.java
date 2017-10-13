package assignment5;

/**
 * Created by alexis on 10/9/17.
 */

public class IceCream extends DessertItem {
	private int cost;

	public IceCream(String name, int cost) {
		super(name);
		this.cost = cost;
	}

	@Override
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
