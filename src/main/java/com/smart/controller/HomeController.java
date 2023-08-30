package com.smart.controller;

import com.smart.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.smart.repository.UserRepository;

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
        model.addAttribute("title", "About - Smart Contact Manager");
        return "common/about";
    }
    //handler for custom login
    @GetMapping("/signin")
    public String customLogin(Model model) {
        model.addAttribute("title", "Login Page");
        return "common/login";
    }
        @RequestMapping("/a")
        public String defaultAfterLogin(Principal principal) {
        User user = userRepository.getUserByUserName(principal.getName());
        String role = user.getRole().toLowerCase();
            if (user.getRole().toLowerCase().equals("role_admin")) {
                return "redirect:/a/admin/index";
            }
            else if (user.getRole().toLowerCase().equals("role_trainer")) {
                return "redirect:/a/trainer/index";
            }
            else if (user.getRole().toLowerCase().equals("role_user")) {
                return "redirect:/a/user/index";
            }
             return "redirect:/";
        }
}
