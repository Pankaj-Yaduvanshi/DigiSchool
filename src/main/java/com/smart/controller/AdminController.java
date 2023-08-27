package com.smart.controller;

import com.smart.entities.User;
import com.smart.helper.Message;
import com.smart.repository.AssignmentRepository;
import com.smart.repository.ContactRepository;
import com.smart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
@Controller
@RequestMapping("/a/admin")
public class AdminController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;

    // method for adding common data to response
    @ModelAttribute
    public void addCommonData(Model model, Principal principal) {
        String userName = principal.getName();
        System.out.println("USERNAME " + userName);
        // get the user using username(Email)
        User user = userRepository.getUserByUserName(userName);
        System.out.println("USER " + user);
        model.addAttribute("user", user);
    }
    @RequestMapping("/index")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("title", "User Dashboard");
        return "admin/user_dashboard";
    }
    @RequestMapping("/add-user")
    public String signup(Model model) {
        model.addAttribute("title", "add - user");
        model.addAttribute("user", new User());
        return "admin/add_user";
    }
//    handler for registering user
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
                return "admin/add_user";
            }
            user.setRole("ROLE_USER");
            user.setEnabled(true);
            user.setImageUrl("default.png");
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            User result = this.userRepository.save(user);
            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));
            return "admin/add_user";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong !! " + e.getMessage(), "alert-danger"));
            return "admin/add_user";
        }
    }
    @RequestMapping("/settings")
    public String setting(Model model) {
        model.addAttribute("title", "Setting | DigiSchool");
        return "common/settings";
    }
    @PostMapping("/change-password")
    public String changePassword(@RequestParam("oldPassword") String oldPassword,
                                 @RequestParam("newPassword") String newPassword, Principal principal, HttpSession session) {
        String userName = principal.getName();
        User currentUser = this.userRepository.getUserByUserName(userName);
        if (this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword())) {
            // change the password
            currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
            this.userRepository.save(currentUser);
            session.setAttribute("message", new Message("Your password is successfully changed..", "success"));
        } else {
            // error...
            session.setAttribute("message", new Message("Please Enter correct old password !!", "danger"));
            return "redirect:/admin/settings";
        }
        return "redirect:/admin/index";
    }

}