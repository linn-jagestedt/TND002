package lab6;

import java.util.ArrayList;

public class PhoneBook
{
    private ArrayList<Person> _numbers;

    public PhoneBook() {
        _numbers = new ArrayList<>();
    }

    public String load(String file) {

    }

    public ArrayList<Person> search(String searString) 
    {
        ArrayList<Person> result = new ArrayList<>();

        for (int i = 0; i < _numbers.size(); i++) 
        {
            Person person = _numbers.get(i);

            if (person.getSurname() == searString || String.valueOf(person.getPhoneNumber()) == searString) {
                result.add(person);
            }
        }

        return result;
    }

    public String deletPerson(String fullName, int phoneNumber) 
    {
        for (int i = 0; i < _numbers.size(); i++) 
        {
            Person person = _numbers.get(i);

            if (person.getFullName() == fullName && person.getPhoneNumber() == phoneNumber) {
                _numbers.remove(i);
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

        for (int i = 0; i < _numbers.size(); i++) 
        {
            Person person = _numbers.get(i);

            if (person.getFullName() == fullName && person.getPhoneNumber() == phoneNumber) {
                return false;
            }
        }

        _numbers.add(new Person(names[0], names[1], phoneNumber));
        return true;
    }

    public String save(String s) 
    {

    }
}

