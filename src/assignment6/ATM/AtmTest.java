package assignment6.ATM;

import java.util.*;

/**
 *
 * Created by alexis on 10/19/17.
 */
public class AtmTest {

	public void mainMenu() {
		System.out.println("********************");
		System.out.println("      Main Menu");
		System.out.println("1 -- Sign up");
		System.out.println("2 -- Log in");
		System.out.println("3 -- Forgot password");
		System.out.println("0 -- Exit");
		System.out.print("Please enter a number: ");
	}

	public void userMenu() {
		System.out.println("********************");
		System.out.println("      User Menu");
		System.out.println("1 -- Check current balance");
		System.out.println("2 -- Deposit");
		System.out.println("3 -- Withdraw");
		System.out.println("4 -- Check recent transactions");
		System.out.println("5 -- Change password");
		System.out.println("0 -- Return to main menu");
		System.out.print("Please enter a number: ");
	}

	public void userInfoInput() {
		System.out.println("********************");
		System.out.println("      User Info Input");
	}
	public void accountInfoInput() {
		System.out.println("********************");
		System.out.println("      Account Info Input");
	}
	public void retrievePassword() {
		System.out.println("********************");
		System.out.println("      Retrieve Password");
	}

	public static void main(String[] args) throws Exception {
		Atm atm = new Atm(Atm.INIT_ATM_AMOUNT, Atm.TRANSACTION_FEE);
		// Read initialized user data from an input file
		atm.init();

		AtmTest atmTest = new AtmTest();

		Scanner sc = new Scanner(System.in);
		// print main menu
		atmTest.mainMenu();

		int input = sc.nextInt();
		// Get into main menu
		while (input != 0) {
			switch (input) {
				// sign up
				case 1:
					atmTest.userInfoInput();
					System.out.print("Name: ");
					String name = sc.next();

					System.out.print("Age: ");
					int age = sc.nextInt();

					System.out.print("Address: ");
					String address = sc.next();

					System.out.print("phoneNumber: ");
					String phoneNumber = sc.next();

					System.out.print("BankAccount: ");
					String bankAccountNumber = sc.next();

					System.out.print("Password: ");
					String password = sc.next();

					Account newAccount = new Account(bankAccountNumber, password);
					User newUser = new User(name, age, address, phoneNumber, newAccount);

					atm.signUp(newUser, newAccount);
					System.out.println("Sign up " + newAccount.getBankAccountNumber() + " Successfully! Please login.");
					atmTest.mainMenu();
					break;
				// login
				case 2:
					atmTest.accountInfoInput();
					// input account information
					System.out.print("BankAccount: ");
					String bankAccountNumberForLogin = sc.next();

					System.out.print("Password: ");
					String passwordForLogin = sc.next();

					User userLogin = atm.login(bankAccountNumberForLogin, passwordForLogin);
					// start user menu
					if (userLogin != null) {
						System.out.println("Login successful!");

						// user menu
						atmTest.userMenu();
						int userInput = sc.nextInt();
						while (userInput != 0) {
							switch(userInput) {
								// check user current balance
								case 1:
									System.out.print("User " + userLogin.getName() + " current balance is: $"
											+ userLogin.getBankAccount().getCurrentBalance());
									System.out.println();
									atmTest.userMenu();
									break;
								// user deposit
								case 2:
									System.out.print("Deposit amount: ");
									double deposit = sc.nextDouble();
									// deposit
									userLogin.deposit(deposit, atm.getTransactionFee());
									System.out.println("User " + userLogin.getName() + " current balance is: $"
											+ userLogin.getBankAccount().getCurrentBalance());
									// update ATM available balance
									atm.updateAvailableAmountInMachine(Operation.DEPOSIT, deposit);
									// add recent transactions
									userLogin.recentTransactions(Operation.DEPOSIT, deposit);
									atmTest.userMenu();
									break;
								// user withdraw
								case 3:
									System.out.print("Withdraw amount: ");
									double withdraw = sc.nextDouble();
									// check if the ATM machine has enough money for user withdraw
									if(atm.ifHaveEnoughAmount(withdraw)) {
										// check if the user has enough balance to withdraw
										if (userLogin.getBankAccount().getCurrentBalance() < withdraw) {
											System.out.println("There is not enough money in your account!");
										} else {
											// withdraw
											userLogin.withdraw(withdraw, atm.getTransactionFee());
											// update ATM available balance
											atm.updateAvailableAmountInMachine(Operation.WITHDRAW, withdraw);
											// add recent transactions
											userLogin.recentTransactions(Operation.WITHDRAW, withdraw);
											System.out.println("User " + userLogin.getName() + " current balance is: $"
													+ userLogin.getBankAccount().getCurrentBalance());
										}
									} else {
										System.out.println("ATM machine doesn't have enough money!");
									}
									atmTest.userMenu();
									break;
								// check recent 10 transactions
								case 4:
									System.out.println("Recent transactions: ");
									System.out.println();
									TreeMap<Date, Map<Operation, Double>> transactions = userLogin.getBankAccount().getTransactions();
									// print latest 10 records
									int i = 0;
									for (Map.Entry<Date, Map<Operation, Double>> entry: transactions.descendingMap().entrySet()) {
										if (i++ < 10) {
											Map<Operation, Double> record = entry.getValue();
											for (Map.Entry<Operation, Double> r : record.entrySet()) {
												System.out.println(entry.getKey() + " " + r.getKey() + ": $" + r.getValue());
											}
										}
									}
									atmTest.userMenu();
									break;
								// change login user password
								case 5:
									System.out.print("Please input the new password: ");
									String newpwd = sc.next();
									userLogin.changePassword(newpwd);
									System.out.println("User " + userLogin.getName() + " new password is: "
											+ userLogin.getBankAccount().getPassword());
									atmTest.userMenu();
									break;
								// user exit
								case 0:
									atm.exit();
							} // end switch
							userInput = sc.nextInt();
						} // end while
					} else {
						System.out.println("Login failed!");
					}
					atmTest.mainMenu();
					break;
				// user retrieve password
				case 3:
					atmTest.retrievePassword();
					System.out.print("Name: ");
					String nameToRetrieve = sc.next();

					System.out.print("Age: ");
					int ageToRetrieve = sc.nextInt();

					System.out.print("phoneNumber: ");
					String phoneNumberToRetrieve = sc.next();

					String passwordRetrieved = atm.retrievePassword(nameToRetrieve, ageToRetrieve, phoneNumberToRetrieve);
					System.out.println("Your password is " + passwordRetrieved);
					atmTest.mainMenu();
					break;
				// user exit
				case 0:
					System.out.println("System exit!");
					atm.exit();
			} // end switch
			input = sc.nextInt();
		} // end while loop
	}
}
