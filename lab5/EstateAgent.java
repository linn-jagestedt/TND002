package lab5;

import java.util.*;
import javax.swing.JOptionPane;

public class EstateAgent 
{
    public static final int ALLBUILDINGS = 1;
    public static final int CITYPROPERTIES = 2;
    public static final int COTTAGES = 3;

    public final String NAME;

    private ArrayList<Building> _buildings;
    private ArrayList<CityProperty> _cityProperties;
    private ArrayList<Cottage> _cottages;

    public EstateAgent(String name) 
    {
        NAME = name;
    
        _buildings = new ArrayList<>();
        _cityProperties = new ArrayList<>();
        _cottages = new ArrayList<>();
    }

    public String addBuilding(Building building) 
    {
        _buildings.add(building);

        if (building instanceof Cottage) {
            _cottages.add((Cottage)building);
            return "Added cottage";
        } else {
            _cityProperties.add((CityProperty)building);
            return "Added city building";
        }
    }

    public String sort(int i) 
    {
        Building.selection = 1 + JOptionPane.showOptionDialog(
            null, 
            "Choose sort method", 
            "Option dialog",
            0, 
            2, 
            null, new String[] { 
                "price", 
                "area" 
            }, 
            "price"
        );

        if (i == ALLBUILDINGS) {
            _buildings.sort(new BuildingComparator());
            updateLists();
        } else if (i == COTTAGES) {
            _cottages.sort(new BuildingComparator()); 
            updateLists(COTTAGES);   
        } else {
            ArrayList<Building> temp = new ArrayList<>();

            for (CityProperty property : _cityProperties) {
                temp.add((Building)property);
            }

            temp.sort(new BuildingComparator());
            _cityProperties.clear();

            for (Building building : temp) {
                _cityProperties.add((CityProperty)building);
            }

            updateLists(CITYPROPERTIES);
        }

        updateLists();
        return Building.selection == 1 ? "Sorted by price" : "Sorted by area" + "\n";
    }

    public void updateLists() 
    {   
        _cityProperties.clear();
        _cottages.clear();

        for (int i = 0; i < _buildings.size(); i++) 
        {
            Building building = _buildings.get(i);
            if (building instanceof Cottage) {
                _cottages.add((Cottage)building);
            } else if (building instanceof CityProperty) {
                _cityProperties.add((CityProperty)building);
            }
        }
    }

    public void updateLists(int option) 
    {
        for (int i = 0, k = 0; i < _buildings.size(); i++) 
        {
            Building building = _buildings.get(i);

            if (option == COTTAGES && building instanceof Cottage) {
                _buildings.set(i, (Building)_cottages.get(k));
                k++;
            }
            
            if (option == CITYPROPERTIES && building instanceof CityProperty) {
                _buildings.set(i, (Building)_cityProperties.get(k));
                k++;
            }
        }
    }

    public String toString() 
    {
        String result =  "Estate agent: " + NAME + "\n\n";
        
        result += "All buildings\n";

        for (Building building : _buildings) {
            result += building.toString();
            result += building instanceof Cottage ? "\n" : "";
        }

        result += "\nCottages\n";

        for (Cottage cottage : _cottages) {
            result += cottage.toString() + "\n";
        }

        result += "\nVillas and apartments\n";

        for (CityProperty cityProperty : _cityProperties) {
            result += cityProperty.toString();
        }

        return result;
    }
}
