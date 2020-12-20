package com.example.Booking.room.controller;


import com.example.Booking.room.model.User;
import com.example.Booking.room.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RegisterController {

    private UserService userService;

    //responsible for handle user request
    //    step1: update model for template
    //    step2: chose HTML template

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    //handle user request
    @GetMapping("/regis")
    public String getRegisterPage(Model model) {

        //    step1: update model for template
            model.addAttribute("allUser", userService.getUser());

        //    step2: chose HTML template
        return "registerPage";
    }
    @PostMapping("/regis")
    public String registerUser(@ModelAttribute User user, Model model) {
        if(userService.findUser(user.getUsername()) == null){
            userService.createUser(user);
            model.addAttribute("allUser", userService.getUser());
            return "redirect:login";
        } else {
            model.addAttribute("greeting", "username is already existed.");
            return "registerPage";
        }

    }
}

