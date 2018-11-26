/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author Minhaz
 */
@Document(collection = "Course")
public class Course {
    @Id @GeneratedValue
    private String id;
    private String name;
    private String code;
    private String dept;
    private int has_lab;
    private List<String> prerequisite;

    private Course(){
        this.prerequisite = new ArrayList<>();
    }
    

    public Course(String name, String course_code, String dept, int has_lab, ArrayList<String> prerequisite) {
        this.name = name;
        this.code = course_code;
        this.dept = dept;
        this.has_lab = has_lab;
        this.prerequisite = prerequisite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse_code() {
        return code;
    }

    public void setCourse_code(String course_code) {
        this.code = course_code;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getHas_lab() {
        return has_lab;
    }

    public void setHas_lab(int has_lab) {
        this.has_lab = has_lab;
    }

    public List<String> getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(List<String> prerequisite) {
        this.prerequisite = prerequisite;
    }

    
    
}
