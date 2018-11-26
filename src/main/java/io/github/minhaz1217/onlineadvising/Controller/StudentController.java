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

    public StudentController(StudentRepository studentRepository){
        this.studentRepository= studentRepository;
    }
    //TODO: Remove this /all gate while deplying
    @GetMapping("/all")
    public List<Student> getAll(){
        List<Student> students = this.studentRepository.findAll();
        return students;
    }

    @RequestMapping(method = RequestMethod.GET, value = "id/{student}")
    public Student getStudent(@PathVariable String student){
        return studentRepository.findStudentByStudentCode(student);
    }
}
