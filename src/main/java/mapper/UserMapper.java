package mapper;

import model.User;
import org.bson.Document;

public class UserMapper {

    public static Document userToDocument(User user) {
        Document document = new Document()
                .append("lastname", user.getLastname())
                .append("firstname", user.getFirstname())
                .append("birthday", user.getBirthdate())
                .append("sexe", user.getSexe());
        return document;
    }
}
