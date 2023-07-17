package model;

import model.Activity;

import java.util.ArrayList;
import java.util.List;

public class Week {
    private int weekNumber;
    private List<Activity> activities;

    public Week(int weekNumber) {
        this.weekNumber = weekNumber;
        this.activities = new ArrayList<>();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public int getTotalDuration() {
        int totalDuration = 0;
        for (Activity activity : activities) {
            totalDuration += activity.getDuration() * activity.getRpe();
        }
        return totalDuration;
    }

    public double getLoadVariability() {
        double averageLoad = (double) getTotalDuration() / 7;
        double sumSquaredDeviations = 0.0;

        for (Activity activity : activities) {
            double deviation = activity.getDuration() - averageLoad;
            sumSquaredDeviations += deviation * deviation;
        }

        double variance = sumSquaredDeviations / 7;

        if (variance == 0.0) {
            return 1;
        }

        double standardDeviation = Math.sqrt(variance);

        return averageLoad / standardDeviation;
    }
}
