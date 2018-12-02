package io.github.minhaz1217.onlineadvising.Controller;

import io.github.minhaz1217.onlineadvising.Interface.CourseRepository;
import io.github.minhaz1217.onlineadvising.models.Course;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.Time;

@Controller
public class ViewController implements ErrorController{

    @RequestMapping(method = RequestMethod.GET, value = "/error2")
    public String showError(){
        return "ERROR_PAGE";
    }
    @RequestMapping(method = RequestMethod.GET, value = "/error")
    public String showError2(){
        return "ERROR_PAGE";
    }
    @RequestMapping(value = "/courses")
    public String showCourses(Model model, CourseRepository courseRepos){
        CourseRepository courseRepository = courseRepos;
        model.addAttribute("course", courseRepository.findAll());
        return "course";

    }

    @RequestMapping(method = RequestMethod.GET, value = "/add/course")
    public String addCourse(Model model, Course course, Pageable pageable){
        return "/";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
