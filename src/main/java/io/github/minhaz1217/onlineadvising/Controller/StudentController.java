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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.*;

/**
 *
 * @author Minhaz
 */


@RestController
@RequestMapping("/student")
public class StudentController {
       
    protected final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentController(StudentRepository studentRepository, CourseRepository courseRepository){
        this.studentRepository= studentRepository;
        this.courseRepository = courseRepository;
    }
    //TODO: Remove this /all gate while deplying
    @GetMapping("/all")
    public List<Student> getAll(){
        List<Student> students = this.studentRepository.findAll();
        return students;
    }

    @RequestMapping(method = RequestMethod.GET, value = "id/{student}")
    public Student getStudent(@PathVariable String student){
        //List<Course> fullCourse = courseRepository.findAll();
        return studentRepository.findStudentByStudentCode(student);
    }

    @RequestMapping(method = RequestMethod.GET, value = "available/{student}")
    public List<String> findRemainingCourse(@PathVariable String student){
        List<Course> fullCourse = courseRepository.findAll();
        List<String> myTaken = Arrays.asList(studentRepository.findStudentByStudentCode(student).getTaken());
        List<String> available = new ArrayList<>();
        int flag = 0;
        for(int i=0;i<fullCourse.size();i++){
            Course curr = fullCourse.get(i);

            if(myTaken.indexOf(curr.getCourse_code()) == -1){
                flag = 1;
                for(int j=0;j<curr.getPrerequisite().size();j++){
                    if(myTaken.indexOf(curr.getPrerequisite().get(j)) == -1){
                        flag = 0;
                        break;
                    }
                }
                if(flag == 1){
                    available.add(curr.getCourse_code());
                }
            }
        }
        return available;

    }
}
