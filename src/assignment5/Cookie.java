package assignment5;

/**
 * Created by alexis on 10/9/17.
 */
public class Cookie extends DessertItem {
	private int number;
	private double pricePerDozen;
	private int cost;

	public Cookie(String name, int number, double pricePerDozen) {
		super(name);
		this.number = number;
		this.pricePerDozen = pricePerDozen;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public double getPricePerDozen() {
		return pricePerDozen;
	}

	public void setPricePerDozen(int pricePerDozen) {
		this.pricePerDozen = pricePerDozen;
	}

	@Override
	public int getCost() {
		return (int) Math.round((pricePerDozen / 12.0) * number);
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
