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
    private String code;
    private String sec;
    private String time;
    private String day;
    private String room;
    private String seats;
    private String instructor;
    public CourseDescription(){}

    public CourseDescription(String code, String sec, String time, String day, String room, String seats) {
        this.code = code;
        this.sec = sec;
        this.time = time;
        this.day = day;
        this.room = room;
        this.seats = seats;
        this.instructor = "";
    }
    public CourseDescription(String code, String sec, String time, String day, String room, String seats, String instructor) {
        this.code = code;
        this.sec = sec;
        this.time = time;
        this.day = day;
        this.room = room;
        this.seats = seats;
        this.instructor = instructor;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
