/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.Controller;

import io.github.minhaz1217.onlineadvising.Interface.CourseDescriptionRepository;
import io.github.minhaz1217.onlineadvising.Interface.CourseRepository;
import io.github.minhaz1217.onlineadvising.models.Course;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.github.minhaz1217.onlineadvising.models.CourseDescription;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(method = RequestMethod.POST, value = "/edit")
    public String courseEdit(@RequestParam("id") String id, Model model){
        //Course course = courseRepository.findCourseByCode("CSE411");
        Course course = courseRepository.findCourseById(id);
        model.addAttribute("course", course);
        return "/edit/EditCourse";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String courseUpdate(@RequestParam MultiValueMap<String, String> myMap, Model model){
        //courseRepository.delete(courseRepository.findCourseById(id));
        ArrayList<String> myList = new ArrayList<>();

        myList.add(myMap.getFirst("firstName"));
        myList.add(myMap.getFirst("lastName"));
        myList.add(myMap.getFirst("email"));
        myList.add(myMap.getFirst("studentId"));
        /*
        myList.add(myMap.values().toString());
        myList.add(myMap.getFirst("code"));
        myList.add(myMap.getFirst("dept"));
        myList.add(myMap.getFirst("has_lab"));
        */
        List<CourseDescription> myCouseDescription;
        if(myMap.get("code")!=null) {
            for (int i = 0; i < myMap.get("prereq").size(); i++) {
                if(!myMap.get("prereq").get(i).equals("")) {
                    myList.add(myMap.get("prereq").get(i));
                }
            }
        }
        String id = myMap.getFirst("id");
        String name = myMap.getFirst("name");
        String code = myMap.getFirst("code");
        String dept = myMap.getFirst("dept");
        String has_lab = "";
        if(myMap.get("has_lab") == null){
            has_lab = "0";
        }else{
            has_lab = "1";
        }
        //String[] prereq= myMap.get("prereq").toArray(new String[0]);

        courseRepository.delete( courseRepository.findCourseById(id) );
        courseRepository.save( new Course(name, code, dept, has_lab, myList) );
        //myList.add(myMap.get("prereq").get(0) );
        //myList.add(myMap.get("prerq").size() + "");
        //        myList.add(code);
        //        myList.add(dept);
        //        myList.add(has_lab);
        myList.add(name);
        model.addAttribute("value", myList);
        //return "test";
        return "redirect:/show/course";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String showAddCourse(Model model){
        return "/add/AddCourse";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String addCourse(Model model){
        return "/add/AddCourse";
    }
    
}
