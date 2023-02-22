package lab5;

public class Villa extends Building implements CityProperty
{
    private double electricity;
    private double heating;

    private int bedrooms;

    public Villa(String address, double price, int area, double electricity, double heating, int bedrooms) 
    {
        super(address, price, area);
        
        this.electricity = electricity;
        this.heating = heating;
        this.bedrooms = bedrooms;
    }
    
    public double maintanance() {
        return 0;
    }

    public double computePropertyTax() {
        return 20 * area + 100 * bedrooms;
    }
}
