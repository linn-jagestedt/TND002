package lab5;

public class Villa extends Building implements CityProperty
{
    private double _electricity;
    private double _heating;
    private int _bedrooms;

    public Villa(String address, double price, int area, double electricity, double heating, int bedrooms) 
    {
        super(address, price, area);
        
        _electricity = electricity;
        _heating = heating;
        _bedrooms = bedrooms;
    }

    public int get_bedrooms() {
        return _bedrooms;
    }
    
    public double maintanance() {
        return _electricity + _heating;
    }

    public double computePropertyTax() {
        return 20 * area + 100 * _bedrooms;
    }
}
