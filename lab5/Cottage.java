package lab5;

public class Cottage extends Building
{
    private double _electricity;
    
    public Cottage(String address, double price, int area, double electricity) 
    {
        super(address, price, area);
        _electricity = electricity;
    }

    public double maintanance() {
        return _electricity;
    }
}
