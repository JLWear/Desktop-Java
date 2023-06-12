package repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import model.Activity;
import org.bson.Document;

import static mapper.ActivityMapper.activityToDocument;

public class ActivityRepositoryImpl implements ActivityRepository {
    MongoCollection<Document> collection;
    public ActivityRepositoryImpl(MongoCollection<Document> collection) {
        this.collection = collection;
    }
    @Override
    public InsertOneResult save(Activity activity) {
        return this.collection.insertOne(activityToDocument(activity));
    }
}