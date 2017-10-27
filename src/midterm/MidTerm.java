package midterm;

/**
 * Created by alexis on 10/25/17.
 */
public class MidTerm {

	// Question 1
	public int[] reverseEvenIndices(int[] nums){ //score 6
		if (nums == null || nums.length == 0) {
			return nums;
		}

		int length = nums.length;
		if (length % 2 == 0) {
			for (int i = 0; i < length / 2; i++) {
				if (i % 2 == 0) {
					swap(nums, i, length - 2 - i);
				}
			}
		} else {
			for (int i = 0; i < (length + 1) / 2; i++ ) {
				if (i % 2 == 0) {
					swap(nums, i, length - 1 - i);
				}
			}
		}
		return nums;
	}

	public void swap(int[] nums, int a, int b) {
		int temp = nums[a];
		nums[a] = nums[b];
		nums[b] = temp;
	}

	// Question 2
	public int arrangeCoins(int n){ // score 7
		long nL = (long) n;
		long start = 1;
		long end = nL;
		long mid = 0;

		while (start <= end) {
			mid = (start + end) / 2;
			if (mid * (mid + 1) <= 2 * nL) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return (int) start - 1;
	}

	// Question 3
	public int minMoves(int[] nums){ // score 7
		if (nums == null || nums.length <= 1) {
			return 0;
		}

		int min = nums[0];
		int diff = 0;
		for (int n : nums) {
			min = Math.min(min, n);
		}
		for (int n : nums) {
			diff += (n - min);
		}
		return diff;
	}

	// Question 4
	public int countNumberOfPossibleWays(int m, int n, int x) { //score 10
		// assume the least faces a dice can have is 6
		if (x == 0 || x < n || n == 0 || m < 6) {
			System.out.println("Error: it's not realistic."); // you should not assume that faces is less than 6.
			return -1;
		}

		int[][] table = new int[n + 1][x + 1];
		// Table entries for only one dice
		for (int j = 1; j <= m && j <= x; j++) {
			table[1][j] = 1;
		}

		for (int i = 2; i <= n; i++) { // n as number of dices
			for (int j = 1; j <= x; j++) { // x as sum
				for (int k = 1; k <= m && k < j; k++) { // m as faces
					table[i][j] += table[i - 1][j - k];
				}
			}
		}
		return table[n][x];
	}

	public static void main(String[] args) {

		// Test Question 1
		int[] nums1 = {9, 4, 8, 7, 5, 1, 3};
		int[] nums2 = {6, 4, 1, 0, 3, 2};
		int[] nums3 = {1, 2, 3};
		MidTerm test = new MidTerm();
		int[] arr1 = test.reverseEvenIndices(nums1);
		int[] arr2 = test.reverseEvenIndices(nums2);
		int[] arr3 = test.reverseEvenIndices(nums3);
		for (int a : arr1) {
			System.out.print(a + ", ");
		}
		System.out.println();
		for (int a : arr2) {
			System.out.print(a + ", ");
		}
		System.out.println();
		for (int a : arr3) {
			System.out.print(a + ", ");
		}
		System.out.println('\n');

		// Test Question 2
		System.out.println(test.arrangeCoins(8));
		System.out.println();

		// Test Question 3
		int[] nums = {1, 2, 3};
		System.out.println(test.minMoves(nums));
		System.out.println();

		// Test Question 4
		System.out.println(test.countNumberOfPossibleWays(6, 3, 9));
	}
}
