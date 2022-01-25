package com.company;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;


public class Screen<Person> extends JFrame{
    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JList listPeople;
    private JButton buttonNew;
    private JButton buttonSave;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JLabel labelAge;
    private JTextField textDateofbirth;
    private JPanel panelTop;
    private ArrayList<Person> people;
    private DefaultListModel listPeopleModel;

    Screen() {
        super("My fancy contacts project");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        people = new ArrayList<Person>();
        listPeopleModel = new DefaultListModel();
        listPeople.setModel(listPeopleModel);
        buttonSave.setEnabled(false);

        buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        listPeople.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
            }
        });
    }

    public void refreshPeopleList(){
        listPeopleModel.removeAllElements();
        System.out.println("Removing all people from list");
        for (Person p : people){
            System.out.println("Adding person to list: " + p.getName());
            listPeopleModel.addElement(p.getName());
        }

    }
            public void addPerson(Person p) {
                people.add(p);
    }

    public void main(String[] args){
        Screen screen = new Screen();
        screen.setVisible(true);

        Person sheldon = new Person(name "Sheldon Cooper", email "sheldon@gmail.com", phoneNumber "5550001", dateOfBirth "01/05/1980");
        Person howard = new Person(name "Howard Wolowitz", email "howard@gmail.com", phoneNumber "5550002", dateOfBirth "28/11/1980");
        Person raj = new Person(name "Rajesh Koothrappali", email "raj@gmail.com", phoneNumber "5550003", dateOfBirth "10/04/1980");
        Person leonard = new Person(name "Leonard Hoftadter", email "leonard@gmail.com", phoneNumber "5550004", dateOfBirth "15/08/1980");
        Person penny = new Person(name "Penny Hofstadter", email "penny@gmail.com", phoneNumber "5550005", dateOfBirth "30/02/1983");
        Person bernadette = new Person(name "Bernadette Wolowitz", email "bernadette@gmail.com", phoneNumber "5550006", dateOfBirth "09/10/1981");
        Person amy = new Person(name "Amy Farrah Fowler", email "amy@gmail.com", phoneNumber "5550007", dateOfBirth "23/06/1981");

        screen.addPerson(sheldon);
        screen.addPerson(howard);
        screen.addPerson(raj);
        screen.addPerson(leonard);
        screen.addPerson(penny);
        screen.addPerson(bernadette);
        screen.addPerson(amy);
    }
}
