package io.github.minhaz1217.onlineadvising.Controller;

import io.github.minhaz1217.onlineadvising.Interface.CourseRepository;
import io.github.minhaz1217.onlineadvising.models.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.sql.Time;

@Controller
public class ViewController {

    @RequestMapping(value = "/")
    public String showIndex(Model model){
        model.addAttribute("TIME", "HI THERE");
        return "index";
    }

    @RequestMapping(value = "/courses")
    public String showCourses(Model model, CourseRepository courseRepos){
        CourseRepository courseRepository = courseRepos;
        model.addAttribute("course", courseRepository.findAll());
        return "course";

    }

    @RequestMapping(value = "/hello")
    public String showHello(Model model){
        model.addAttribute("name", "HI");
        return "hello";
    }
    @RequestMapping(value = "/hello2")
    public String showHello2(Model model){
        model.addAttribute("name", "Minhaz");
        return "jsptest";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/add/course")
    public String addCourse(Model model, Course course, Pageable pageable){
        return "addCourse";
    }
}
