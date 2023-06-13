package repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import model.Activity;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

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
    public ObjectId save(Activity activity) {
        InsertOneResult result = this.collection.insertOne(activityToDocument(activity));
        return result.getInsertedId().asObjectId().getValue();
    }

    public Activity getActivityById (ObjectId id) throws Exception {
        Document query = new Document();
        query.append("_id", id);
        Document result = this.collection.find(query).first();
        if (result == null) {
            throw new Exception();
        }
        return documentToActivity(result);
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