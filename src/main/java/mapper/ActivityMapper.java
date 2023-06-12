package mapper;

import model.Activity;
import org.bson.Document;

import java.util.Date;

public class ActivityMapper {

    public static Document activityToDocument(Activity activity) {
        Document document = new Document()
                .append("name", activity.getName())
                .append("duration", activity.getDuration())
                .append("date", activity.getDate())
                .append("rpe", activity.getRpe())
                .append("load", activity.getLoad());
        return document;
    }

    public static Activity documentToActivity(Document document) {
        Activity activity = new Activity(
                (String) document.get("name"),
                (int) document.get("duration"),
                (Date) document.get("date"),
                (int) document.get("rpe"),
                (int) document.get("load")
        );
        return activity;
    }
}
