/* Good Work
 * score 10 + extra credit 2; total score 10
 */
package assignment3;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by alexis on 9/24/17.
 */
public class Solution3 {

	/*
	Question 1
	Find the error in the following code and explain in few lines why it is wrong. (Score 1)
	Here is the code.
	*/
	public class Book { // score 1

		int size;
		int price;
		String name;

		public Book(int size) {
			this.size = size;
		}

		public Book(int size, int price, String name) {
			// there is no parent class for it // parent class is Object class
			super();
			this.size = size;
			this.price = price;
			this.name = name;
		}

		/*
		there is already a constructor with only one parameter which is an int type, could change int to double:
		public Book(double price) {
			this.price = price;
		}
		 */
		public Book(int price) {
			this.price = price;
		}

		/*
		there is no return type in this function, should be :
		public String setName(String name){
			return name;
		}
		If it's a setter method, it should not return anything and mark "void" on it, would be:
		public void setName(String name) {

		}
		*/
		public setName(String name) {
			return name;
		}
	}

	/*
	Question 2
	Find the error in the following code and explain in few lines why it is wrong. (Score 1)
	Here is the code.
	 */
	class Clock { // score 1
		String time;

		// If it is a getter method, it should have a return type String instead of void;
		// If the result type is void, it cannot return a value;
		void getTime() {
			return time;
		}

		void setTime(String t) {
			time = t;
		}
	}

	/*
	Question 3
	Write a Java function to remove vowels in a string. (Score 2)
		i. The function should take a string as input.
		ii. Should return the input string after omitting the vowels.
	Here is the prototype you can work with
	*/
	public String removeVowelsFromString(String input) { // score 2

		StringBuilder sb = new StringBuilder();

		if (input != null) {

			HashSet<Character> vowels = new HashSet<Character>();

			vowels.add('a');
			vowels.add('e');
			vowels.add('i');
			vowels.add('o');
			vowels.add('u');
			vowels.add('A');
			vowels.add('E');
			vowels.add('I');
			vowels.add('O');
			vowels.add('U');

			for (char c : input.toCharArray()) {

				if (!vowels.contains(c)) {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	/*
	Question 4
	Write a java function to check if two strings are Anagrams or not. (Score 2)
		i. The function should take two input strings.
		ii. Should return a boolean ‘true’ if the inputs are Anagrams else return ‘false’.
	Here is the prototype you can work with
	*/

	public boolean checkIfTwoStringsAreAnagrams(String s1, String s2) { // score 2

		if (s1 == s2 || s1.length() != s2.length() || s1 == null || s2 == null) {
			return false;
		}

		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();

		Arrays.sort(c1);
		Arrays.sort(c2);

		if (Arrays.equals(c1,c2)) {
			return true;
		} else {
			return false;
		}
	}

	/*
	Question 5
	5. Create a calculator that can perform the following features. (Total Score 4)
		i. The calculator should be able to perform Addition, subtraction, multiplication, division. (Score 2)
		ii. Should be able to perform squareRoot, square, cube. (Score 1)
		iii. Should be able to convert ‘Fahrenheit-Celsius’ , ‘Feet-Inches’. (Score 1)
	*/

	static class Calculator { // score 4 + extra credit 2

		// function i
		public double add(double a, double b) {
			return a + b;
		}

		public double subtract(double a, double b) {
			return a - b;
		}

		public double multiply(double a, double b) {
			return a * b;
		}

		public double divide(double a, double b) {
			if (b != 0) {
				return a / b;
			} else {
				throw new IllegalArgumentException("The divisor cannot be zero!");
			}
		}

		// function ii
		public double squareRoot(double a) {
			if (a >= 0) {
				return Math.sqrt(a);
			} else {
				throw new IllegalArgumentException("Only positive real numbers can be calculated!");
			}
		}

		public double square(double a) {
			return a * a;
		}

		public double cube(double a) {
			return a * a * a;
		}

		// function iii
		public double fahrenheitToCelsius(double f) {
			return (f - 32) * 5 / 9;
		}

		public double celsiusToFahrenheit(double c) {
			return c / 5 * 9 + 32;
		}

		public double feetToInches(double ft) {
			return ft * 12;
		}

		public double inchesToFeet(double in) {
			return in / 12;
		}

	/*
	Extra credit (Score 2)
	The calculator should be able to solve a quadratic equation and return the solution as array.
		i. This function should take three arguments.
		ii. For example, if quadratic equation is Ax^2 + Bx + C. The function should take A,B,C as arguments and return a solution as array.
	*/

		public double[] quadraticEquation(int a, int b, int c) {

			double delta = b * b - 4 * a * c;
			double[] arr;

			if (delta >= 0) {
				arr = new double[2];
				arr[0] = (-b + Math.sqrt(delta)) / (2 * a);
				arr[1] = (-b - Math.sqrt(delta)) / (2 * a);
			} else {
				arr = new double[0];
			}
			return arr;
		}

		public static void main(String[] args) {

			Solution3 test = new Solution3();

			// test Question 3
			String input = test.removeVowelsFromString("Remove all vowels from this string");
			System.out.println(input);

			// test Question 4
			System.out.println(test.checkIfTwoStringsAreAnagrams("angel Gea", "Glean age"));

			// test Question 5
			Calculator calculator = new Calculator();

			System.out.println(calculator.celsiusToFahrenheit(32));
			System.out.println(calculator.squareRoot(64));
			System.out.println(calculator.feetToInches(5.5));

			// test Extra Credit
			double[] arr = calculator.quadraticEquation(2, -2, -4);
			for (double x:arr) {
				System.out.println(x);
			}
		}
	}
}






