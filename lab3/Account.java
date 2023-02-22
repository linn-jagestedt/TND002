package lab3;

import java.util.ArrayList;

public class Account {

	private static int nextAccountNumber = 1;
	
	private String customer;
	private int accountNumber;
	private double balance;

	protected static Bank bank;
	protected Account otherAccount;
	protected ArrayList<String> transactions;

	public Account(String customer, double balance) {
		accountNumber = nextAccountNumber;
		nextAccountNumber++;

		this.customer = customer;
		this.balance = balance;

		otherAccount = this;

		transactions = new ArrayList<>();
	}

	public Account (String customer, double balance, double otherBalance) {
		accountNumber = nextAccountNumber;
		nextAccountNumber++;

		this.customer = customer;
		this.balance = balance;

		otherAccount = new SavingsAccount(customer, otherBalance);
		otherAccount.otherAccount = this;

		transactions = new ArrayList<>();
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public String getCustomer() {
		return customer;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static void setBank(Bank bank) {
		Account.bank = bank;
	}

	public SavingsAccount getOtherAccount() 
	{
		if (otherAccount == this) {
			if (otherAccount instanceof SavingsAccount) {
				return (SavingsAccount)otherAccount;
			}
		}

		return null;
	}

	public String toString() 
	{
		String result = "";

		if (this instanceof CurrentAccount) {
			result += "Current Account "; 
		} else if (this instanceof SavingsAccount) {
			result += "Savings Account ";
		}

		result += "with account number " + accountNumber + ": " + balance + "\n";

		for (int i = 0; i < transactions.size(); i++) {
            result += transactions.get(i) + "\n";
        }

		return result;
	};
}

