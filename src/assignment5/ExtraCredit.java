package assignment5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis on 10/11/17.
 */
public class ExtraCredit {

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> spiral = new ArrayList<Integer>();

		if (matrix.length == 0 || matrix[0].length == 0) {
			return  spiral;
		}

		// set top left as (n1, m1), bottom right as (n2, m2)
		// m as rows, n as columns
		int m1 = 0;
		int m2 = matrix.length - 1;
		int n1 = 0;
		int n2 = matrix[0].length - 1;

		while (m1 <= m2 && n1 <= n2) {
			// top rows
			for (int n = n1; n <= n2; n++) {
				spiral.add(matrix[m1][n]);
			}
			// right rows
			for (int m = m1 + 1; m <= m2; m++) {
				spiral.add(matrix[m][n2]);
			}
			if (m1 < m2 && n1 < n2) {
				// bottom rows
				for (int n = n2 - 1; n > n1; n--) {
					spiral.add(matrix[m2][n]);
				}
				// left rows
				for (int m = m2; m > m1; m--) {
					spiral.add(matrix[m][n1]);
				}
			}
			m1++;
			n2--;
			m2--;
			n1++;
		}
		return spiral;
	}

	public static void main(String[] args) {
		ExtraCredit test = new ExtraCredit();

		int[][] matrix = {
				{1, 2, 3},
				{4, 5, 6},
				{7, 8, 9}
		};

		List<Integer> spiral = test.spiralOrder(matrix);
		for (int x : spiral) {
			System.out.print(x + ", ");
		}
	}
}
