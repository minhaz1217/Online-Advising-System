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
import org.springframework.util.MultiValueMap;
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

    @RequestMapping(method = RequestMethod.GET, value = "/edit/{id}")
    public String courseEdit(@PathVariable String id, Model model){
        //courseRepository.delete(courseRepository.findCourseById(id));
        Course course = courseRepository.findCourseByCode("CSE411");
        model.addAttribute("course", course);
        return "EditCourse";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String courseUpdate(@RequestParam MultiValueMap<String, String> myMap, Model model){
        //courseRepository.delete(courseRepository.findCourseById(id));
        Course course = courseRepository.findCourseByCode("CSE411");
        model.addAttribute("course", course);
        List<String> myList = new ArrayList<>();
        myList.add(myMap.values().toString());
        myList.add(myMap.getFirst("name"));
        myList.add(myMap.getFirst("code"));
        myList.add(myMap.getFirst("dept"));
        myList.add(myMap.getFirst("has_lab"));
        for(int i=0;i<myMap.get("prereq").size();i++){
            myList.add(myMap.get("prereq").get(i));
        }
        //myList.add(myMap.get("prereq").get(0) );
        //myList.add(myMap.get("prerq").size() + "");

        model.addAttribute("value", myList);
        return "test";
    }
    
}
