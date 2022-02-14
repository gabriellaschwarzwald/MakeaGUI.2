import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


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
    private JButton buttonClear;
    private JPanel PicturePanel;

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
                        textAddress.getText(),
                        textDateofbirth.getText()
                );
                people.add(p);
                refreshPeopleList();
            }
        });


                buttonSave.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int personNumber = listPeople.getSelectedIndex();
                        if (personNumber >= 0) {
                            Person p = people.get(personNumber);
                            p.setName(textName.getText());
                            p.setEmail(textEmail.getText());
                            p.setPhoneNumber(textPhoneNumber.getText());
                            p.setDateOfBirth(textDateofbirth.getText());
                            refreshPeopleList();
                        }
                    }
                });

                listPeople.addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        int personNumber = listPeople.getSelectedIndex();
                        if (personNumber >= 0) {
                            Person p = people.get(personNumber);
                            textName.setText(p.getName());
                            textEmail.setText(p.getEmail());
                            textPhoneNumber.setText(p.getPhoneNumber());
                            textAddress.setText(p.getAddress());
                            textDateofbirth.setText(p.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                            labelAge.setText(Integer.toString(p.getAge()) + "years");
                            buttonSave.setEnabled(true);
                        } else {
                            buttonSave.setEnabled(false);
                        }
                    }
                });
        buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    textName.setText(" ");
                    textEmail.setText(" ");
                    textPhoneNumber.setText(" ");
                    textAddress.setText(" ");
                    textDateofbirth.setText(" ");

            }
        });
    }



            public void refreshPeopleList() {
                listPeopleModel.removeAllElements();
                System.out.println("Removing all people from list");
                for (Person p : people) {
                    System.out.println("Adding person to list: " + p.getName());
                    listPeopleModel.addElement(p.getName());
                }

            }

            public void addPerson(Person p) {
                people.add(p);
                refreshPeopleList();
            }

            public static void main(String[] args) throws IOException {
                Screen screen = new Screen();
                screen.setSize(850, 500);
                screen.setVisible(true);

                // insert picture:

                screen.PicturePanel.setLayout(new FlowLayout());
                BufferedImage myPicture = ImageIO.read(new File("addressbookpic.png"));
                Image dmyPicture = myPicture.getScaledInstance(111, 96, Image.SCALE_SMOOTH);
                JLabel picLabel = new JLabel(new ImageIcon(dmyPicture));
                screen.PicturePanel.add(picLabel);

                FileWriter fw = new FileWriter ("Contacts Book", true);

                fw.write("Gabriella, gabi.email@gmail.com, 456678898, Via Roma,05/01/2002\n");
                fw.write("John Smith, johnny@gmail.com, 76536272, Via Po,09/04/1998\n");
                fw.write("Paul British, paulie@gmail.com, 72346220, Via Moon,08/03/1996\n");

                fw.close();

                FileReader fr = new FileReader("Contacts Book");
                BufferedReader br = new BufferedReader(fr);
               String line;
                while((line = br.readLine()) != null) {
                   System.out.println(line);
                    String[] arraypeople = line.split(",");
                    Person a = new Person(arraypeople[0], arraypeople[1], arraypeople[2], arraypeople[3], arraypeople[4]);
                    System.out.println(a.getName());
                    System.out.println(a.getEmail());
                    System.out.println(a.getPhoneNumber());
                    System.out.println(a.getAddress());
                    System.out.println(a.getDateOfBirth());

                    screen.addPerson(a);
                }

                fr.close();
                br.close();
/*
                Person sheldon = new Person("Sheldon Cooper", "sheldon@gmail.com", "5550001", "Via Po", "07/07/1997");
                Person howard = new Person("Howard Wolowitz", "howard@gmail.com", "5550002", "Via Garibaldi", "06/09/1970");
                Person raj = new Person("Rajesh Koothrappali", "raj@gmail.com", "5550003", "Via Rapulzel", "28/06/1989");
                Person leonard = new Person("Leonard Hoftadter", "leonard@gmail.com", "5550004", "Via 25", "06/03/1998");
                Person penny = new Person("Penny Hofstadter", "penny@gmail.com", "5550005", "Via 2", "11/09/1998");
                Person bernadette = new Person("Bernadette Wolowitz", "bernadette@gmail.com", "5550006", "Via Phil", "03/11/1996");
                Person amy = new Person("Amy Farrah Fowler", "amy@gmail.com", "5550007", "Via Ire", "05/01/1999");

                screen.addPerson(sheldon);
                screen.addPerson(howard);
                screen.addPerson(raj);
                screen.addPerson(leonard);
                screen.addPerson(penny);
                screen.addPerson(bernadette);
                screen.addPerson(amy);

 */
            }
        }