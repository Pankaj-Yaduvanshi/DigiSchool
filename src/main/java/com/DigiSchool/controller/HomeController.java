package com.DigiSchool.controller;

import com.DigiSchool.Model.Role;
import com.DigiSchool.Model.User;
import com.DigiSchool.Helper.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.DigiSchool.Repository.UserRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Home - DigiSchool");
        return "common/home";
    }

    @RequestMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About - DigiSchool");
        return "common/about";
    }

    //handler for custom login
    @GetMapping("/signin")
    public String customLogin(Model model) {
        model.addAttribute("title", "Login Page");
        return "common/login";
    }

    @RequestMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Register - DigiSchool");
        model.addAttribute("user", new User());
        return "signup";
    }

    // handler for registering user
    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result1,
                               @RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
                               HttpSession session) {

        try {

            if (!agreement) {
                System.out.println("You have not agreed the terms and conditions");
                throw new Exception("You have not agreed the terms and conditions");
            }

            if (result1.hasErrors()) {
                System.out.println("ERROR " + result1.toString());
                model.addAttribute("user", user);
                return "signup";
            }

            user.setRole(Role.REGULAR);
            user.setEnabled(true);
            user.setImageUrl("default.png");
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            System.out.println("Agreement " + agreement);
            System.out.println("USER " + user);

            User result = this.userRepository.save(user);

            model.addAttribute("user", new User());

            session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));
            return "signup";

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something Went wrong !! " + e.getMessage(), "alert-danger"));
            return "signup";
        }

    }

    @RequestMapping("/a")
    public String defaultAfterLogin(Principal principal) {
        User user = userRepository.getUserByUserName(principal.getName());
        System.out.println(user.getRole());
        if (Role.ADMIN.equals(user.getRole())) {
            return "redirect:/a/admin/index";
        } else if (Role.TRAINER.equals(user.getRole())) {
            return "redirect:/a/trainer/index";
        } else if (Role.REGULAR.equals(user.getRole())) {
            return "redirect:/a/regular/index";
        }
        return "redirect:/";
    }
}
