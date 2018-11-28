package io.github.minhaz1217.onlineadvising.Controller;


import io.github.minhaz1217.onlineadvising.Interface.CourseDescriptionRepository;
import io.github.minhaz1217.onlineadvising.models.Course;
import io.github.minhaz1217.onlineadvising.models.CourseDescription;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/coursedescription")
public class CourseDescriptionController {


    CourseDescriptionRepository courseDescriptionRepository;

    public CourseDescriptionController(CourseDescriptionRepository courseDescriptionRepository){
        this.courseDescriptionRepository = courseDescriptionRepository;
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/remove/{id}")
    public String courseDelete(@PathVariable String id){
        courseDescriptionRepository.delete(courseDescriptionRepository.findCourseDescriptionById(id));
        return "redirect:/show/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/edit/")
    @ResponseBody
    public List<CourseDescription> courseEdit(Model model){
        //return "ShowCourse";
        List<CourseDescription> myList = this.courseDescriptionRepository.findCourseDescriptionsByCode("CSE411");
        return myList;
        //CourseDescription course = courseDescriptionRepository.findCourseById(id);
        //model.addAttribute("course", course);
        //return "/edit/EditCourseDescription";
    }
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String courseUpdate(@RequestParam MultiValueMap<String, String> myMap, Model model){
        //courseRepository.delete(courseRepository.findCourseById(id));
        //CourseDescription course = courseDescriptionRepository.findCourseDescriptionsByCode("CSE411");
        //model.addAttribute("course", course);
        ArrayList<String> myList = new ArrayList<>();
        /*
        myList.add(myMap.values().toString());
        myList.add(myMap.getFirst("name"));
        myList.add(myMap.getFirst("code"));
        myList.add(myMap.getFirst("dept"));
        myList.add(myMap.getFirst("has_lab"));
        */
        if(myMap.get("prereq")!=null) {
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

        //courseDescriptionRepository.delete( courseRepository.findCourseById(id) );
        //courseDescriptionRepository.save( new Course(name, code, dept, has_lab, myList) );
        //myList.add(myMap.get("prereq").get(0) );
        //myList.add(myMap.get("prerq").size() + "");
        //        myList.add(code);
        //        myList.add(dept);
        //        myList.add(has_lab);
        myList.add(name);
        model.addAttribute("value", myList);
        //return "test";
        return "redirect:/show/list";
    }

}
