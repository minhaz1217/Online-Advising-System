package io.github.minhaz1217.onlineadvising.Controller;


import io.github.minhaz1217.onlineadvising.Interface.CourseDescriptionRepository;
import io.github.minhaz1217.onlineadvising.models.Course;
import io.github.minhaz1217.onlineadvising.models.CourseDescription;
import io.github.minhaz1217.onlineadvising.models.CourseExtended;
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


    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String showCourseDescriptions(Model model){
        return "redirect:/show/list";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/remove")
    public String courseDelete(@RequestParam("id") String id){
        courseDescriptionRepository.delete(courseDescriptionRepository.findCourseDescriptionById(id));
        return "redirect:/show/list";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit")
    public String courseEdit(@RequestParam("id") String id, Model model){
        //Course course = courseRepository.findCourseByCode("CSE411");
        CourseDescription courseDescription = this.courseDescriptionRepository.findCourseDescriptionById(id);
        model.addAttribute("course", courseDescription);
        return "/edit/EditCourseDescription";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public String courseUpdate(@RequestParam MultiValueMap<String, String> myMap, Model model){
        ArrayList<String> myList = new ArrayList<>();

        String id = myMap.getFirst("id");
        String code = myMap.getFirst("code");
        String sec = myMap.getFirst("sec");
        String time = myMap.getFirst("time");
        String day = myMap.getFirst("day");
        String room = myMap.getFirst("room");
        String seats = myMap.getFirst("seats");
        String instructor = myMap.getFirst("instructor");



        this.courseDescriptionRepository.delete(this.courseDescriptionRepository.findCourseDescriptionById(id));
        this.courseDescriptionRepository.save(new CourseDescription(code, sec, time , day, room, seats, instructor));
        return "redirect:/show/list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "test/{code}/{sec}/{day}")
    @ResponseBody
    public String testingThing(@PathVariable String code, @PathVariable String sec, @PathVariable String day){
        return "HELLO";
    }

}
