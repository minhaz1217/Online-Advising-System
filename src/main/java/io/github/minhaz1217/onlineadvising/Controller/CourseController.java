/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.Controller;

import io.github.minhaz1217.onlineadvising.Interface.CourseRepository;
import io.github.minhaz1217.onlineadvising.models.Course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;

/**
 *
 * @author Minhaz
 */

@RestController
@RequestMapping("/course")
public class CourseController {
    
    private final CourseRepository courseRepository2;
    
    public CourseController(CourseRepository courseRepository){
        this.courseRepository2= courseRepository;
    }
    @GetMapping("/all")
    public List<Course> getAll(){
        List<Course> courses = this.courseRepository2.findAll();
        return courses;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{courseCode}")
    public Course findCourseByCode(@PathVariable String courseCode){
        return courseRepository2.findCourseByCode(courseCode.toUpperCase());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{courseCode}/{val}")
    public List<String> findCourseByCodeAndExtra(@PathVariable String courseCode, @PathVariable String val){
        //return (Course)courseRepository2.findCourseByCode(courseCode);
       return courseRepository2.findCourseByCode(courseCode.toUpperCase()).getPrerequisite();
        //return courseCode + " " + val;
    }
    
    
}
