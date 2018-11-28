/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.minhaz1217.onlineadvising.Controller;

import io.github.minhaz1217.onlineadvising.Interface.CourseDescriptionRepository;
import io.github.minhaz1217.onlineadvising.Interface.CourseRepository;
import io.github.minhaz1217.onlineadvising.Interface.StudentRepository;
import io.github.minhaz1217.onlineadvising.models.Course;
import io.github.minhaz1217.onlineadvising.models.CourseDescription;
import io.github.minhaz1217.onlineadvising.models.CourseExtended;
import io.github.minhaz1217.onlineadvising.models.Student;

import java.lang.reflect.Array;
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
    private final CourseDescriptionRepository descriptionRepository;

    public StudentController(StudentRepository studentRepository, CourseRepository courseRepository, CourseDescriptionRepository descriptionRepository){
        this.studentRepository= studentRepository;
        this.courseRepository = courseRepository;
        this.descriptionRepository = descriptionRepository;
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
        List<CourseExtended> myCourses = (studentRepository.findStudentByStudentCode(student).getTaken());
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
        String firstName = (myMap.getFirst("firstName"));
        String lastName = (myMap.getFirst("lastName"));
        String email = (myMap.getFirst("email"));
        String studentId = (myMap.getFirst("studentId"));


        ArrayList<CourseExtended> myCourseExtended = new ArrayList<>();
        if(myMap.get("code")!=null) {
            for (int i = 0; i < myMap.get("code").size(); i++) {
                if(!myMap.get("code").get(i).equals("")) {
                    myCourseExtended.add(new CourseExtended( myMap.get("code").get(i).toUpperCase() ));
                }
            }
        }
        if(myMap.get("cdCode")!=null) {
            for (int i = 0; i < myMap.get("cdCode").size(); i++) {
                if(myMap.get("cdSec").get(i).equals("")) {
                    myCourseExtended.add(new CourseExtended( myMap.get("cdCode").get(i).toUpperCase() ));
                }else{
                    String cdCode = myMap.get("cdCode").get(i).toUpperCase();
                    String cdSec = myMap.get("cdSec").get(i).toUpperCase();
                    List<CourseDescription> courseDescription = descriptionRepository.findCourseDescriptionsByCodeAndSec(cdCode, cdSec);
                    if(courseDescription.size() >= 1){
                        myCourseExtended.add(new CourseExtended( cdCode, cdSec ) );
                    }
                }
            }
        }




        this.studentRepository.delete(this.studentRepository.findStudentById(id));
        this.studentRepository.save(new Student(firstName, lastName, email,studentId, myCourseExtended));
        return "redirect:/show/student";
    }
}
