package lab5;

public class Apartment extends Building implements CityProperty
{
    private double _monthlyFee;
    private int _bedrooms;

    public Apartment(String address, double price, int area, double monthlyFee, int bedrooms) 
    {
        super(address, price, area);
        
        _monthlyFee = monthlyFee;
        _bedrooms = bedrooms;
    }

    public double maintanance() {
        return _monthlyFee;
    }

    public double computePropertyTax() {
        return 10 * area + 50 * _bedrooms;
    }
}
