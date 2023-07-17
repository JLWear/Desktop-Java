package GUI.Activities;

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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class Register {

    private static final Logger logger = LoggerFactory.getLogger(Register.class);

    private final String[] dates
            = { "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31" };
    private final String[] months
            = { "Jan", "feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec" };
    private final String[] years
            = { "1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019", "2020", "2021", "2022", "2023" };

    public void register(Container container){

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
                //String connectionString = "mongodb+srv://app-de-test:app-de-test@cluster0.mcjcyio.mongodb.net/?retryWrites=true&w=majority";
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

}
