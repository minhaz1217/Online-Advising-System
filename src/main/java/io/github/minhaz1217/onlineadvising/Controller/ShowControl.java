/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.Controller;

import io.github.minhaz1217.onlineadvising.Interface.CourseRepository;
import io.github.minhaz1217.onlineadvising.Interface.StudentRepository;
import io.github.minhaz1217.onlineadvising.models.Course;
import io.github.minhaz1217.onlineadvising.models.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 *
 * @author 2016-1-60-134
 */

@Controller
@RequestMapping("/show")
public class ShowControl {
    CourseRepository courseRepository;
    StudentRepository studentRepository;
    public ShowControl(CourseRepository courseRepository, StudentRepository studentRepository){
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/course")
    public String showCourse(Model model){
        List<Course> myCourses = courseRepository.findAll();
        model.addAttribute("course", myCourses);
        return "ShowCourse";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/student")
    public String showStudents(Model model){
        List<Student> myStudents = studentRepository.findAll();
        model.addAttribute("student", myStudents);
        return "ShowStudent";
    }
    
}
