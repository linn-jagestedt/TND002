package lab4;

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

	public Account (CurrentAccount account) 
	{
		otherAccount = account;
		balance = -account.getBalance();
		accountNumber = account.getAccountNumber();
		customer = account.getCustomer();
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

	public Account getOtherAccount() {
		return otherAccount == this ? null : otherAccount;
	}

	public void annualChange() 
	{
		if (this instanceof CurrentAccount) 
		{
			balance -= 10;
			if (balance < 0) {
				bank.getLoan((CurrentAccount)this);
				balance = 0;
			}
		} else if (this instanceof SavingsAccount) {
			balance *= 1.01;
		} else if (this instanceof Loan) {
			balance *= 1.05;
		}

	}

	public String toString() 
	{
		String result = "";

		if (this instanceof CurrentAccount) {
			result += "Current Account "; 
		} else if (this instanceof SavingsAccount) {
			result += "Savings Account ";
		} else if (this instanceof Loan) {
			result += "Loan ";
		}

		result += "with account number " + accountNumber + ": " + balance + "\n";

		for (int i = 0; i < transactions.size(); i++) {
            result += transactions.get(i) + "\n";
        }

		return result;
	};
}

