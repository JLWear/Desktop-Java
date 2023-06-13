package repository;

import model.Activity;
import org.bson.types.ObjectId;

import java.util.List;

public interface ActivityRepository {
    ObjectId save(Activity activity);

    Activity getActivityById (ObjectId id) throws Exception;

    List<Activity> getAll();


}