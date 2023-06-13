package model;

import org.bson.types.ObjectId;

import java.util.Date;

public class Activity {
    public Activity(ObjectId id, String name, Integer duration, Date date, Integer rpe, Integer load){
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.date = date;
        this.rpe = rpe;
        this.load = load;
    }

    public Activity(String name, Integer duration, Date date, Integer rpe, Integer load){
        this.name = name;
        this.duration = duration;
        this.date = date;
        this.rpe = rpe;
        this.load = load;
    }

    private ObjectId id;
    private String name;
    private int duration;
    private Date date;
    private int rpe;
    private int load;

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRpe() {
        return rpe;
    }

    public void setRpe(Integer rpe) {
        this.rpe = rpe;
    }

    public Integer getLoad() {
        return load;
    }

    public void setLoad(Integer load) {
        this.load = load;
    }
}