package lab4;

import java.util.ArrayList;

public class Bank 
{
    public String Name;

    private ArrayList<Account> accounts;
    private ArrayList<Loan> loans;

    public Bank(String name) {
        Name = name;
        accounts = new ArrayList<>();
        loans = new ArrayList<>();
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
        
        Account.bank = this;
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

        Account.bank = this;
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

    public void transfer(String customerA, String customerB, double amount) {
        CurrentAccount accountA = searchAccount(customerA);
        CurrentAccount accountB = searchAccount(customerB);

        if (accountA == null || accountB == null) {
            return;
        }

        accountA.send(amount, accountB);
    }

    public void getLoan(CurrentAccount account) {
        loans.add(new Loan(account));
    }
    
    public void cashPayment(String customer, double amount) 
    {
        for (int i = 0; i < loans.size(); i++) {
            Loan loan = loans.get(i);
            if (loan.getCustomer() == customer) {
                double result = loan.payOff(amount);
                if (result > 0) {
                    amount = result;
                } else {
                    return;
                }
            }
        }

        CurrentAccount account = searchAccount(customer);
        account.receive("Cash payment", amount);
    }

    public void computeAnnualChange() {
        for (int i = 0; i < accounts.size(); i++) {
            accounts.get(i).annualChange();
        }
    }

    public String toString() 
    {
        String result = "Bank: " + Name + "\n"; 

        result += "Accounts: " + accounts.size() + "\n";
        result += "Loans: " + loans.size() + "\n";

        double current = 0;
        double savings = 0;
        double debt = 0;

        for (int i = 0; i < accounts.size(); i++) 
        {
            Account account = accounts.get(i);
            
            if (account instanceof SavingsAccount) {
                savings += account.getBalance();
            } else if (account instanceof CurrentAccount) {
                current += account.getBalance();
            } else if (account instanceof Loan) {
                debt += account.getBalance();
            }
        }

        result += "Money in current / savings accounts / debt: " + current + " / " +  savings + " / " + debt + "\n";

        return result; 
    };
}
