package lab5;

public class Apartment extends Building implements CityProperty
{
    private double monthlyFee;
    private int bedrooms;

    public Apartment(String s, double d, int i, double monthlyFee, int bedrooms) 
    {
        super(s, bedrooms, i);
        
        this.monthlyFee = monthlyFee;
        this.bedrooms = bedrooms;
    }

    public double maintanance() {
        return 0;
    }

    public double computePropertyTax() {
        return 10 * area + 50 * bedrooms;
    }
}
