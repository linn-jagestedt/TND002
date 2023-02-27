package lab6;

public class Person 
{
    private String _name;
    private String _surname;
    private int _phoneNumber;

    public Person(String name, String surname, int phoneNumber) {
        _name = name;
        _surname = surname;
        _phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return _surname;

    }
    public String getFullName() {
        return _name + " " + _surname;
    }

    public int getPhoneNumber() {
        return _phoneNumber;
    }
}
