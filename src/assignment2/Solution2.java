package assignment2;

import com.sun.tools.doclint.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alexis on 9/18/17.
 */
public class Solution2 {

	/*
	1. Write a java function to calculate the salary of an employee based on the following rules.

	i. function takes input of number of hours an employee worked and returns the salary.
	ii.  The first 36 hours worked are paid at a rate of 15.0, then the next 5 hours are paid at a rate of 15 * 1.5. Hours after that up to a max of 48 are paid at a rate of 15 * 2.

	Here is the prototype you can work with
	*/

	public double employeeSalary(double hours){

		double salary = 0.0;
		double rate = 15.0;
		double baseHours = 36.0;
		double addOnHours = 41.0;
		double maxHours = 48.0;
		double baseSalary = rate * baseHours;
		double addOnSalary = rate * 1.5 * 5.0;

		if (hours > 0.0 && hours <= baseHours) {
			salary = rate * hours;
			return salary;
		} else if (hours > baseHours && hours <= addOnHours) {
			salary = baseSalary + (hours - baseHours) * rate * 1.5;
			return salary;
		} else if (hours > addOnHours && hours <= maxHours) {
			salary = baseSalary + addOnSalary + (hours - addOnHours) * rate * 2.0;
			return salary;
		} else {
			return 0.0;
		}
	}

	/*
	2. Write a java function that adds all the digits of an integer until it is single digit.

	i. function takes an integer as input and returns its sum of digits.
	ii. for example input = 37, sum = 3+7 = 10, sum = 1+0 = 1. result = 1.

	Here is the prototype you can work with
	*/

	public int addDigits(int input){

		if (input <= 0) {
			return 0;
		}
		int sum = 0;
		while (input > 0 || sum > 9) {
			if (input == 0) {
				input = sum;
				sum = 0;
			}
			sum += input % 10;
			input /= 10;
		}
		return sum;
	}

	/*
	3. Write a java function to print all perfect number between 1 and n.
	i. Perfect number is a positive integer which is equal to the sum of its proper positive divisors.
	ii. For example: 6 is the first perfect number, Proper divisors of 6 are 1, 2, 3. Sum of its proper divisors = 1 + 2 + 3 = 6.

	Here is the prototype you can work with
	*/

	public void printPerfectNumbers(int n){

		if (n >= 6) {
			for (int j = 1; j < n; j++) {
				int sum = 0;
				for (int i = 1; i < j; i++) {
					if (j % i == 0) {
						sum += i;
					}
				}
				if (sum == j) {
					System.out.println(j + " is a perfect number!");
				}
			}
		} else {
			System.out.println("Error! There is no perfect number!");
		}

	}

	/*
	4. Write a java class called pizza with (Add detail as needed) :
	i. Create at least 3 attributes :pizza type , unit price and loyalty points. More attributes are welcome to have.
	ii. Create constructors . Extra-credit of 0.5 point if you write more than 1 right constructor to this class
	*/

	public static class Pizza {

		String pizzaType;
		double unitPrice;
		double loyaltyPoints;
		String pizzaTopping;

		// constructors
		public Pizza() {

		}

		public Pizza(String pizzaType) {

		}

		public Pizza(String pizzaType, double unitPrice) {
			this.pizzaType = pizzaType;
			this.unitPrice = unitPrice;
		}

		public Pizza(String pizzaType, String pizzaTopping) {

		}

		public Pizza(String pizzaType, double unitPrice, double loyaltyPoints) {

		}
	}

	/*
	5. Write a java class called customer (Add detail as needed) :
              i. Create Attributes: customer name and which pizzas customer has ordered.
              ii. Think about what kind of data structure can be used to record the pizza name and number of each kind of pizza.( Give me your thought, Extra credit of 1 point)
              iii. In main method , sum up how much money the customer spent.
	 */

	public static class Customer {

		String customerName;
		Map<Pizza, Integer> orderedPizzas;

		public Customer(String customerName, Map<Pizza, Integer> orderedPizzas) {
			this.customerName = customerName;
			this.orderedPizzas = orderedPizzas;
		}
	}

	public static void main(String[] args) {

		// continue answer question 5
		Pizza hawaiian = new Pizza("hawaiian", 3.99);
		Pizza pepperoni = new Pizza("pepperoni", 5.99);
		Pizza cheese = new Pizza("cheese", 2.99);

		// use HashMap to record pizza name and number of pizza
		HashMap<Pizza, Integer> orderedPizzas= new HashMap<>();
		orderedPizzas.put(hawaiian, 2);
		orderedPizzas.put(pepperoni, 1);
		orderedPizzas.put(cheese, 3);

		Customer customer = new Customer("Alexis", orderedPizzas);

		// sum up how much money the customer spent
		double hawaiianPrice = hawaiian.unitPrice * orderedPizzas.get(hawaiian);
		double pepperoniPrice = pepperoni.unitPrice * orderedPizzas.get(pepperoni);
		double cheesePrice = cheese.unitPrice * orderedPizzas.get(cheese);
		double totalPrice = hawaiianPrice + pepperoniPrice + cheesePrice;
		System.out.println(totalPrice);


		Solution2 solution = new Solution2();

		// test question 1
		System.out.println(solution.employeeSalary(46.0));

		// test question 2
		System.out.println(solution.addDigits(786));

		// test question 3
		solution.printPerfectNumbers(1000);

		// test question 6
		solution.printIsoscelesTriangle(8);
	}

	/*
	// EXTRA CREDIT
	6. Write a Java program that generates an isosceles right angled triangle made of asterisks.
		i. function should take input of one equal side as integer. Other than the edges the inner part of the triangle should be empty.
		ii. For example input is 6. the function should print—

	   *
	   **
	   * *
	   *  *
	   *   *
	   ******

	   Here is the prototype you can work with
	 */

	public void printIsoscelesTriangle(int n){

		if (n >= 3) {
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= i ; j++) {
					if (i > 2 && j > 1 && i != n && i != j) {
						System.out.print(" ");
					} else {
						System.out.print("*");
					}
				}
				System.out.println();
			}
		} else {
			System.out.println("Error! Can't print a triangle!");
		}
	}
}
