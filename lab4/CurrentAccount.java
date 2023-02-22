package lab4;

public class CurrentAccount extends Account 
{
    public CurrentAccount(String customer, double balance) {
        super(customer, balance);
    }

    public CurrentAccount(String customer, double balance, double otherBalance) {
        super(customer, balance, otherBalance);
    }

    public void savings(double amount) 
    {
        if (otherAccount == this) {
            return;
        }

        double currentBalance = getBalance();
        double savingsBalance = otherAccount.getBalance();

        if (amount > 0) 
        {
            if (amount > currentBalance) {
                amount = currentBalance;
            } 

            setBalance(currentBalance - amount);
            otherAccount.setBalance(savingsBalance + amount);

            transactions.add("To savings account: " + amount);
            otherAccount.transactions.add("From current account: " + amount);
        } 
        else if (amount <= 0) 
        {
            amount = -amount;

            if (amount > savingsBalance) {
                amount = savingsBalance;
            } 

            setBalance(currentBalance + amount);
            otherAccount.setBalance(savingsBalance - amount);

            transactions.add("From savings account: " + amount);
            otherAccount.transactions.add("To current account: " + amount);
        }
    }

    public void send(double amount, CurrentAccount account) 
    {
        transactions.add("Sent to account " + account.getCustomer() + ": " + amount);
        account.receive(getCustomer(), amount);
        setBalance(getBalance() - amount);

        if (getBalance() < 0) 
        {
            Account savingsAccount = getOtherAccount();

            if (savingsAccount != null) 
            {
                double savingsBalance = savingsAccount.getBalance();

                if (savingsBalance > -getBalance()) {
                    savings(getBalance());
                    setBalance(0);
                } else {
                    savings(-savingsBalance);
                    setBalance(getBalance() + savingsBalance);
                }
            }
            
            if (getBalance() < 0) {
                bank.getLoan(this);
            }
        }
    }

    public void receive(String arg, double amount) 
    {
        setBalance(getBalance() + amount);
        if (arg == "Cash payment") {
            transactions.add("Cash payment: " + amount);
        } else {
            transactions.add("Received from account of " + arg + ": " + amount);
        }
    }
}
