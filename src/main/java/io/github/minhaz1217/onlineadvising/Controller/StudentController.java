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

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Minhaz
 */

class SeatPlan{
    String code;
    List<Pair<String , String>> sections;

    public SeatPlan(String code, List<Pair<String, String>> sections) {
        this.code = code;
        this.sections = sections;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Pair<String, String>> getSections() {
        return sections;
    }

    public void setSections(List<Pair<String, String>> sections) {
        this.sections = sections;
    }
}


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
    //@ResponseBody
    public String findRemainingCourse(@PathVariable String student, Model model){
        model.addAttribute("student", student);
        List<Course> fullCourse = courseRepository.findAll();
        List<CourseExtended> myCourses = (studentRepository.findStudentByStudentCode(student).getTaken());
        List<String> available = new ArrayList<>();
        List<String> myTaken = new ArrayList<>();

        //getting the code name for the taken courses
        for(int i=0;i<myCourses.size();i++){
            myTaken.add(myCourses.get(i).getCode());
        }


        //checking prerequisite
        int flag = 0;
        for(int i=0;i<fullCourse.size();i++) {
            Course curr = fullCourse.get(i);
            //he didn't take this course and this isn't a lab course
            if (myTaken.indexOf(curr.getCode()) == -1) {
                flag = 1;
                for (int j = 0; j < curr.getPrerequisite().size(); j++) {
                    if (myTaken.indexOf(curr.getPrerequisite().get(j)) == -1) {
                        flag = 0;
                        break;
                    }
                }
                if (flag == 1) {

                    available.add(curr.getCode());
                    // if this course has lab then we need to add this course's lab with it
                    if(curr.getHas_lab().equals("1")){
                        available.add(curr.getCode()+"LAB");
                    }
                }
            }
        }
        System.out.println(available.size());

        List<CourseDescription> availableList = new ArrayList<CourseDescription>();
        List<SeatPlan> seatPlan = new ArrayList<>();
        String section = "", seats = "", code="", instructor = "", day = "", time = "", room= "", message = "";

        for(int i=0;i<available.size();i++){
            availableList.addAll( descriptionRepository.findCourseDescriptionsByCodeOrderBySecAsc(available.get(i)) );
            List<Pair<String, String>> mySections = new ArrayList<Pair<String, String>>();

            for (int ai=0;ai<availableList.size();ai++) {
                CourseDescription cd = availableList.get(ai);
                code = cd.getCode();
                section = cd.getSec();
                time = cd.getTime();
                day = cd.getDay();
                room = cd.getRoom();
                seats = cd.getSeats();
                instructor = cd.getInstructor();
                //empty section means we've already added this section
                if(section.equals("")){
                    continue;
                }
                if(instructor.equals("")){
                    instructor = "TBA";
                }
                message = section + "("+ seats +") -> [" + instructor + "] (" + day + ", " + time + ", " + room + ")";
                if(day.length() == 1){
                    for(int j=ai+1;j<availableList.size();j++){
                        if(availableList.get(j).getCode().equals(code) && availableList.get(j).getSec().equals(section)){
                            availableList.get(j).setSec("");
                            cd = availableList.get(j);
                            time = cd.getTime();
                            day = cd.getDay();
                            room = cd.getRoom();
                            message = message + " (" + day + ", " + time + ", " + room + ")";
                            break;
                        }
                    }
                }
                mySections.add(new Pair(section, message));
            }
            seatPlan.add(new SeatPlan(available.get(i), mySections));
            availableList.clear();
        }

        //( Section : time : WEEKDAY : class room : instructor : seats )
        //System.out.println(seatPlan.size());
        model.addAttribute("seatplan", seatPlan);
        return  "/show/ShowAvailable";
        //return seatPlan;
    }

