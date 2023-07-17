package GUI.Activities;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import controller.ActivityController;
import model.Activity;
import org.bson.Document;
import repository.ActivityRepositoryImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class DisplayAll {

    public void displayAll(Container container){
        //String connectionString = "mongodb+srv://app-de-test:app-de-test@cluster0.mcjcyio.mongodb.net/?retryWrites=true&w=majority";
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

}
