package assignment4;

/**
 * This document includes Question 1, 5 and Extra credit
 * Question 2-4 will be in separate java documents.
 *
 * Created by alexis on 9/29/17.
 */
public class Solution4 {

	// Question 1
	public String formatALicenseKey(String s, int k) {
		String ls = "";
		String ss = removeDash(s).toUpperCase();
		char[] sArr = ss.toCharArray();

		if (s != null && s.length() <= 12000 && k > 0) {

			if (ss.length() % k == 0) {
				for (int i = 0; i < sArr.length; i++) {
					if (i % k == 0 && i != 0) {
						ls += "-";
					}
					ls += sArr[i];
				}
			} else if (ss.length() % k > 0){
				for (int i = 0; i < sArr.length; i++) {
					int m = ss.length() % k;
					if (i == m ) {
						ls += '-';
					}
					if (i > m && (i - m) % k == 0 && i != 0) {
						ls += '-';
					}
					ls += sArr[i];
				}
			}
		}
		return ls;
	}

	public String removeDash(String s) {
		String temp = "";
		char[] sArr = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			if (sArr[i] != '-') {
				temp += sArr[i];
			}
		}
		return temp;
	}

	// Question5
	public String intToRoman(int num) {

		String s = "";
		if (num >=1 && num <= 3999) {
			String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
			int[] n = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
			for (int i = 0; i < n.length; i++) {
				while (num > 0 && num / n[i] > 0) {
					s += roman[i];
					num -= n[i];
				}
			}
		}
		return s;
	}

	// Extra Credit
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {

		if ((nums1.length + nums2.length) % 2 == 0) {
			int k = (nums1.length + nums2.length) / 2;
			double m1 = findKthNumber(nums1, nums2, k);
			double m2 = findKthNumber(nums1, nums2, (k + 1));
			return (m1 + m2) / 2;
		} else {
			int k =(nums1.length + nums2.length + 1) / 2;
			double m = findKthNumber(nums1, nums2, k);
			return m;
		}
	}

	public int findKthNumber(int[] nums1, int[] nums2, int k) {
		int i = 0;
		int j = 0;
		while ((i + j) < (k - 1) && i < nums1.length && j < nums2.length) {
			if (nums1[i] <= nums2[j]) {
				i++;
			} else {
				j++;
			}
		}
		if (i == nums1.length) {
			return nums2[k-i-1];
		} else if (j == nums2.length) {
			return nums1[k-j-1];
		} else if (nums1[i] < nums2[j]){
			return nums1[i];
		} else {
			return nums2[j];
		}
	}

	public static void main(String[] args) {

		Solution4 test = new Solution4();

		// Test Question 1
		String s = "2-4a0r7-4k";
		int k = 3;
		System.out.println(test.formatALicenseKey(s, k));

		// Test Question 5
		System.out.println(test.intToRoman(155));

		// Test Extra Credit
		int[] nums1 = {1, 4, 7, 10, 11};
		int[] nums2 = {2, 3, 9, 17, 19, 24, 31};
		double median = test.findMedianSortedArrays(nums1, nums2);
		System.out.println(median);
	}
}
