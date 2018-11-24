package io.github.minhaz1217.onlineadvising.Controller;


import io.github.minhaz1217.onlineadvising.Interface.UserRepository;
import io.github.minhaz1217.onlineadvising.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET , value = "/signup")
    public String signup(Model model, User user, Pageable pageable){
        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public String signupPost(@Valid User user, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "signup";
        }else{
        }
        model.addAttribute("success", "SUCCESSFULLY SIGNEDUP");
        return "signup";
    }
}
