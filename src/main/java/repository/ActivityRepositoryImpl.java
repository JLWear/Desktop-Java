package repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import model.Activity;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static mapper.ActivityMapper.activityToDocument;
import static mapper.ActivityMapper.documentToActivity;

public class ActivityRepositoryImpl implements ActivityRepository {
    MongoCollection<Document> collection;
    public ActivityRepositoryImpl(MongoCollection<Document> collection) {
        this.collection = collection;
    }
    @Override
    public String save(Activity activity) {
        InsertOneResult result = this.collection.insertOne(activityToDocument(activity));
        return result.getInsertedId().toString();
    }
    @Override
    public List<Activity> getAll() {
        List<Activity> activities = new ArrayList<>();
        for (Document document : this.collection.find()) {
            activities.add(documentToActivity(document));
        }
        return activities;
    }
}