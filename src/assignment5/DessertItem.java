package assignment5;
/**
 * Created by alexis on 10/9/17.
 */

public abstract class DessertItem {
	protected String name;

	public DessertItem() {
	}

	public DessertItem(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract int getCost();
}






