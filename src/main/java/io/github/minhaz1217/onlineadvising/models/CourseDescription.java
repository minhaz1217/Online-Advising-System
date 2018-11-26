package io.github.minhaz1217.onlineadvising.models;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Document(collection = "CourseDescription")
public class CourseDescription {

    @Id
    @GeneratedValue
    private String id;
    private String name;
    private String code;
    private String sec;
    private String timeSlot;
    private String day;
    private String room;
    private String seats;
    public CourseDescription(){

    }
    public CourseDescription(String name, String code, String sec, String timeSlot, String day, String room, String seats) {
        this.name = name;
        this.code = code;
        this.sec = sec;
        this.timeSlot = timeSlot;
        this.day = day;
        this.room = room;
        this.seats = seats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }
}
