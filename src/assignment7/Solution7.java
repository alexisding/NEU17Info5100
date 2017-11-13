package assignment7;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis on 11/8/17.
 */
public class Solution7 {

	// Question 4
	public void printPascalTriangle(int n) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();

		if (n <= 0) {
			System.out.println(result);
		} else {

			List<Integer> previousRow = new ArrayList<Integer>();
			previousRow.add(1);
			result.add(previousRow);

			for (int i = 1; i < n; i++) {
				List<Integer> currentRow = new ArrayList<Integer>();
				currentRow.add(1);

				for (int j = 0; j < previousRow.size() - 1; j++) {
					currentRow.add(previousRow.get(j) + previousRow.get(j + 1));
				}

				currentRow.add(1);
				result.add(currentRow);
				previousRow = currentRow;
			}

			for (int k = 0; k < result.size(); k++) {
				System.out.println(result.get(k));
			}
		}
	}

	// Extra Credit
	public boolean findPartition(int[] arr) {
		if (arr == null || arr.length == 0) {
			return false;
		}

		int sum = 0;
		for (int n : arr) {
			sum += n;
		}

		if (sum % 2 != 0) {
			return false;
		}

		boolean[] dp = new boolean[sum + 1];
		dp[0] = true;

		for (int i = 0; i < arr.length; i++) {
			for (int j = sum; j >= arr[i]; j--) {
				dp[j] = dp[j] || dp[j - arr[i]];
			}
		}
		return dp[sum];
	}


	public static void main(String[] args) {
		Solution7 test = new Solution7();

		// test question 4
		test.printPascalTriangle(6);

		System.out.println();

		// test question 5
		int[] arr1 = {1, 5, 5, 11};
		int[] arr2 = {1, 2, 3, 5};
		System.out.println(test.findPartition(arr1));
		System.out.println(test.findPartition(arr2));
	}
}
