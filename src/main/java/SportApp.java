import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Activity;
import model.User;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.ActivityRepository;
import repository.ActivityRepositoryImpl;
import repository.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SportApp {
    private static final Logger logger = LoggerFactory.getLogger(SportApp.class);

    public static void main(String[] args) {
        String connectionString = "mongodb+srv://thomas:kfNaplaiusOuzFqi@cluster0.ysbjvuj.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {

            MongoDatabase database = mongoClient.getDatabase("myActivity");
            MongoCollection<Document> activityCollection = database.getCollection("activity");
            ActivityRepositoryImpl activityRepository = new ActivityRepositoryImpl(activityCollection);


// Create Activity
//            Activity createActivity = new Activity(
//                    "Hockey sur gazon",
//                    2,
//                    new Date(2023, 06, 15),
//                    302,
//                    24
//            );
//            ObjectId resultCreate = activityRepository.save(createActivity);

// Read Activity

// Update Activity => Donner une Activity avec un ObjectId
//            Activity updateActivity = new Activity(
//                    new ObjectId("64886ec2fd0b9f307e9a8e3a"),
//                    "Football",
//                    2,
//                    new Date(2023, 06, 15),
//                    45,
//                    24
//            );
//            Activity resultUpdate = activityRepository.update(updateActivity);

// Delete Activity => via ID
//            activityRepository.deleteById(new ObjectId("64886ec2fd0b9f307e9a8e3a"));


        } catch (Exception e) {
            logger.error("An error occurred during connection ==> {}", e);
        }
    }
}
