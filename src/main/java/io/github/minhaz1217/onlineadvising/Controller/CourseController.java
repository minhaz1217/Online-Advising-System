/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.Controller;

import io.github.minhaz1217.onlineadvising.Interface.CourseRepository;
import io.github.minhaz1217.onlineadvising.models.Course;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 2016-1-60-134
 */

@RestController
@RequestMapping("/courses")
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
    
    
    
}
