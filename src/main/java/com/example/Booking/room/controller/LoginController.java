package com.example.Booking.room.controller;

import com.example.Booking.room.model.User;
import com.example.Booking.room.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {

        return "loginPage";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        // 1. เอา username กับ passW ไปเช็คกับข้อมูล User ที่มีอยู่ ว่าตรงกันบ้างไหม
        User matchingUser = userService.checkPassword(user);

        // 2. ถ้าตรง ส่งข้อมูล User กลับไปแสดงผล
        if (matchingUser != null) {
//            model.addAttribute("greeting",
//                    "Welcome, " + matchingUser.getUsername());

            return "redirect:booking/" + user.getUsername() + "&" + sdf.format(date) + "&1";
        } else {
            // 3. ถ้าไม่ตรง แจ้งว่าไม่มีข้อมูล User นี้
            model.addAttribute("greeting", "Your username/password do not match.");
            return "loginPage";
        }

    }
}
