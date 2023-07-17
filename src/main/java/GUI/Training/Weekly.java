package GUI.Training;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import controller.ActivityController;
import model.Activity;
import model.Week;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.ActivityRepositoryImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Weekly {

    private static final Logger logger = LoggerFactory.getLogger(Weekly.class);

    public void weekly(Container container) {
        //String connectionString = "mongodb+srv://app-de-test:app-de-test@cluster0.mcjcyio.mongodb.net/?retryWrites=true&w=majority";
        String connectionString = "mongodb+srv://thomas:kfNaplaiusOuzFqi@cluster0.ysbjvuj.mongodb.net/?retryWrites=true&w=majority";

        java.util.List<Activity> listActivities;

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase("myActivity");
            MongoCollection<Document> activityCollection = database.getCollection("activity");
            ActivityRepositoryImpl activityRepository = new ActivityRepositoryImpl(activityCollection);
            ActivityController activityController = new ActivityController(activityRepository);
            listActivities = activityController.getAllActivities();
        }

        // Organize activities by week
        java.util.List<Week> weeks = new ArrayList<>();

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
