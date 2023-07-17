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
import java.awt.*;
import java.util.List;

public class DisplayAll {

    public void displayAll(Container container){
        String connectionString = "mongodb+srv://app-de-test:app-de-test@cluster0.mcjcyio.mongodb.net/?retryWrites=true&w=majority";
        //String connectionString = "mongodb+srv://thomas:kfNaplaiusOuzFqi@cluster0.ysbjvuj.mongodb.net/?retryWrites=true&w=majority";

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
