package assignment5;

/**
 * Created by alexis on 10/9/17.
 */
public class DessertShoppe {

	public static final double TAX_RATE = 0.065;
	public static final String STORE_NAME = "M & M Dessert Shoppe";
	public static final int MAX_ITEM_NAME = 20;
	public static final int WIDTH = 35;

	public static double getTaxRate() {
		return TAX_RATE;
	}

	public static String getStoreName() {
		return STORE_NAME;
	}

	public static int getMaxItemName() {
		return MAX_ITEM_NAME;
	}

	public static int getWIDTH() {
		return WIDTH;
	}

	public static String centsToDollarsAndCents(int cents) {
		double dollars = (double) cents / 100;
		return Double.toString(dollars);
	}
}