    @RequestMapping(method = RequestMethod.POST, value = "available/{student}")
    //@ResponseBody
    public String takeCourse(@PathVariable String student, @RequestParam MultiValueMap<String, String> myMap, Model model, RedirectAttributes redirectAttributes){

        String id = myMap.getFirst("id");
        List<Pair<String , String>> pair = new ArrayList<Pair<String, String>>();
        int credit = 0;
        if(myMap.get("takeCode")!=null){
            for(int i=0;i<myMap.get("takeCode").size();i++){
                String full = myMap.get("takeCode").get(i);
                if(!full.equals("0")){
                    String code = full.substring(0,full.lastIndexOf("_"));
                    String section = full.substring(full.lastIndexOf('_')+1 );
                    if(code.contains("LAB")){
                        credit+=1;
                    }else{
                        credit += 3;
                    }
                    //System.out.println(code + " " + section);
                    pair.add(new Pair(code, section));
                }
            }
        }

        //DETECTION: credit count check{
        if(credit < 9){
            redirectAttributes.addFlashAttribute("error", "Need to take at least 9 credits.");
            return "redirect:/student/available/"+id;
        }else if(credit > 15){

            redirectAttributes.addFlashAttribute("error", "You can take maximum 15 credits.");
            return "redirect:/student/available/"+id;
        }


        //DETECTION: took subject that has a lab but didn't take the lab
        String needLab = "Need to take lab for: ", code = "", needMain = "You need to take the course : ";
        List<String> sendList = new ArrayList<>();
        String pairToString = pair.toString();
        int flagLab =0, flagMain =0;
        for(int i=0;i<pair.size();i++){
            code = pair.get(i).getKey();

            //this is a normal course
            if(!code.contains("LAB")){
                sendList.add(code);
            }else{
                // this is a lab course
                String mainCourseCode = code.substring(0,code.lastIndexOf("LAB"));
                //checking if the CSE411 and CSE411LAB doesn't have same index
                //searching from front and from end, if they give different result then both the course and the lab is  there, if they won't then they aren't there
                System.out.println(pairToString.indexOf(mainCourseCode) + " " + pairToString.lastIndexOf(mainCourseCode));
                if(pairToString.indexOf(mainCourseCode) == pairToString.lastIndexOf(mainCourseCode)){
                    needMain = needMain +  mainCourseCode + " ";
                    flagMain = 1;
                }
            }
            if(sendList.size() == 3){
                List<Course> courses = courseRepository.findCoursesByCodeOrCodeOrCode(sendList.get(0), sendList.get(1), sendList.get(2));
                for(Course cc : courses){
                    if(cc.getHas_lab().equals("1")){
                        if(!pairToString.contains(cc.getCode()+"LAB")){
                            sendList.add(cc.getCode());
                            needLab = needLab + cc.getCode()+" ";
                            flagLab = 1;
                        }
                    }
                }
            }
        }
        if(sendList.size() == 2){
            List<Course> courses = courseRepository.findCoursesByCodeOrCode(sendList.get(0), sendList.get(1));
            for(Course cc : courses){
                if(cc.getHas_lab().equals("1")){
                    if(!pairToString.contains(cc.getCode()+"LAB")){
                        sendList.add(cc.getCode());
                        needLab = needLab + cc.getCode()+" ";
                        flagLab = 1;
                    }
                }
            }
        }else if(sendList.size() == 1){
            Course cc= courseRepository.findCourseByCode(sendList.get(0));
            if(cc.getHas_lab().equals("1")){
                if(!pairToString.contains(cc.getCode()+"LAB")){
                    sendList.add(cc.getCode());
                    needLab = needLab + cc.getCode()+" ";
                    flagLab = 1;
                }
            }
        }

        if(flagLab == 1){
            redirectAttributes.addFlashAttribute("error", needLab);
            return "redirect:/student/available/"+id;
        }else if(flagMain == 1){
            redirectAttributes.addFlashAttribute("error", needMain);
            return "redirect:/student/available/"+id;
        }







        redirectAttributes.addFlashAttribute("error", "THERE WAS AN ERROR");
        return "redirect:/student/available/"+id;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/test")
    @ResponseBody
    public List<Course> hi(){
        return courseRepository.findCoursesByCodeOrCodeOrCode("ENG101", "CSE109", "CSE411");
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

                String cdCode = myMap.get("cdCode").get(i).toUpperCase();
                if(myMap.get("cdSec").get(i).equals("")) {
                    if(!cdCode.equals("")){
                        myCourseExtended.add(new CourseExtended( myMap.get("cdCode").get(i).toUpperCase() ));
                    }
                }else{
                    String cdSec = myMap.get("cdSec").get(i).toUpperCase();
                    List<CourseDescription> courseDescription = descriptionRepository.findCourseDescriptionsByCodeAndSec(cdCode, cdSec);
                    if(courseDescription.size() >= 1){
                        myCourseExtended.add(new CourseExtended( cdCode, cdSec ) );
                    }else{
                        Student student = studentRepository.findStudentById(id);
                        for(int j=0;j<student.getTaken().size();j++){
                            if(student.getTaken().get(i).getCode().equals(cdCode)){
                                myCourseExtended.add(student.getTaken().get(i));
                                break;
                            }
                        }
                    }
                }
            }
        }

        this.studentRepository.delete(this.studentRepository.findStudentById(id));
        this.studentRepository.save(new Student(firstName, lastName, email,studentId, myCourseExtended));
        return "redirect:/show/student";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add")
    public String showAddStudent(Model model){
        return "/add/AddStudent";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public String addStudent(@RequestParam MultiValueMap<String, String> myMap,  Model model){

        String firstName = (myMap.getFirst("firstName"));
        String lastName = (myMap.getFirst("lastName"));
        String email = (myMap.getFirst("email"));
        String studentId = (myMap.getFirst("studentId"));
        ArrayList<CourseExtended> myCourseExtended = new ArrayList<>();

        if(myMap.get("cdCode")!=null) {
            for (int i = 0; i < myMap.get("cdCode").size(); i++) {
                String cdCode = myMap.get("cdCode").get(i).toUpperCase();
                if(myMap.get("cdSec").get(i).equals("")) {
                    if(!cdCode.equals("")){
                        myCourseExtended.add(new CourseExtended( myMap.get("cdCode").get(i).toUpperCase() ));
                    }
                }else{
                    String cdSec = myMap.get("cdSec").get(i).toUpperCase();
                    List<CourseDescription> courseDescription = descriptionRepository.findCourseDescriptionsByCodeAndSec(cdCode, cdSec);
                    if(courseDescription.size() >= 1){
                        myCourseExtended.add(new CourseExtended( cdCode, cdSec ) );
                    }
                }
            }
        }
        this.studentRepository.save(new Student(firstName, lastName, email,studentId, myCourseExtended));

        return "redirect:/show/student";
    }
}
