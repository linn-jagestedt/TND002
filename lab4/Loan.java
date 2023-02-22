package lab4;

public class Loan extends Account 
{
    public Loan(CurrentAccount account) 
    {   
        super(account);
    }

    public double payOff(double amount) 
    {
        double balance = getBalance();
        setBalance(balance + amount);
        
        transactions.add("Paid off: " + amount);

        return getBalance();
    }
}
