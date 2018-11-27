/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.models;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/**
 *
 * @author Minhaz
 */



@Document(collection = "Student")
public class Student {
    
    @Id @GeneratedValue
    private String id;
    
    private String first_name;
    private String last_name;
    private String email;
    private String student_id;
    private CourseExtended[] taken;
    private Student(){
        //taken = new ArrayList<>();
    }
    public Student(String first_name, String last_name, String email, String student_id, CourseExtended... taken) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.student_id = student_id;
        this.taken = taken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public CourseExtended[] getTaken() {
        return taken;
    }

    public void setTaken(CourseExtended... taken) {
        this.taken = taken;
    }
    
    
    
    
}
