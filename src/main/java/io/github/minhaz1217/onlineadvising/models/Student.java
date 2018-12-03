/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.models;

import java.lang.reflect.Array;
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
    
    private String firstName;
    private String lastName;
    private String email;
    private String studentId;
    private ArrayList<CourseExtended> taken;
    private ArrayList<CourseDescription> advising;
    private Student(){
        taken = new ArrayList<>();
        advising = new ArrayList<>();
    }

    public Student(String firstName, String lastName, String email, String studentId, ArrayList<CourseExtended> taken) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentId = studentId;
        this.taken = taken;
        advising = new ArrayList<>();
    }
    public Student(String firstName, String lastName, String email, String studentId, ArrayList<CourseExtended> taken, ArrayList<CourseDescription> advising) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentId = studentId;
        this.taken = taken;
        this.advising = advising;
    }

    public ArrayList<CourseDescription> getAdvising() {
        return advising;
    }

    public void setAdvising(ArrayList<CourseDescription> advising) {
        this.advising = advising;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public ArrayList<CourseExtended> getTaken() {
        return taken;
    }

    public void setTaken(ArrayList<CourseExtended> taken) {
        this.taken = taken;
    }
}
