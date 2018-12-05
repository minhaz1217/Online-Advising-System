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
import java.util.Arrays;
import java.util.List;

import org.h2.util.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Minhaz
 */


class Pair<K,V>{
    public K key;
    public V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}

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
        //System.out.println(available.size());
        //available has just the course names
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

                if(seats.equals("0")){
                    continue;
                }

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
                mySections.add(new Pair(code+"_"+section, message));
            }
            seatPlan.add(new SeatPlan(available.get(i), mySections));
            availableList.clear();
        }

        //( Section : time : WEEKDAY : class room : instructor : seats )
        //System.out.println(seatPlan.size());

        for(int i=0;i<seatPlan.size();i++){

        }

        model.addAttribute("seatplan", seatPlan);
        return  "show/ShowAvailable";
        //return seatPlan;
    }

    public int convDay(char c){
        if(c == 'S' || c == 's'){
            return 1;
        }else if(c == 'M' || c == 'm'){
            return 2;
        }else if(c == 'T' || c == 't'){
            return 3;
        }else if(c == 'W' || c == 'w'){
            return 4;
        }else if(c == 'R' || c == 'r'){
            return 5;
        }
        return 0;
    }

    private int convertTimeTo24HourFormat(String time){
        int t = Integer.parseInt(time);
        if(t >= 1 && t<=7){
            return t+12;
        }
        return t;
    }
    private boolean isConflicting(String course1Time, String course2Time){
        // 11:50 - 01:20
        int     c1StartHour = convertTimeTo24HourFormat(course1Time.split("-")[0].trim().split(":")[0].trim()),
                c1StartMin = Integer.parseInt(course1Time.split("-")[0].trim().split(":")[1].trim()),

                c1EndHour = convertTimeTo24HourFormat(course1Time.split("-")[1].trim().split(":")[0].trim()),
                c1EndMin = Integer.parseInt(course1Time.split("-")[1].trim().split(":")[1].trim()),

                c2StartHour = convertTimeTo24HourFormat(course2Time.split("-")[0].trim().split(":")[0].trim()),
                c2StartMin = Integer.parseInt(course2Time.split("-")[0].trim().split(":")[1].trim()),

                c2EndHour = convertTimeTo24HourFormat(course2Time.split("-")[1].trim().split(":")[0].trim()),
                c2EndMin = Integer.parseInt(course2Time.split("-")[1].trim().split(":")[1].trim());
        // checking c2's starting

        if(c2StartHour >= c1StartHour && c2StartHour <= c1EndHour){
            if(c2StartHour == c1StartHour && c2StartMin < c1StartMin){
                return false;
            }else if(c2StartHour == c1EndHour && c2StartMin > c1EndMin){
                return false;
            }else{
                return true;
            }
        }
        // checking c2's ending
        if(c2EndHour >= c1StartHour && c2EndHour <= c1EndHour){
            if(c2EndHour == c1StartHour && c2EndMin < c1StartMin){
                return false;
            }else if(c2EndHour == c1EndHour && c2EndMin > c1EndMin){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }


    @RequestMapping(method = RequestMethod.POST, value = "available/{student}")
    //@ResponseBody
    public String takeCourse(@PathVariable String student, @RequestParam MultiValueMap<String, String> myMap, Model model, RedirectAttributes redirectAttributes){

        String id = myMap.getFirst("id");
        List<Pair<String , String>> pair = new ArrayList<Pair<String, String>>();
        List<String> retrunList = new ArrayList<>();
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
                    retrunList.add(code+"_"+section);
                }
            }
        }

        //DETECTION: credit count check{
        if(credit < 9){
            redirectAttributes.addFlashAttribute("msg_error", "Need to take at least 9 credits.");
            redirectAttributes.addFlashAttribute("take", retrunList);
            return "redirect:/student/available/"+id;
        }else if(credit > 15){
            redirectAttributes.addFlashAttribute("msg_error", "You can take maximum 15 credits.");
            redirectAttributes.addFlashAttribute("take", retrunList);
            return "redirect:/student/available/"+id;
        }

        // all the taken courses
        List<CourseDescription> fullList = new ArrayList<>();
        for(int i=0;i<pair.size();i++){
            fullList.addAll( descriptionRepository.findCourseDescriptionsByCodeAndSec(pair.get(i).getKey(), pair.get(i).getValue()) );
        }

        //DETECTION: ENOUGH SEATS
        String seatErrorMessage = "Not enough seats in: ";
        int flagSeat = 0;
        for(int i=0;i<fullList.size();i++){
            if(fullList.get(i).getSeats().equals("0")){
                seatErrorMessage = seatErrorMessage + fullList.get(i).getCode() + "("+fullList.get(i).getSec()+") ";
                flagSeat = 1;
            }
        }
        if(flagSeat == 1){
            redirectAttributes.addFlashAttribute("msg_error", seatErrorMessage);
            redirectAttributes.addFlashAttribute("take", retrunList);
            return "redirect:/student/available/"+id;
        }



        //DETECTION: took subject that has a lab but didn't take the lab
        String needLab = "Need to take lab for: ", code = "", needMain = "You need to take the course : ";
        List<String> sendList = new ArrayList<>();
        String pairToString = "";
        //[CSE350=01, CSE360=02, CSE365=01, CSE365LAB=01]
        for(int i=0;i<pair.size();i++){
            pairToString += pair.get(i).getKey()+"="+pair.get(i).getValue() + ", ";
        }
        //System.out.println(pairToString);
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
                //System.out.println(pairToString.indexOf(mainCourseCode) + " " + pairToString.lastIndexOf(mainCourseCode));
                if(pairToString.indexOf(mainCourseCode) == pairToString.lastIndexOf(mainCourseCode)){
                    needMain = needMain +  mainCourseCode + " ";
                    flagMain = 1;
                }
            }
            System.out.println(pairToString);
            if(sendList.size() == 3){
                List<Course> courses = courseRepository.findCoursesByCodeOrCodeOrCode(sendList.get(0), sendList.get(1), sendList.get(2));
                for(Course cc : courses){
                    if(cc.getHas_lab().equals("1")){
                        if(!pairToString.contains(cc.getCode()+"LAB")){
                            sendList.add(cc.getCode());
                            needLab = needLab + cc.getCode()+" ";
                            System.out.println("HIT 1");
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
                        System.out.println("HIT 2");
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
                    System.out.println("HIT 3");
                    flagLab = 1;
                }
            }
        }
        if(flagLab == 1){
            redirectAttributes.addFlashAttribute("msg_error", needLab);
            redirectAttributes.addFlashAttribute("take", retrunList);
            return "redirect:/student/available/"+id;
        }else if(flagMain == 1){
            redirectAttributes.addFlashAttribute("msg_error", needMain);
            redirectAttributes.addFlashAttribute("take", retrunList);
            return "redirect:/student/available/"+id;
        }


        // detect time collusion


        
        String day, time,startTime, endTime,checkDay, checkTime,checkCode,errMessage="Conflict in: ";
        int flagCollision = 0;
        for(int i=0;i<fullList.size();i++){
            day = fullList.get(i).getDay();
            time = fullList.get(i).getTime();
            startTime = time.split("-")[0].trim();
            endTime = time.split("-")[1].trim();
            code = fullList.get(i).getCode();
            for(int j=i+1;j<fullList.size();j++){
                checkDay = fullList.get(j).getDay();
                checkTime = fullList.get(j).getTime();
                checkCode = fullList.get(j).getCode();
                if(day.length() == 2){
                    //their day same but code not same so they might colide (a course can have 2 days in different weekday
                    if(checkDay.contains( day.charAt(0)+"") && !code.equals(checkCode)){
                        if(isConflicting(time, checkTime) == true){
                            errMessage += code + " vs " + checkCode + " ";
                            flagCollision = 1;
                        }
                    }
                    if(checkDay.contains(day.charAt(1)+"") && !code.equals(checkCode)){
                        if(isConflicting(time, checkTime) == true){
                            errMessage += code + " vs " + checkCode + " ";
                            flagCollision = 1;
                        }

                    }
                }else{
                    if(checkDay.contains(day) && !code.equals(checkCode) ){
                        if(isConflicting(time, checkTime) == true){
                            errMessage += code + " vs " + checkCode + " ";
                            flagCollision = 1;
                        }
                    }
                }
            }
        }
        if(flagCollision == 1){
            redirectAttributes.addFlashAttribute("msg_error", errMessage);
            redirectAttributes.addFlashAttribute("take", retrunList);
            return "redirect:/student/available/"+id;

        }


        //everything is fine so the student can take this course
        Student myStudent = studentRepository.findStudentByStudentCode(id);
        myStudent.setAdvising(new ArrayList<>(fullList));
        //studentRepository.delete(studentRepository.findStudentByStudentCode(id));
        studentRepository.save(myStudent);
        //making all the courses' seat one less
        for(int i=0;i<fullList.size();i++){
            fullList.get(i).setSeats( (Integer.parseInt(fullList.get(i).getSeats())-1)+"" );
            descriptionRepository.save(fullList.get(i));
        }

        return "redirect:/student/show/"+id;
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
    public String studentRemove(@RequestParam("id") String id, RedirectAttributes redirectAttributes){
        Student student = studentRepository.findStudentById(id);
        String std_id = student.getStudentId();
        studentRepository.delete(student);
        redirectAttributes.addFlashAttribute("msg_success", "Successfully deleted: "+ std_id);
        return "redirect:/show/student";
    }

    @RequestMapping(method = RequestMethod.POST, value = "edit")
    public String studentEdit(@RequestParam("id") String id, Model model){
        //Course course = courseRepository.findCourseByCode("CSE411");
        Student student = this.studentRepository.findStudentById(id);
        model.addAttribute("student", student);
        return "edit/EditStudent";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String studentUpdate(@RequestParam MultiValueMap<String, String> myMap, RedirectAttributes redirectAttributes){
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
        redirectAttributes.addFlashAttribute("msg_success", "Successfully updated: "+ studentId);
        return "redirect:/show/student";
    }

    @RequestMapping(method = RequestMethod.GET, value = "add")
    public String showAddStudent(Model model){
        return "add/AddStudent";
    }
    @RequestMapping(method = RequestMethod.POST, value = "add")
    public String addStudent(@RequestParam MultiValueMap<String, String> myMap,  RedirectAttributes redirectAttributes){

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

        redirectAttributes.addFlashAttribute("msg_success", "Successfully added: "+ studentId);
        return "redirect:/show/student";
    }



    @RequestMapping(method = RequestMethod.GET, value = "/select")
    public String selectStudent(){
        return "SelectStudent";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/select")
    public String selectStudentPost(@RequestParam MultiValueMap<String, String> myMap, RedirectAttributes redirectAttributes){
        //System.out.println(id);
        String year = myMap.getFirst("idYear");
        String semister = myMap.getFirst("idSemister");
        String dept = myMap.getFirst("idDept");
        String roll = myMap.getFirst("idRoll");
        if( !(StringUtils.isNumber(year) && StringUtils.isNumber(dept) && StringUtils.isNumber(semister) && StringUtils.isNumber(roll)) ){
            redirectAttributes.addFlashAttribute("msg_error", "The id can only consists of numbers.");


            redirectAttributes.addFlashAttribute("idYear", year);
            redirectAttributes.addFlashAttribute("idSemister", semister);
            redirectAttributes.addFlashAttribute("idDept", dept);
            redirectAttributes.addFlashAttribute("idRoll", roll);
            return "redirect:/student/select";
        }
        String id = year + "-" + semister + "-" +dept + "-" + roll;
        return "redirect:/student/available/"+id;
    }


    //TODO: fix this
    @RequestMapping(method = RequestMethod.GET, value = "show/{id}")
    public String showStudent(@PathVariable String id, Model model){
        Student student = studentRepository.findStudentByStudentCode(id);
        model.addAttribute("dashStudent", student);
        return "show/ShowStudentDashboard";
    }


    @RequestMapping(method = RequestMethod.GET, value = "show")
    public String showStudentSingle(Model model){
        Student student = studentRepository.findStudentByStudentCode("2016-1-60-100");
        model.addAttribute("dashStudent", student);
        return "show/ShowStudentDashboard";
    }






}
