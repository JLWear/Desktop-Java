package repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import model.Activity;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonObject;
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

    @Override
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

    @Override
    public void deleteById(ObjectId id) {
        this.collection.deleteOne(new Document("_id", id));
    }


    @Override
    public Activity update(Activity activity) throws Exception {
        Bson filter = Filters.eq("_id", activity.getId());
        Document document = activityToDocument(activity);

        document.remove("_id");

        Bson update = new Document("$set", document);
        UpdateResult result = collection.updateOne(filter, update);

        if (result.getMatchedCount() != 1) {
            throw new Exception("No such Activity exists to update");
        }

        return getActivityById(activity.getId());
    }
}