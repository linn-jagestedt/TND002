package lab5;

public class Cottage extends Building
{
    private double electricity;
    
    public Cottage(String address, double price, int area, double electricity) 
    {
        super(address, price, area);
        this.electricity = electricity;
    }

    public double maintanance() {
        return 0;
    }
}
