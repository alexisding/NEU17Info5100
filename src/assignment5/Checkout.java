package assignment5;

import java.util.Vector;

/**
 * Created by alexis on 10/9/17.
 */
public class Checkout {

	private Vector<DessertItem> dessertItems;

	public Checkout() {
		dessertItems = new Vector<DessertItem>();
	}

	public Vector<DessertItem> getDessertItems() {
		return dessertItems;
	}

	public void setDessertItems(Vector<DessertItem> dessertItems) {
		this.dessertItems = dessertItems;
	}

	public int numberOfItems() {
		return dessertItems.size();
	}

	public void enterItem(DessertItem item) {
		dessertItems.add(item);
	}

	public void clear() {
		dessertItems.clear();
	}

	public int totalCost() {
		int total = 0;
		for (DessertItem item : dessertItems) {
			total += item.getCost();
		}
		return total;
	}

	public int totalTax() {
		return (int) Math.round(totalCost() * DessertShoppe.getTaxRate());
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		// print store name
		int s = (DessertShoppe.WIDTH - DessertShoppe.getStoreName().length()) / 2;
		sb.append('\n' + String.format("%" + (s + DessertShoppe.getStoreName().length()) + "s%n", DessertShoppe.getStoreName()));

		// print dash
		StringBuilder dash = new StringBuilder();
		for (int i = 0; i < DessertShoppe.getStoreName().length(); i++) {
			dash.append('-');
		}
		sb.append(String.format("%" + (s + DessertShoppe.getStoreName().length()) + "s%n", dash.toString()));
		sb.append('\n');

		// print items with costs
		for (DessertItem item : dessertItems) {
			String cost = DessertShoppe.centsToDollarsAndCents(item.getCost());

			if (item instanceof Sundae) {
				sb.append(((Sundae) item).getToppingName() + " Sundae with ");
				sb.append('\n' +item.name + String.format("%" + (DessertShoppe.WIDTH - item.name.length()) + "s%n", cost));

			} else if (item instanceof Candy) {
				String pricePerPound = DessertShoppe.centsToDollarsAndCents((int)((Candy) item).getPricePerPound());
				String weight = String.format("%.2f", ((Candy) item).getWeight()); // make weight precised to 0.00
				sb.append(weight + " lbs. @ " + pricePerPound + " /lb");
				sb.append('\n' + item.name + String.format("%" + (DessertShoppe.WIDTH - item.name.length()) + "s%n", cost));

			} else if (item instanceof Cookie) {
				String pricePerDozen = DessertShoppe.centsToDollarsAndCents((int)((Cookie) item).getPricePerDozen());
				sb.append(((Cookie) item).getNumber() + " @ " + pricePerDozen + " /dz");
				sb.append('\n' + item.name + String.format("%" + (DessertShoppe.WIDTH - item.name.length()) + "s%n", cost));

			} else if (item instanceof IceCream) {
				sb.append(item.name + String.format("%" + (DessertShoppe.WIDTH - item.name.length()) + "s%n", cost));
			}
		}
		sb.append('\n');

		// print tax and total cost
		String tax = DessertShoppe.centsToDollarsAndCents(totalTax());
		String totalCost = DessertShoppe.centsToDollarsAndCents(totalCost() + totalTax());
		String t = "Tax";
		String c = "Total Cost";
		sb.append("Tax" + String.format("%" + (DessertShoppe.WIDTH - t.length()) + "s%n", tax));
		sb.append("Total Cost" + String.format("%" + (DessertShoppe.WIDTH - c.length()) + "s%n", totalCost));

		return sb.toString();
	}
}
