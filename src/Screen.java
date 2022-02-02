import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;


public class Screen extends JFrame {
    private JPanel panelMain;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JList listPeople;
    private JButton buttonNew;
    private JButton buttonSave;
    private JTextField textName;
    private JTextField textEmail;
    private JTextField textPhoneNumber;
    private JTextField textAddress;
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
                Person p = new Person(
                        textName.getText(),
                        textEmail.getText(),
                        textPhoneNumber.getText(),
                        textDateofbirth.getText()
                );
                people.add(p);
                refreshPeopleList();

                int personNumber = listPeople.getSelectedIndex();
                if (personNumber >= 0){
                     p = people.get(personNumber);
                    p.setName(textName.getText());
                    p.setEmail(textEmail.getText());
                    p.setPhoneNumber(textPhoneNumber.getText());
                    p.setDateOfBirth(textDateofbirth.getText());
                }
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
                int personNumber = listPeople.getSelectedIndex();
                if (personNumber >= 0){
                    Person p = people.get(personNumber);
                    textName.setText(p.getName());
                    textEmail.setText(p.getEmail());
                    textPhoneNumber.setText(p.getPhoneNumber());
                    textDateofbirth.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                    labelAge.setText(Integer.toString(p.getAge())+ "years");
                    buttonSave.setEnabled(true);
                } else {buttonSave.setEnabled(false);}
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

    public static void main(String[] args){
        Screen screen = new Screen();
        screen.setVisible(true);


        Person sheldon = new Person("Sheldon Cooper", "sheldon@gmail.com", "5550001", "01/05/1980");
        Person howard = new Person("Howard Wolowitz", "howard@gmail.com", "5550002", "28/11/1980");
        Person raj = new Person("Rajesh Koothrappali", "raj@gmail.com", "5550003", "10/04/1980");
        Person leonard = new Person("Leonard Hoftadter", "leonard@gmail.com", "5550004", "15/08/1980");
        Person penny = new Person("Penny Hofstadter", "penny@gmail.com", "5550005", "30/02/1983");
        Person bernadette = new Person("Bernadette Wolowitz", "bernadette@gmail.com", "5550006", "09/10/1981");
        Person amy = new Person("Amy Farrah Fowler", "amy@gmail.com", "5550007", "23/06/1981");

        screen.addPerson(sheldon);
        screen.addPerson(howard);
        screen.addPerson(raj);
        screen.addPerson(leonard);
        screen.addPerson(penny);
        screen.addPerson(bernadette);
        screen.addPerson(amy);
    }
}
