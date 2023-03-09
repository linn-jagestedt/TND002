package lab6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GUI extends JFrame 
{
    private PhoneBook _phoneBook;

    private JButton _loadButton; 
    private JButton _saveButton;
    private JButton _searchButton; 
    private JButton _nextButton; 
    private JButton _addButton; 
    private JButton _deleteButton;

    private JTextField _searchField;
    private JTextField _nameField;
    private JTextField _numberField;

    private ArrayList<Person> _searchResult;
    private int _searchResultCounter;

    public GUI() 
    {
        _phoneBook = new PhoneBook();

        // Set GUI properties
        setTitle("Interactive phone book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFont(new Font("SansSerif", Font.PLAIN, 20));

        // Create buttons
        _loadButton = new JButton("Load phonebook");
        _saveButton = new JButton("Save phonebook");
        _searchButton = new JButton("Search");
        _nextButton = new JButton("Next");
        _addButton = new JButton("Add");
        _deleteButton = new JButton("Delete");

        // Set button actions
        _loadButton.addActionListener(new LoadButtonListener());
        _saveButton.addActionListener(new SaveButtonListener());
        _searchButton.addActionListener(new SearchButtonListener());
        _nextButton.addActionListener(new NextButtonListener());
        _addButton.addActionListener(new AddButtonListener());
        _deleteButton.addActionListener(new DeleteButtonListener());

        // Create text fields
        _searchField = new JTextField();
        _nameField = new JTextField();
        _numberField = new JTextField();

        _searchField.setText("lab6/PhoneList.txt");

        // Disable buttons except for loadButton
        _saveButton.setEnabled(false);
        _searchButton.setEnabled(false);
        _nextButton.setEnabled(false);
        _addButton.setEnabled(false);
        _deleteButton.setEnabled(false);

        // Create panels
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2));
        JPanel textPanel = new JPanel(new GridLayout(3, 1));

        // Add buttons to button panel
        buttonPanel.add(_loadButton);
        buttonPanel.add(_saveButton);
        buttonPanel.add(_searchButton);
        buttonPanel.add(_nextButton);
        buttonPanel.add(_addButton);
        buttonPanel.add(_deleteButton);

        // Add text fields to text panel
        textPanel.add(_searchField);
        textPanel.add(_nameField);
        textPanel.add(_numberField);

        // Add panels to content pane
        Container contentPane = getContentPane();
        contentPane.add(buttonPanel, BorderLayout.WEST);
        contentPane.add(textPanel, BorderLayout.CENTER);

        _searchResult = new ArrayList<>();
        _searchResultCounter = 0;

        // Pack and show GUI
        pack();
        setVisible(true);
    }

    private class LoadButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) {
            String filename = _searchField.getText();
            _searchField.setText("");
            String result = _phoneBook.load(filename);
            if (result.equals("Phone book loaded")) {
                _nameField.setText(result);
                _saveButton.setEnabled(true);
                _searchButton.setEnabled(true);
                _addButton.setEnabled(true);
                _deleteButton.setEnabled(true);
            } else {
                _nameField.setText("Try again");
            }
        }
    }

    private class SaveButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) {
            String filename = _searchField.getText();
            _searchField.setText("");
            if (filename.isEmpty()) {
                _nameField.setText("Provide a file name");
            } else {
                String result = _phoneBook.save(filename);
                _nameField.setText(result);
            }
        }
    }

    private class SearchButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) {
            String query = _searchField.getText();
            ArrayList<Person> results = _phoneBook.search(query);
            if (results.isEmpty()) {
                _nameField.setText("No results found");
                _numberField.setText("");
            } else {
                _searchResult = results;
                
                Person firstResult = _searchResult.get(0);
                _nameField.setText(firstResult.getFullName());
                _numberField.setText(Integer.toString(firstResult.getPhoneNumber()));
                
                _searchResultCounter = 1;
                _nextButton.setEnabled(_searchResult.size() > 1);
            }
        }
    }

    private class NextButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            Person result = _searchResult.get(_searchResultCounter);
            _nameField.setText(result.getFullName());
            _numberField.setText(Integer.toString(result.getPhoneNumber()));

            _searchResultCounter++;
            _nextButton.setEnabled(_searchResult.size() > _searchResultCounter);
        }
    }

    private class AddButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            String message = "Type in name and phone number";

            if (!_searchField.getText().equals(message)) 
            {
                _searchField.setText(message);

                _nameField.setText("");
                _nameField.setEditable(true);

                _numberField.setText("");
                _numberField.setEditable(true);
            } else 
            {
                Boolean success = _phoneBook.addPerson(
                    _nameField.getText(), 
                    Integer.parseInt(_numberField.getText())
                );

                if (success) {
                    _searchField.setText("Person added");
                } else {
                    _searchField.setText("Person could not be added");
                }

                _nameField.setText("");
                _nameField.setEditable(false);

                _numberField.setText("");
                _numberField.setEditable(false);
            }
        }
    }

    private class DeleteButtonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            _searchField.setText(
                _phoneBook.deletPerson(
                    _nameField.getText(), 
                    Integer.parseInt(_numberField.getText())
                )
            );
        }
    }
}
