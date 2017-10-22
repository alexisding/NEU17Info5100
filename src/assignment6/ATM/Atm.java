package assignment6.ATM;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexis on 10/17/17.
 */
public class Atm {
	public static final int TRANSACTION_FEE = 5;
	public static final int INIT_ATM_AMOUNT = 1000;

	private double availableAmountInMachine;
	private double transactionFee;
	private Map<User, Account> userData;

	public Atm(double availableAmountInMachine, double transactionFee) {
		this.availableAmountInMachine = availableAmountInMachine;
		this.transactionFee = transactionFee;
		this.userData = new HashMap<User, Account>();
	}

	public User login(String bankAccountNumberForLogin, String passwordForLogin) {
		User userLogin = null;
		for(Map.Entry<User, Account> entry : userData.entrySet()){
			if (bankAccountNumberForLogin.equalsIgnoreCase(entry.getValue().getBankAccountNumber())
					&& passwordForLogin.equalsIgnoreCase(entry.getValue().getPassword())) {
				userLogin = entry.getKey();
			}
		}
		return userLogin;
	}

	public void signUp(User user, Account bankAccount) {
		addUserData(user, bankAccount);
	}

	public void addUserData(User user, Account account) {
		userData.put(user, account);
	}

	public String retrievePassword(String name, int age, String phoneNumber) {
		String retrievedPwd = null;
		for(Map.Entry<User, Account> entry : userData.entrySet()){
			if (name.equalsIgnoreCase(entry.getKey().getName())
					&& age == entry.getKey().getAge() && phoneNumber.equalsIgnoreCase(entry.getKey().getPhoneNumber())) {
				retrievedPwd = entry.getValue().getPassword();
			}
		}
		return retrievedPwd;
	}

	// check if ATM has enough amount of balance
	public boolean ifHaveEnoughAmount(double amount) {
		double amountLeft = availableAmountInMachine - amount + transactionFee;
		return amountLeft > 0;
	}

	// update the available amount in ATM
	public double updateAvailableAmountInMachine(Operation operation, double amount) {
		// update the available balance in ATM accordingly
		switch (operation) {
			case WITHDRAW:
				availableAmountInMachine -= (amount - transactionFee);
				break;
			case DEPOSIT:
				availableAmountInMachine += (amount + transactionFee);
				break;
		}
		return availableAmountInMachine;
	}

	// Initialize the user data from the file
	public void init() throws Exception {

		String file = "src/assignment6/ATM/userData.txt";
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] users = line.split(",");
				String userName = users[0];
				int age = Integer.valueOf(users[1]);
				String address = users[2];
				String phone = users[3];
				String accountNumber = users[4];
				String password = users[5];
				Account newAccount = new Account(accountNumber, password);
				User newUser = new User(userName, age, address, phone, newAccount);
				addUserData(newUser, newAccount);
			}
		}
	}

	public double getAvailableAmountInMachine() {
		return availableAmountInMachine;
	}

	public void setAvailableAmountInMachine(double availableAmountInMachine) {
		this.availableAmountInMachine = availableAmountInMachine;
	}

	public double getTransactionFee() {
		return transactionFee;
	}

	public void setTransactionFee(double transactionFee) {
		this.transactionFee = transactionFee;
	}

	public Map<User, Account> getUserData() {
		return userData;
	}

	public void exit() {
		System.exit(1);
	}
}
