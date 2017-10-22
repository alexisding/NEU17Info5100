package assignment6.ATM;

import java.util.Map;
import java.util.TreeMap;
import java.util.Date;

/**
 * Created by alexis on 10/21/17.
 */

public class Account {

	public static final double INIT_BALANCE = 0;

	private String bankAccountNumber;
	private String password;
	private double currentBalance;
	private TreeMap<Date, Map<Operation, Double>> transactions;

	public Account(String bankAccountNumber, String password) {
		this.bankAccountNumber = bankAccountNumber;
		this.password = password;
		this.currentBalance = INIT_BALANCE;
		transactions = new TreeMap<>();
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public TreeMap<Date, Map<Operation, Double>> getTransactions() {
		return transactions;
	}
}
