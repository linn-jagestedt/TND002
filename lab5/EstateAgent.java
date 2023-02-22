package lab5;

import java.util.*;

public class EstateAgent 
{
    public static final int ALLBUILDINGS = 1;
    public static final int CITYBUILDINGS = 2;
    public static final int COTTAGES = 3;

    public String Name;

    private ArrayList<Building> _buildings;
    private ArrayList<CityProperty> _cityBuildings;
    private ArrayList<Cottage> _cottages;

    public EstateAgent(String name) 
    {
        Name = name;
    
        _buildings = new ArrayList<>();
        _cityBuildings = new ArrayList<>();
        _cottages = new ArrayList<>();
    }

    public String addBuilding(Building building) 
    {
        _buildings.add(building);

        return "";
    }

    public String sort(int i) {
        return "";
    }

    public void updateLists() {

    }

    public void updateLists(int i) {

    }

    public String toString() {
        return "";
    }
}
