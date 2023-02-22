package lab5;

public abstract class Building implements Comparable<Building>
{
    private String address;
    private double price;
    
    protected int area;
    protected static int selection;

    public Building(String address, double price, int area) 
    {
        this.address = address;
        this.price = price;
        this.area = area;
    }

    public abstract double maintanance();

    public int compareTo(Building b) {
        return 0;
    }

    public String toString() {
        return "";
    }
}
