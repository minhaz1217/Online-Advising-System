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
    private String section;
    private String curr;
private CourseExtended(){}

    public CourseExtended(String code, String section) {
        this.code = code;
        this.section = section;
    }
    public CourseExtended(String code) {
        this.code = code;
        this.section = null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
