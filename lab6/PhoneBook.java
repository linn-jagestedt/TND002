package lab6;

import java.util.ArrayList;
import java.io.*;

public class PhoneBook
{
    private ArrayList<Person> _people;

    public PhoneBook() {
        _people = new ArrayList<>();
    }

    public String load(String fileName) 
    {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            
            while ((line = br.readLine()) != null) 
            {
                String[] tokens = line.trim().split(" ");

                if(tokens.length != 3) {
                    continue;
                }

                _people.add(
                    new Person(
                        tokens[0], 
                        tokens[1], 
                        Integer.parseInt(tokens[2])
                    )
                );
            }

            br.close();
            return "Phone book loaded";
        } catch (IOException e) {
            return "try again";
        }
    }

    public ArrayList<Person> search(String searchString) 
    {
        ArrayList<Person> result = new ArrayList<>();

        for (Person person : _people) 
        {
            if (person.getSurname().equals(searchString) || String.valueOf(person.getPhoneNumber()) == searchString) {
                result.add(person);
            }
        }

        return result;
    }

    public String deletPerson(String fullName, int phoneNumber) 
    {
        for (int i = 0; i < _people.size(); i++) 
        {
            Person person = _people.get(i);

            if (person.getFullName().equals(fullName) && person.getPhoneNumber() == phoneNumber) {
                _people.remove(i);
                return "Person deleted";
            }
        }

        return "The person/number does not exist";
    }

    public Boolean addPerson(String fullName, int phoneNumber) 
    {
        String[] names = fullName.split(" ");

        if (names.length != 2) {
            return false;
        }

        for (Person person : _people) 
        {
            if (person.getFullName().equals(fullName) && person.getPhoneNumber() == phoneNumber) {
                return false;
            }
        }

        _people.add(new Person(names[0], names[1], phoneNumber));
        return true;
    }

    public String save(String fileName) 
    {
        try 
        {
            FileWriter fw = new FileWriter(fileName);

            for (Person person : _people) {
                String fullName = String.format("%-20s", person.getFullName());
                String phoneNumber = String.format("%5d", person.getPhoneNumber());
                fw.write(fullName + phoneNumber + "\n");
            }

            fw.close();
            return "Saved " + _people.size() + " people to the file";
        } catch (IOException e) {
            return "Could not save to the file";
        }
    }
}

