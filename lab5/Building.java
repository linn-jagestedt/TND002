package lab5;

import java.util.*;

public abstract class Building implements Comparable<Building>
{
    private String _address;
    private double _price;
    
    protected int area;
    protected static int selection;

    public Building(String address, double price, int area) 
    {
        this._address = address;
        this._price = price;
        this.area = area;
    }

    public abstract double maintanance();

    public int compareTo(Building b) 
    {
        if (selection == 1) {
            return _price > b._price ? 1 : _price < b._price ? -1 : 0;
        } else {
            return _price > b.area ? 1 : _price < b.area ? -1 : 0;
        }
    }

    public String toString() 
    {
        String result = String.format("Address: %16s", _address) + ", ";

        result += String.format("Living area: %4d", area) + ", ";
        result += String.format("Price: %.2f", _price) + ", ";
        result += String.format("Maintanance (per month): %.2f", maintanance()) + "\n";

        if (this instanceof CityProperty) {
            result += "Property tax: " + ((CityProperty)this).computePropertyTax() + "\n";
        }

        return result;
    }
}

class BuildingComparator implements Comparator<Building> {
 
    public int compare(Building a, Building b) {
        return a.compareTo(b);
    }
}