package assignment6;

/**
 * Created by alexis on 10/16/17.
 */
public class MyIndexOutOfBoundException extends Exception{

	private int lowerBound;
	private int upperBound;
	private int index;

	public MyIndexOutOfBoundException(String message, int lowerBound, int upperBound, int index) {
		super(message);
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.index = index;
	}

	@Override
	public String toString() {
		String errorMessage = "Error Message: Index: " + index + ", but Lower bound: " + lowerBound + ", Upper bound: " + upperBound;
		return errorMessage;
	}

	public static void main(String[] args) {
		int[] arr = new int[10];
		int index = 10;
		if (index > arr.length - 1) {
			try {
				throw new MyIndexOutOfBoundException("", 0, arr.length - 1, index);
			} catch (MyIndexOutOfBoundException e) {
				e.printStackTrace();
			}
		}
	}
}
