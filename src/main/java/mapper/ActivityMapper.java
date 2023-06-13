package mapper;

import model.Activity;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Date;

public class ActivityMapper {

    public static Document activityToDocument(Activity activity) {
        return new Document()
                .append("name", activity.getName())
                .append("duration", activity.getDuration())
                .append("date", activity.getDate())
                .append("rpe", activity.getRpe())
                .append("load", activity.getLoad());
    }

    public static Activity documentToActivity(Document document) {
        return new Activity(
                document.getObjectId("_id"),
                (String) document.get("name"),
                (int) document.get("duration"),
                (Date) document.get("date"),
                (int) document.get("rpe"),
                (int) document.get("load")
        );
    }
}
