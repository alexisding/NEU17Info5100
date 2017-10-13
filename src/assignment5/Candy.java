package assignment5;

/**
 * Created by alexis on 10/9/17.
 */
public class Candy extends DessertItem {
	private double weight;
	private int pricePerPound;
	private int cost;

	public Candy(String name, double weight, int pricePerPound) {
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

	public int getPricePerPound() {
		return pricePerPound;
	}

	public void setPricePerPound(int pricePerPound) {
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
