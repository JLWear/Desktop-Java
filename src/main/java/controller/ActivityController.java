package controller;

import model.Activity;
import org.bson.types.ObjectId;
import repository.ActivityRepository;

import java.util.List;

public class ActivityController {
    private ActivityRepository activityRepository;

    public ActivityController(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public String createActivity(Activity activity) {
        activity.setLoad(activity.getDuration() * activity.getRpe());
        ObjectId id = activityRepository.save(activity);
        return "Activity created : " + id;
    }

    public List<Activity> getAllActivities() {
        return activityRepository.getAll();
    }
}
