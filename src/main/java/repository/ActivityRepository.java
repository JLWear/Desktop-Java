package repository;

import com.mongodb.client.result.InsertOneResult;
import model.Activity;

public interface ActivityRepository {
    InsertOneResult save(Activity activity);
}