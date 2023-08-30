package com.smart.controller;
import com.smart.entities.Assignment;
import com.smart.entities.Option;
import com.smart.entities.Question;
import com.smart.entities.User;
import com.smart.helper.Message;
import com.smart.repository.AssignmentRepository;
import com.smart.repository.OptionRepository;
import com.smart.repository.QuestionRepository;
import com.smart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;
@Controller
@RequestMapping("/a/trainer")
public class TrainerController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private OptionRepository optionRepository;
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
    public String dashboard (Model model, Principal principal) {
        model.addAttribute("title", "User Dashboard");
        return "trainer/user_dashboard";
    }
      @GetMapping("/add-assignment")
    public String openAddAssignmentForm(Model model) {
        model.addAttribute("title", "Add Assignment");
        model.addAttribute("assignment", new Assignment());
        return "trainer/add_assignment_form";
    }

//    To add assignment
   @PostMapping("/process-assignment")
    public String processAssignment(@ModelAttribute Assignment assignment, HttpSession session) {
        try {
            assignmentRepository.save(assignment);
//			message success.......
            session.setAttribute("message", new Message("Your assignment is added successfully!!!!!", "success"));
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
            e.printStackTrace();
            // message error
            session.setAttribute("message", new Message("Something went wrong !! Try again..", "danger"));
        }
        return "trainer/add_assignment_form";
    }
    // show assignment handler
    // per page = 5[n]
    // current page = 0 [page]
    @RequestMapping("/show-assignments{page}")
    public String showAssignments(@PathVariable("page") Integer page, Model m) {
        int srNo = 1;
        m.addAttribute("title", "Show Assignment");
        // currentPage-page
        // Contact Per page - 5
        Pageable pageable = PageRequest.of(page, 10);
        Page<Assignment> assignments = this.assignmentRepository.findAll(pageable);
        m.addAttribute("assignments", assignments);
        m.addAttribute("currentPage", page);
        m.addAttribute("totalPages", assignments.getTotalPages());
        m.addAttribute("srNo", srNo);
        return "trainer/show_assignments";
    }

    // showing assignment details.
    @RequestMapping("/{aId}")
    public String showAssignmentDetail(@PathVariable("aId") Integer aId, Model model) {
        Optional<Assignment> assignmentOptional = this.assignmentRepository.findById(aId);
        Assignment assignment = assignmentOptional.get();
        model.addAttribute("title", assignment.getQuestionnaireNumber());
        model.addAttribute("assignment", assignment);
        return "trainer/assignment_detail";
    }

//    Show Question
@RequestMapping("/{qId}/question")
public String showQuestionDetail(@PathVariable("qId") Integer qId, Model model) {
    Optional<Question> questionOptional = this.questionRepository.findById(qId);
    Question question = questionOptional.get();
    model.addAttribute("title", question);
    model.addAttribute("question", question);
    return "trainer/question_detail";
}

    @PostMapping("/{aId}/process-question")
    public String processQuestion(@ModelAttribute Question question, @PathVariable("aId") Integer aId,HttpSession session, Model m) {
        try {
            Optional<Assignment> assignmentOptional = this.assignmentRepository.findById(aId);
            Assignment assignment = assignmentOptional.get();
            question.setAssignment(assignment);
            questionRepository.save(question);
            m.addAttribute("assignment", assignment);
//			message success.......
            session.setAttribute("message", new Message("Your Question added successfully!!!!!", "success"));
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
            e.printStackTrace();
//          message error
            session.setAttribute("message", new Message("Something went wrong !! Try again..", "danger"));
        }
        return "trainer/assignment_detail";
    }

        @RequestMapping("/{qId}/add-option")
    public String addOption  (@PathVariable("qId") Integer qId, Model model) {
        Optional<Question> questionOptional = this.questionRepository.findById(qId);
        Question question = questionOptional.get();
        model.addAttribute("qId", question.getQId());
        model.addAttribute("option", new Option());
        return "trainer/add_option_form";
    }
    @PostMapping("/{qId}/process-option")
    public String processOption(@ModelAttribute Option option, @PathVariable("qId") Integer qId, HttpSession session, Model m) {
        try {
            Optional<Question> questionOptional = this.questionRepository.findById(qId);
            Question question = questionOptional.get();
            option.setQuestion(question);
            optionRepository.save(option);
            m.addAttribute("question", question);
//			message success.......
            session.setAttribute("message", new Message("Your Option added successfully!!!!!", "success"));
        } catch (Exception e) {
            System.out.println("ERROR " + e.getMessage());
            e.printStackTrace();
//          message error
            session.setAttribute("message", new Message("Something went wrong !! Try again..", "danger"));
        }
        return "trainer/question_detail";
    }
    @RequestMapping("/training-log")
    public String trainingLog(Model model, Principal principal) {
        model.addAttribute("title", "User Dashboard");
        return "trainer/training-log";
    }

    @RequestMapping("/profile")
    public String profile(Model model, Principal principal) {
        model.addAttribute("title", "User Dashboard");
        return "common/profile";
    }
    @RequestMapping("/settings")
    public String settings(Model model, Principal principal) {
        model.addAttribute("title", "User Dashboard");
        model.addAttribute("template", "user");
        return "common/settings";
    }

}