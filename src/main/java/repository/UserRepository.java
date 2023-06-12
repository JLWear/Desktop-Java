package repository;

import com.mongodb.client.result.InsertOneResult;
import model.User;

public interface UserRepository {
    InsertOneResult save(User user);
}