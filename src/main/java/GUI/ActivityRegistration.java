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
import java.util.List;


public class ActivityRegistration extends JFrame implements ActionListener {
    private Container container;
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
        setSize(800, 600);// size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close running program if window are closed
        setLocationRelativeTo(null); // set window position at center
        show();
    }// Main class constructor

    // menu choices
    JMenuItem Registration, Exit, ListActivity;

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

        ListActivity = new JMenuItem("List activites");
        ListActivity.setToolTipText("Show all registered activities");
        ListActivity.addActionListener(this);
        ListActivity.setBackground(Color.WHITE);
        messagesObj.add(ListActivity);

        barObj.add(messagesObj);
        setJMenuBar(barObj);
    } //create menu end


    // implemented method
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == Registration){
            container = this.getContentPane();
            container.removeAll();
            activityRegistration(container);
            container.repaint();
            container.revalidate();
            container.setLayout(new FlowLayout());

        } else if (e.getSource() == ListActivity){
            container = this.getContentPane();
            container.removeAll();
            displayActivities(container);
            container.repaint();
            container.revalidate();
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        } else if (e.getSource() == Exit){
            int exitReply = JOptionPane.showConfirmDialog(this, "Voulez-vous quitter l'application ?",
                    "Exit", JOptionPane.YES_NO_OPTION);// exitReply is what u have choosen
            if(exitReply == JOptionPane.YES_OPTION){// if its has been chose/ program will shutdown
                System.exit(0);
            }
        }
    }// actionPerformed

    public void activityRegistration(Container container){

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

        JTextField jtfRPELabel = new JTextField("RPE", 10);
        jtfRPELabel.setEditable(false);
        SpinnerModel value = new SpinnerNumberModel(0, 0, 10, 1);
        JSpinner jtfSpinner = new JSpinner(value);

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
        container.add(jtfSpinner);
        container.add(jtfRPELabel);
        container.add(submitRegObj);

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
                Integer rpe = Integer.parseInt(jtfSpinner.getValue().toString());

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

    public void displayActivities(Container container){
        String connectionString = "mongodb+srv://thomas:kfNaplaiusOuzFqi@cluster0.ysbjvuj.mongodb.net/?retryWrites=true&w=majority";

        List<Activity> listActivities;

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("myActivity");
            MongoCollection<Document> activityCollection = database.getCollection("activity");
            ActivityRepositoryImpl activityRepository = new ActivityRepositoryImpl(activityCollection);
            ActivityController activityController = new ActivityController(activityRepository);
            listActivities = activityController.getAllActivities();
        }
        JPanel panelListActivities = new JPanel();
        panelListActivities.setLayout(new BoxLayout(panelListActivities, BoxLayout.Y_AXIS));



        for (Activity activity : listActivities) {

            JTextField jtfNameLabel = new JTextField(activity.getName(), 17);
            jtfNameLabel.setEditable(false);
            panelListActivities.add(jtfNameLabel);

            JTextField jtfDurationLabel = new JTextField(activity.getDuration().toString(), 17);
            jtfDurationLabel.setEditable(false);
            panelListActivities.add(jtfDurationLabel);

            JTextField jtfDateLabel = new JTextField(activity.getDate().toString(), 17);
            jtfDateLabel.setEditable(false);
            panelListActivities.add(jtfDateLabel);

            JTextField jtfRPELabel = new JTextField(activity.getRpe().toString(), 17);
            jtfRPELabel.setEditable(false);
            panelListActivities.add(jtfRPELabel);

            JTextField jtfLoadLabel = new JTextField(activity.getLoad().toString(), 17);
            jtfLoadLabel.setEditable(false);
            panelListActivities.add(jtfLoadLabel);

            JPanel separator = new JPanel();
            separator.setSize(50, 3);
            separator.setBackground(Color.LIGHT_GRAY);
            panelListActivities.add(separator);

        }
        JScrollPane scrollingPanel = new JScrollPane(panelListActivities, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.add(scrollingPanel);
    }
}
