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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String signupPost(@Valid User user,
                             BindingResult bindingResult,
                             Model model,
                             @RequestParam("confirm_pass") String conPass,
                             @RequestParam("admin_pass") String adminPass){
        if(bindingResult.hasErrors()){
            model.addAttribute("success", "UNSUCCESSFUL SIGNEDUP");
            return "signup";
        }else if(!user.getPassword().equals(conPass)){
            model.addAttribute("err_confirm_pass", "Password Didn't Match, Try again.");
            return "signup";
        }else{
            if(adminPass.equals("minhaz")){
                user.setRole("ROLE_ADMIN", "ROLE_USER");
            }else{
                user.setRole("ROLE_USER");
            }
            userRepository.save(user);
        }
        return "test";
    }
}
