package assignment6.ATM;

import assignment6.ATM.Account;
import assignment6.ATM.Operation;

import java.util.*;

/**
 * Created by alexis on 10/17/17.
 */
public class User {
	private String name;
	private int age;
	private String address;
	private String phoneNumber;
	private Account bankAccount;

	public User(String name, int age, String address, String phoneNumber, Account bankAccount) {
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.bankAccount = bankAccount;
	}

	public double availableBalance() {
		return bankAccount.getCurrentBalance();
	}

	public double withdraw(double withdraw, double transactionFree) {
		double currentBalance = bankAccount.getCurrentBalance() - withdraw - transactionFree;
		currentBalance = (currentBalance > 0) ? currentBalance : 0;
		bankAccount.setCurrentBalance(currentBalance);
		return currentBalance;
	}

	public double deposit(double deposit, double transactionFree) {
		double currentBalance = bankAccount.getCurrentBalance() + deposit - transactionFree;
		bankAccount.setCurrentBalance(currentBalance);
		return currentBalance >= 0 ? currentBalance : 0;
	}

	public TreeMap<Date, Map<Operation, Double>> recentTransactions(Operation operation, double amount) {
		Map<Operation, Double> record = new HashMap<>();
		record.put(operation, amount);
		bankAccount.getTransactions().put(new Date(), record);
		return bankAccount.getTransactions();
	}

	public void changePassword(String newPassword) {
		bankAccount.setPassword(newPassword);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Account getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(Account bankAccount) {
		this.bankAccount = bankAccount;
	}
}
