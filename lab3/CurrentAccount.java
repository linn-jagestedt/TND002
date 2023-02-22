package lab3;

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
}
