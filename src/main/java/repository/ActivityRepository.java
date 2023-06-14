package repository;

import model.Activity;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.List;

public interface ActivityRepository {
    ObjectId save(Activity activity);

    Activity getActivityById (ObjectId id) throws Exception;

    List<Activity> getAll();

    void deleteById(ObjectId id);

    Activity update(Activity activity) throws Exception;

    List<Activity> getActivityByDate(Date date);
}