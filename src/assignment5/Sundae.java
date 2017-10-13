package assignment5;

/**
 * Created by alexis on 10/9/17.
 */
public class Sundae extends IceCream {
	private String toppingName;
	private int toppingCost;

	public Sundae(String name, int cost, String toppingName, int toppingCost) {
		super(name, cost);
		this.toppingName = toppingName;
		this.toppingCost = toppingCost;
	}

	@Override
	public int getCost() {
		return super.getCost() + toppingCost;
	}

	public String getToppingName() {
		return toppingName;
	}

	public void setToppingName(String toppingName) {
		this.toppingName = toppingName;
	}

	public int getToppingCost() {
		return toppingCost;
	}

	public void setToppingCost(int toppingCost) {
		this.toppingCost = toppingCost;
	}
}
