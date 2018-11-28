/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.models;

/**
 *
 * @author 2016-1-60-134
 */
public class CourseExtended {
    private String code;
    private CourseDescription courseDescription;
    private CourseExtended(){}
    public CourseExtended(String code){
        this.code = code;
        this.courseDescription = null;
    }
    public CourseExtended(String code, CourseDescription courseDescription) {
        this.code = code;
        this.courseDescription = courseDescription;
    }
    public CourseExtended(CourseDescription courseDescription) {
        this.code = courseDescription.getCode();
        this.courseDescription = courseDescription;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CourseDescription getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(CourseDescription courseDescription) {
        this.courseDescription = courseDescription;
    }


    
}
