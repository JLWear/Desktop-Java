import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import model.Activity;
import model.User;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import repository.ActivityRepositoryImpl;
import repository.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SportApp {
    private static Logger logger = LoggerFactory.getLogger(SportApp.class);

    public static void main(String[] args) {
        String connectionString = "mongodb+srv://thomas:kfNaplaiusOuzFqi@cluster0.ysbjvuj.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
            databases.forEach(db -> logger.info("Database : {}", db.toJson()));
            MongoDatabase database = mongoClient.getDatabase("myActivity");

            MongoCollection<Document> activityCollection = database.getCollection("activity");
            ActivityRepositoryImpl activityRepository = new ActivityRepositoryImpl(activityCollection);

            Activity activity = new Activity(
                    ,
                    "Tennis",
                    2,
                    new Date(2023, 06, 15),
                    302,
                    24
            );
            logger.info("Activity saved {}", activityRepository.save(activity));

            MongoCollection<Document> userCollection = database.getCollection("user");
            UserRepositoryImpl userRepository = new UserRepositoryImpl(userCollection);

            User user = new User(
                    "Paire",
                    "Benoit",
                    "08/05/1989",
                    "Mr"
            );
            logger.info("User saved {}", userRepository.save(user));

            logger.info("Activity : {}", activityRepository.getAll());

        } catch (Exception e) {
            logger.error("An error occurred during connection ==> {}", e);
        }
    }
}
