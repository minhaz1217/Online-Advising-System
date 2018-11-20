package io.github.minhaz1217.onlineadvising.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Time;

@Controller
public class ViewController {

    @RequestMapping(value = "/")
    public String showCourse(Model model){
        model.addAttribute("TIME", "HI");
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


}
