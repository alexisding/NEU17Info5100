package assignment5;

/**
 * Created by alexis on 10/9/17.
 */

public class Candy extends DessertItem {
	private double weight;
	private double pricePerPound;
	private int cost;

	public Candy(String name, double weight, double pricePerPound) {
		super(name);
		this.weight = weight;
		this.pricePerPound = pricePerPound;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getPricePerPound() {
		return pricePerPound;
	}

	public void setPricePerPound(double pricePerPound) {
		this.pricePerPound = pricePerPound;
	}

	@Override
	public int getCost() {
		return (int) Math.round(weight * pricePerPound);
	}

	public void setCost(int cost) {
		this.cost = cost;
	}
}
