/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.Controller;

import io.github.minhaz1217.onlineadvising.Interface.CourseRepository;
import io.github.minhaz1217.onlineadvising.models.Course;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.print.DocFlavor;

/**
 *
 * @author Minhaz
 */

@Controller
@RequestMapping("/course")
public class CourseController {
    
    private final CourseRepository courseRepository;
    
    public CourseController(CourseRepository courseRepository){
        this.courseRepository= courseRepository;
    }
    @GetMapping("/all")
    public List<Course> getAll(){
        List<Course> courses = this.courseRepository.findAll();
        return courses;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{courseCode}")
    public Course findCourseByCode(@PathVariable String courseCode){
        return courseRepository.findCourseByCode(courseCode.toUpperCase());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{courseCode}/{val}")
    public List<String> findCourseByCodeAndExtra(@PathVariable String courseCode, @PathVariable int val){
        //return (Course)courseRepository2.findCourseByCode(courseCode);
        Course course = courseRepository.findCourseByCode(courseCode.toUpperCase());
        List<String> myList = new ArrayList<>();
        if(val == 1){
            myList = Arrays.asList(course.getName());
        }else if(val == 2){
            myList = Arrays.asList(courseCode.toUpperCase());
        }else if(val == 3){
            myList = Arrays.asList(course.getDept());
        }else if(val == 4){
            myList = Arrays.asList((course.getHas_lab()));
            //myList = Arrays.asList(Integer.toString(course.getHas_lab()));
        }else{
            myList = (course.getPrerequisite());
        }
        return myList;
        //return courseCode + " " + val;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/remove/{id}")
    public String courseDelete(@PathVariable String id){
        courseRepository.delete(courseRepository.findCourseById(id));
        return "redirect:/show/course";
    }
    
    
}
