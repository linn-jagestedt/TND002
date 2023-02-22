package lab3;

import java.util.ArrayList;

public class Bank 
{
    public final String Name;

    private ArrayList<Account> accounts;

    public Bank(String name) {
        Name = name;
        accounts = new ArrayList<>();
    }

    public CurrentAccount searchAccount(String customer) 
    {
        for (int i = 0; i < accounts.size(); i++) 
        {
            Account account = accounts.get(i);
            if (account.getCustomer() == customer && account instanceof CurrentAccount) {
                return (CurrentAccount)account;
            }
        }

        return null;
    }

    public String createAccount(String customer, double balance) 
    {
        if (searchAccount(customer) != null) {
            return "Accounts already exist for: " + customer;
        }

        accounts.add(new CurrentAccount(customer, balance));
        return "Current account created for: " + customer;
    }

    public String createAccount(String customer, double balance, double otherBalance) 
    {
        if (searchAccount(customer) != null) {
            return "Accounts already exist for: " + customer;
        }

        CurrentAccount newAccount = new CurrentAccount(customer, balance, otherBalance);
        accounts.add(newAccount);
        accounts.add(newAccount.getOtherAccount());

        return "Current and savings account created for: " + customer;
    }

    public void currentToSavings(String customer, double amount) 
    {
        CurrentAccount current = searchAccount(customer);

        if (current == null) {
            return;
        }

        current.savings(amount);
    }

    public String checkPerson(String customer) 
    {
        CurrentAccount current = searchAccount(customer);

        if (current == null) {
            return "Customer does not exist";
        }

        String result = current.getCustomer() + "\n\n";
        result += current.toString();
        
        Account otherAccount = current.getOtherAccount();
        
        if (otherAccount != null) {
            result += "\n" + otherAccount.toString();
        } 

        return result;
    }

    public String toString() 
    {
        String result = "Bank: " + Name + "\n"; 

        result += "Accounts: " + accounts.size() + "\n";

        double current = 0;
        double savings = 0;

        for (int i = 0; i < accounts.size(); i++) 
        {
            Account account = accounts.get(i);
            
            if (account instanceof SavingsAccount) {
                savings += account.getBalance();
            } else if (account instanceof CurrentAccount) {
                current += account.getBalance();
            }
        }

        result += "Money in current / savings accounts: " + current + " / " +  savings + "\n";

        return result; 
    };
}
