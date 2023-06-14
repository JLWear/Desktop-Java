package GUI;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Activity;
import org.bson.Document;
import repository.ActivityRepositoryImpl;
import controller.ActivityController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.Date;


public class ActivityRegistration extends JFrame implements ActionListener {
    private static final Logger logger = LoggerFactory.getLogger(ActivityRegistration.class);
    private String dates[]
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private String months[]
            = { "Jan", "feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec" };
    private String years[]
            = { "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019", "2020", "2021", "2022", "2023" };

    public static void main (String []args){
        new ActivityRegistration("Menu");

    }
    // Main class constructor
    public ActivityRegistration(String title) {
        super(title);
        setMenu(); //create menu
        setSize(300, 400);// size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close running program if window are closed
        setLocationRelativeTo(null); // set window position at center
        setResizable(false); //resizable or not
        show();
    }// Main class constructor

    // menu choices
    JMenuItem Registration, Exit;

    // menu method for creation and style
    private void setMenu() {
        JMenuBar barObj = new JMenuBar(); // create menuBar obj
        JMenu messagesObj = new JMenu("Menu"); //create menu bar menu object

        barObj.setBackground(Color.LIGHT_GRAY); // set menu bar bg color

        Registration = new JMenuItem("Registration");
        Registration.setToolTipText("Push to register"); // write text when u hang mouse over
        Registration.addActionListener(this);
        Registration.setBackground(Color.WHITE); // set menu bar menu options bg color
        messagesObj.add(Registration); // add Registration into messages

        Exit = new JMenuItem("Exit");
        Exit.setToolTipText("Here you will exit");
        Exit.addActionListener(this);
        Exit.setBackground(Color.WHITE);
        messagesObj.add(Exit);



        barObj.add(messagesObj);
        setJMenuBar(barObj);
    } //create menu end


    // implemented method
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Registration){
            int registReply = JOptionPane.showConfirmDialog(this, "Voulez-vous enregistrer une activité ?",
                    "Register", JOptionPane.YES_NO_OPTION);
            if(registReply == JOptionPane.YES_OPTION){ //registReply is what u have choosen
                activityRegistration ();
            }
        }else if (e.getSource() == Exit){
            int exitReply = JOptionPane.showConfirmDialog(this, "Voulez-vous quitter l'application ?",
                    "Exit", JOptionPane.YES_NO_OPTION);// exitReply is what u have choosen
            if(exitReply == JOptionPane.YES_OPTION){// if its has been chose/ program will shutdown
                System.exit(0);
            }
        } // if end
    }// actionPerformed

    public void activityRegistration(){

        Container container = getContentPane();
// Activity textbox and label
        JTextField jtfRegLabel = new JTextField("Activity Registration", 25);
        jtfRegLabel.setHorizontalAlignment(JTextField.CENTER);
        jtfRegLabel.setEditable(false);


        JTextField jtfText1 = new JTextField(7);
        JTextField jtfNameLabel = new JTextField("Name Activity", 17);
        jtfNameLabel.setEditable(false);

        JTextField jtfText2 = new JTextField(7);
        JTextField jtfDurationLabel = new JTextField("Duration", 17);
        jtfDurationLabel.setEditable(false);

        JComboBox jftComboBox1 = new JComboBox(dates);

        JComboBox jftComboBox2 = new JComboBox(months);

        JComboBox jftComboBox3 = new JComboBox(years);
        JTextField jtfDateLabel = new JTextField("Date", 7);
        jtfDateLabel.setEditable(false);

        JTextField jtfText3 = new JTextField(7);
        JTextField jtfRpeLabel = new JTextField("Rpe", 17);
        jtfRpeLabel.setEditable(false);

// submit registration
        JButton submitRegObj = new JButton("Submit");

        container.add(jtfRegLabel);
        container.add(jtfText1);
        container.add(jtfNameLabel);
        container.add(jtfText2);
        container.add(jtfDurationLabel);
        container.add(jftComboBox1);
        container.add(jftComboBox2);
        container.add(jftComboBox3);
        container.add(jtfDateLabel);
        container.add(jtfText3);
        container.add(jtfRpeLabel);
        container.add(submitRegObj);

        container.setLayout(new FlowLayout());
        setSize(300, 400); // set size of window
        setVisible(true);// set it visible

        submitRegObj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer les valeurs des champs de texte et des combobox
                String name = jtfText1.getText();
                Integer duration = Integer.parseInt(jtfText2.getText());
                int selectedDay = Integer.parseInt((String) jftComboBox1.getSelectedItem());
                int selectedMonth = jftComboBox2.getSelectedIndex() + 1; // Ajouter 1 pour correspondre aux mois de 1 à 12
                int selectedYear = Integer.parseInt((String) jftComboBox3.getSelectedItem());
                Date date = new Date(selectedYear - 1900, selectedMonth - 1, selectedDay); // Créer un objet Date à partir des composants sélectionnés
                Integer rpe = Integer.parseInt(jtfText3.getText());

                // Créer l'objet Activity
                String connectionString = "mongodb+srv://thomas:kfNaplaiusOuzFqi@cluster0.ysbjvuj.mongodb.net/?retryWrites=true&w=majority";
                try (MongoClient mongoClient = MongoClients.create(connectionString)) {
                    MongoDatabase database = mongoClient.getDatabase("myActivity");
                    MongoCollection<Document> activityCollection = database.getCollection("activity");
                    ActivityRepositoryImpl activityRepository = new ActivityRepositoryImpl(activityCollection);
                    ActivityController activityController = new ActivityController(activityRepository);
                    Activity activity = new Activity(name, duration, date, rpe);
                    String resultCreate = activityController.createActivity(activity);
                }catch (Exception err) {
                    logger.error("An error occurred during connection ==> {}", err);
                }
            }
        });
    }

}// Main clases end
