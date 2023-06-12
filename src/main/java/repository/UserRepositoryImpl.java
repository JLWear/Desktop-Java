package repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import model.User;
import org.bson.Document;

import static mapper.UserMapper.userToDocument;

public class UserRepositoryImpl implements UserRepository {
    MongoCollection<Document> collection;
    public UserRepositoryImpl(MongoCollection<Document> collection) {
        this.collection = collection;
    }
    @Override
    public InsertOneResult save(User user) {
        return this.collection.insertOne(userToDocument(user));
    }
}