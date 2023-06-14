package controller;

import model.Activity;
import org.bson.types.ObjectId;
import repository.ActivityRepository;

import java.util.Date;
import java.util.List;

public class ActivityController {
    private final ActivityRepository activityRepository;

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

    public Activity getActivityById(ObjectId id) throws Exception {
        return activityRepository.getActivityById(id);
    }

    public void deleteActivity(ObjectId id) {
        activityRepository.deleteById(id);
    }

    public Activity updateActivity(Activity activity) throws Exception {
        return activityRepository.update(activity);
    }

    public List<Activity> getActivityByDate(Date date) {
        return activityRepository.getActivityByDate(date);
    }
}
