package assignment7;

/**
 * Question 1
 *
 * Created by alexis on 11/9/17.
 */
public class MaxValue extends Thread {
	private int[] arr;
	private int max;
	private int low;
	private int high;

	public MaxValue(int[] arr, int low, int high) {
		this.arr = arr;
		this.low = low;
		this.high = high;
	}

	public int findMax(int[] arr) {
		if (arr == null || arr.length == 0) {
			return -1;
		}

		max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			max = Integer.max(arr[i], max);
		}
		return max;
	}

	@Override
	public void run() {
		findMax(arr);
	}

	public static int max(int[] arr) throws InterruptedException {
		// 4 threads
		MaxValue[] mv = new MaxValue[4];
		for (int i = 0; i < mv.length; i++) {
			mv[i] = new MaxValue(arr, (i * arr.length) / mv.length, ((i + 1) * arr.length) / 4);
			mv[i].start();
		}

		int max = mv[0].max;
		for (int i = 0; i < mv.length; i++) {
			mv[i].join();
			max = Integer.max(max, mv[i].max);
		}
		return max;
	}

	public static void main(String[] args) throws InterruptedException {
		int[] arr = new int[100];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 100);
		}
		System.out.println("Max value = " + max(arr));
	}
}
