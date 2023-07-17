package GUI;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import controller.ActivityController;
import model.Activity;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.ActivityRepositoryImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ActivityRegistration extends JFrame implements ActionListener {

    private Container container;
    private JTextField totalLoadLabel;
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

    public static void main(String[] args) {
        new ActivityRegistration("Menu");
    }

    private void setupUI() {
        setMenu();
        setSize(375, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.decode("#FFFFFF"));
        setVisible(true);
    }

    // Main class constructor
    public ActivityRegistration(String title) {
        super(title);
        setupUI();
    }// Main class constructor

    // menu choices
    JMenuItem Registration, ListActivity, WeeklyTrainingLoad, Exit;

    // menu method for creation and style
    private void setMenu() {
        JMenuBar barObj = new JMenuBar();
        JMenu messagesObj = new JMenu("Menu");
        UIManager.put("MenuBar.background", Color.decode("#2E4053")); // a modern navy color
        UIManager.put("Menu.background", Color.decode("#2E4053"));
        UIManager.put("MenuItem.background", Color.decode("#FFFFFF"));
        UIManager.put("MenuItem.opaque", true);
        UIManager.put("Menu.foreground", Color.decode("#FFFFFF")); // white color for the menu items' text
        UIManager.put("MenuItem.foreground", Color.decode("#2E4053")); // navy color for the menu items' text

        barObj.setBackground(Color.LIGHT_GRAY); // set menu bar bg color

        Registration = new JMenuItem("Registration");
        Registration.setToolTipText("Push to register"); // write text when u hang mouse over
        Registration.addActionListener(this);
        Registration.setBackground(Color.WHITE); // set menu bar menu options bg color
        messagesObj.add(Registration); // add Registration into messages

        ListActivity = new JMenuItem("List activites");
        ListActivity.setToolTipText("Show all registered activities");
        ListActivity.addActionListener(this);
        ListActivity.setBackground(Color.WHITE);
        messagesObj.add(ListActivity);

        WeeklyTrainingLoad = new JMenuItem("Weekly Training Load");
        WeeklyTrainingLoad.setToolTipText("Show weekly training load data");
        WeeklyTrainingLoad.addActionListener(this);
        WeeklyTrainingLoad.setBackground(Color.WHITE);
        messagesObj.add(WeeklyTrainingLoad);

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
        Container container = this.getContentPane();
        container.removeAll();
        if (e.getSource() == Registration) {
            container.setLayout(new FlowLayout());
            activityRegistration(container);
        } else if (e.getSource() == ListActivity) {
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
            displayActivities(container);
        } else if (e.getSource() == Exit) {
            int exitReply = JOptionPane.showConfirmDialog(this, "Voulez-vous quitter l'application ?",
                    "Exit", JOptionPane.YES_NO_OPTION);
            if (exitReply == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }else if (e.getSource() == WeeklyTrainingLoad) {
            container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
            displayWeeklyTrainingLoad(container);
        }
        container.repaint();
        container.revalidate();
    }// actionPerformed

    public void activityRegistration(Container container){
        JTextField jtfRegLabel = new JTextField("Activity Registration : ", 25);
        jtfRegLabel.setHorizontalAlignment(JTextField.CENTER);
        jtfRegLabel.setEditable(false);
        jtfRegLabel.setBackground(Color.decode("#2E4053"));
        jtfRegLabel.setForeground(Color.decode("#FFFFFF"));
        jtfRegLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel titleLabel = new JLabel("Activity Registration");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        container.add(titleLabel);

        JTextField jtfNameLabel = new JTextField("Name Activity : ", 17);
        jtfNameLabel.setEditable(false);
        JTextField jtfText1 = new JTextField(7);

        JTextField jtfDurationLabel = new JTextField("Duration : ", 17);
        jtfDurationLabel.setEditable(false);
        JTextField jtfText2 = new JTextField(7);

        JTextField jtfDateLabel = new JTextField("Date : ", 7);
        jtfDateLabel.setEditable(false);
        JComboBox jftComboBox1 = new JComboBox(dates);

        JComboBox jftComboBox2 = new JComboBox(months);

        JComboBox jftComboBox3 = new JComboBox(years);


        JTextField jtfRPELabel = new JTextField("RPE : ", 10);
        jtfRPELabel.setEditable(false);
        SpinnerModel value = new SpinnerNumberModel(0, 0, 10, 1);
        JSpinner jtfSpinner = new JSpinner(value);

        JButton submitRegObj = new JButton("Submit");
        submitRegObj.setFont(new Font("Arial", Font.PLAIN, 14));
        submitRegObj.setForeground(Color.decode("#000"));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        namePanel.add(jtfNameLabel);
        namePanel.setOpaque(false);
        namePanel.add(jtfText1);
        container.add(namePanel);

        JPanel durationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        durationPanel.add(jtfDurationLabel);
        durationPanel.setOpaque(false);
        durationPanel.add(jtfText2);
        container.add(durationPanel);

        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(jtfDateLabel);
        datePanel.setOpaque(false);
        datePanel.add(jftComboBox1);
        datePanel.add(jftComboBox2);
        datePanel.add(jftComboBox3);
        container.add(datePanel);

        JPanel rpePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rpePanel.add(jtfRPELabel);
        rpePanel.setOpaque(false);
        rpePanel.add(jtfSpinner);
        container.add(rpePanel);

        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitPanel.add(submitRegObj);
        submitPanel.setOpaque(false);
        container.add(submitPanel);

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
        panelListActivities.setBackground(Color.decode("#FFFFFF")); // set a light background color for modern look
        panelListActivities.setLayout(new BoxLayout(panelListActivities, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("My Activities");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));  // set the font, you can change the size as needed
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);  // center the label
        titleLabel.setBorder(new EmptyBorder(10, 0, 10, 0));  // 10px padding at the top and the bottom

        panelListActivities.add(titleLabel);

        JSeparator titleSeparator = new JSeparator();
        titleSeparator.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelListActivities.add(titleSeparator);


        for (Activity activity : listActivities) {

            JTextField jtfNameLabel = new JTextField("Name: " + activity.getName(), 17);
            jtfNameLabel.setEditable(false);
            jtfNameLabel.setBorder(BorderFactory.createEmptyBorder());
            jtfNameLabel.setBackground(Color.decode("#E1F5FE")); // Light blue background
            jtfNameLabel.setFont(jtfNameLabel.getFont().deriveFont(Font.BOLD)); // Set text to bold
            panelListActivities.add(jtfNameLabel);

            JTextField jtfDurationLabel = new JTextField("Duration: " + activity.getDuration().toString() + " minute(s)", 17);
            jtfDurationLabel.setEditable(false);
            jtfDurationLabel.setBorder(BorderFactory.createEmptyBorder());
            jtfDurationLabel.setBackground(Color.decode("#E1F5FE")); // Light blue background
            panelListActivities.add(jtfDurationLabel);

            DateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH);

            JTextField jtfDateLabel = new JTextField("Date: " + df.format(activity.getDate()), 17);
            jtfDateLabel.setEditable(false);
            jtfDateLabel.setBorder(BorderFactory.createEmptyBorder());
            jtfDateLabel.setBackground(Color.decode("#E1F5FE")); // Light blue background
            panelListActivities.add(jtfDateLabel);

            JTextField jtfRPELabel = new JTextField("RPE: " + activity.getRpe().toString(), 17);
            jtfRPELabel.setEditable(false);
            jtfRPELabel.setBorder(BorderFactory.createEmptyBorder());
            jtfRPELabel.setBackground(Color.decode("#E1F5FE")); // Light blue background
            panelListActivities.add(jtfRPELabel);

            JTextField jtfLoadLabel = new JTextField("Load: " + activity.getLoad().toString(), 17);
            jtfLoadLabel.setEditable(false);
            jtfLoadLabel.setBorder(BorderFactory.createEmptyBorder());
            jtfLoadLabel.setBackground(Color.decode("#E1F5FE")); // Light blue background
            panelListActivities.add(jtfLoadLabel);

            // Add a JSeparator for a more modern look
            JSeparator separator = new JSeparator();
            separator.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panelListActivities.add(separator);
        }

        JScrollPane scrollingPanel = new JScrollPane(panelListActivities, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.add(scrollingPanel);
    }

    public void displayWeeklyTrainingLoad(Container container) {
        String connectionString = "mongodb+srv://thomas:kfNaplaiusOuzFqi@cluster0.ysbjvuj.mongodb.net/?retryWrites=true&w=majority";

        List<Activity> listActivities;

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("myActivity");
            MongoCollection<Document> activityCollection = database.getCollection("activity");
            ActivityRepositoryImpl activityRepository = new ActivityRepositoryImpl(activityCollection);
            ActivityController activityController = new ActivityController(activityRepository);
            listActivities = activityController.getAllActivities();
        }

        // Organize activities by week
        List<Week> weeks = new ArrayList<>();

        for (Activity activity : listActivities) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(activity.getDate());
            int weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);

            Week week = findOrCreateWeek(weeks, weekNumber);
            week.addActivity(activity);
        }

        // Display activities by week
        JPanel panelListActivities = new JPanel();
        panelListActivities.setBackground(Color.decode("#FFFFFF"));
        panelListActivities.setLayout(new BoxLayout(panelListActivities, BoxLayout.Y_AXIS));

        for (Week week : weeks) {
            JLabel weekLabel = new JLabel("Week " + week.getWeekNumber());
            weekLabel.setFont(new Font("Arial", Font.BOLD, 18));
            weekLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            weekLabel.setBorder(new EmptyBorder(10, 0, 10, 0));

            panelListActivities.add(weekLabel);

            int totalDuration = week.getTotalDuration();
            JTextField jtfLoadLabel = new JTextField("LOAD: " + totalDuration + " minute(s)", 17);
            jtfLoadLabel.setEditable(false);
            jtfLoadLabel.setBorder(BorderFactory.createEmptyBorder());
            jtfLoadLabel.setBackground(Color.decode("#3C3B6E"));
            jtfLoadLabel.setForeground(Color.decode("#FFFFFF"));
            panelListActivities.add(jtfLoadLabel);

            double loadVariability = week.getLoadVariability();
            JTextField jtfMonotonyLabel = new JTextField("Monotony: " + loadVariability, 17);
            jtfMonotonyLabel.setEditable(false);
            jtfMonotonyLabel.setBorder(BorderFactory.createEmptyBorder());
            jtfMonotonyLabel.setBackground(Color.decode("#3C3B6E"));
            jtfMonotonyLabel.setForeground(Color.decode("#FFFFFF"));
            panelListActivities.add(jtfMonotonyLabel);

            double constraint = totalDuration * loadVariability;
            JTextField jtfConstraintLabel = new JTextField("Contrainte: " + constraint, 17);
            jtfConstraintLabel.setEditable(false);
            jtfConstraintLabel.setBorder(BorderFactory.createEmptyBorder());
            jtfConstraintLabel.setBackground(Color.decode("#3C3B6E"));
            jtfConstraintLabel.setForeground(Color.decode("#FFFFFF"));
            panelListActivities.add(jtfConstraintLabel);

            double fitness = totalDuration - constraint;
            JTextField jtfFitnessLabel = new JTextField("Fitness: " + fitness, 17);
            jtfFitnessLabel.setEditable(false);
            jtfFitnessLabel.setBorder(BorderFactory.createEmptyBorder());
            jtfFitnessLabel.setBackground(Color.decode("#3C3B6E"));
            jtfFitnessLabel.setForeground(Color.decode("#FFFFFF"));
            panelListActivities.add(jtfFitnessLabel);

            for (Activity activity : week.getActivities()) {
                JTextField jtfNameLabel = new JTextField("Name: " + activity.getName(), 17);
                jtfNameLabel.setEditable(false);
                jtfNameLabel.setBorder(BorderFactory.createEmptyBorder());
                jtfNameLabel.setBackground(Color.decode("#E1F5FE"));
                jtfNameLabel.setFont(jtfNameLabel.getFont().deriveFont(Font.BOLD));
                panelListActivities.add(jtfNameLabel);

                JTextField jtfDurationLabel = new JTextField("Duration: " + activity.getDuration().toString() + " minute(s)", 17);
                jtfDurationLabel.setEditable(false);
                jtfDurationLabel.setBorder(BorderFactory.createEmptyBorder());
                jtfDurationLabel.setBackground(Color.decode("#E1F5FE"));
                panelListActivities.add(jtfDurationLabel);

                DateFormat df = new SimpleDateFormat("dd MMMM yyyy", Locale.FRANCE);

                JTextField jtfDateLabel = new JTextField("Date: " + df.format(activity.getDate()), 17);
                jtfDateLabel.setEditable(false);
                jtfDateLabel.setBorder(BorderFactory.createEmptyBorder());
                jtfDateLabel.setBackground(Color.decode("#E1F5FE"));
                panelListActivities.add(jtfDateLabel);

                JTextField jtfRPELabel = new JTextField("RPE: " + activity.getRpe().toString(), 17);
                jtfRPELabel.setEditable(false);
                jtfRPELabel.setBorder(BorderFactory.createEmptyBorder());
                jtfRPELabel.setBackground(Color.decode("#E1F5FE"));
                panelListActivities.add(jtfRPELabel);

                JSeparator separator = new JSeparator();
                separator.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                panelListActivities.add(separator);
            }

            JSeparator weekSeparator = new JSeparator();
            weekSeparator.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            panelListActivities.add(weekSeparator);
        }

        JScrollPane scrollingPanel = new JScrollPane(panelListActivities, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        container.add(scrollingPanel);
    }

    private Week findOrCreateWeek(List<Week> weeks, int weekNumber) {
        for (Week week : weeks) {
            if (week.getWeekNumber() == weekNumber) {
                return week;
            }
        }

        Week newWeek = new Week(weekNumber);
        weeks.add(newWeek);
        return newWeek;
    }

}
