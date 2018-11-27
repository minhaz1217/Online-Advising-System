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
import javax.persistence.OrderBy;

import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
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
    @OrderBy(value="code")
    private String code;
    private String dept;
    private String has_lab;
    private List<String> prerequisite;

    private Course(){
        this.prerequisite = new ArrayList<>();
    }
    

    public Course(String name, String course_code, String dept, String has_lab, ArrayList<String> prerequisite) {
        this.name = name;
        this.code = course_code;
        this.dept = dept;
        this.has_lab = (has_lab);
        this.prerequisite = prerequisite;
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

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getHas_lab() {
        return has_lab;
    }

    public void setHas_lab(String has_lab) {
        this.has_lab = has_lab;
    }

    public List<String> getPrerequisite() {
        return prerequisite;
    }

    public void setPrerequisite(List<String> prerequisite) {
        this.prerequisite = prerequisite;
    }
}
