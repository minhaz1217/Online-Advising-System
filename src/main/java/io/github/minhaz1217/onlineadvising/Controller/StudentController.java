/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.Controller;

import io.github.minhaz1217.onlineadvising.Interface.CourseRepository;
import io.github.minhaz1217.onlineadvising.Interface.StudentRepository;
import io.github.minhaz1217.onlineadvising.models.Course;
import io.github.minhaz1217.onlineadvising.models.CourseDescription;
import io.github.minhaz1217.onlineadvising.models.CourseExtended;
import io.github.minhaz1217.onlineadvising.models.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Minhaz
 */

@Controller
@RequestMapping("/student")
public class StudentController {
       
    protected final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentController(StudentRepository studentRepository, CourseRepository courseRepository){
        this.studentRepository= studentRepository;
        this.courseRepository = courseRepository;
    }
    //TODO: Remove this /all gate while deplying
    @RequestMapping(method = RequestMethod.GET, value = "/all")
    @ResponseBody
    public List<Student> getAll(){
        List<Student> students = this.studentRepository.findAll();
        return students;
    }

    @RequestMapping(method = RequestMethod.GET, value = "id/{student}")
    @ResponseBody
    public Student getStudent(@PathVariable String student){
        //List<Course> fullCourse = courseRepository.findAll();
        return studentRepository.findStudentByStudentCode(student);
    }

    @RequestMapping(method = RequestMethod.GET, value = "available/{student}")
    @ResponseBody
    public List<String> findRemainingCourse(@PathVariable String student){
        List<Course> fullCourse = courseRepository.findAll();
        List<CourseExtended> myCourses = Arrays.asList(studentRepository.findStudentByStudentCode(student).getTaken());
        List<String> available = new ArrayList<>();
        List<String> myTaken = new ArrayList<>();
        for(int i=0;i<myCourses.size();i++){
            myTaken.add(myCourses.get(i).getCode());
        }
        int flag = 0;
        for(int i=0;i<fullCourse.size();i++){
            Course curr = fullCourse.get(i);

            if(myTaken.indexOf(curr.getCode()) == -1){
                flag = 1;
                for(int j=0;j<curr.getPrerequisite().size();j++){
                    if(myTaken.indexOf(curr.getPrerequisite().get(j)) == -1){
                        flag = 0;
                        break;
                    }
                }
                if(flag == 1){
                    available.add(curr.getCode());
                }
            }
        }
        return available;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String showStudent(Model model){
        return "redirect:/show/student";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/remove")
    public String studentRemove(@RequestParam("id") String id){
        studentRepository.delete(studentRepository.findStudentById(id));
        return "redirect:/show/student";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit")
    public String studentEdit(@RequestParam("id") String id, Model model){
        //Course course = courseRepository.findCourseByCode("CSE411");
        Student student = this.studentRepository.findStudentById(id);
        model.addAttribute("student", student);
        return "/edit/EditStudent";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String studentUpdate(@RequestParam MultiValueMap<String, String> myMap, Model model){
        ArrayList<String> myList = new ArrayList<>();
        String id = myMap.getFirst("id");
/*

        String code = myMap.getFirst("code");
        String sec = myMap.getFirst("sec");
        String time = myMap.getFirst("time");
        String day = myMap.getFirst("day");
        String room = myMap.getFirst("room");
        String seats = myMap.getFirst("seats");
        String instructor = myMap.getFirst("instructor");
        String has_lab = "";
        */
        this.studentRepository.delete(this.studentRepository.findStudentById(id));
        //this.studentRepository.save(new CourseDescription(code, sec, time , day, room, seats, instructor));
        return "redirect:/show/student";
    }
}
