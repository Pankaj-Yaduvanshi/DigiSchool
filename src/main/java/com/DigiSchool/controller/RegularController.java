package com.DigiSchool.controller;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.DigiSchool.Helper.Message;
import com.DigiSchool.Model.Assignment;
import com.DigiSchool.Model.Question;
import com.DigiSchool.Model.Result;
import com.DigiSchool.Repository.AssignmentRepository;
import com.DigiSchool.Repository.QuestionRepository;
import com.DigiSchool.Repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.DigiSchool.Repository.UserRepository;
import com.DigiSchool.Model.User;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/a/regular")
public class RegularController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AssignmentRepository assignmentRepository;
	@Autowired
	private ResultRepository resultRepository;
	@Autowired
	private QuestionRepository questionRepository;

	// method for adding common data to response
	@ModelAttribute
	public void addCommonData(Model model, Principal principal) {
		String userName = principal.getName();
		System.out.println("USERNAME " + userName);
		User user = userRepository.getUserByUserName(userName);
		System.out.println("USER " + user);
		model.addAttribute("user", user);
	}
	// dashboard home
	@RequestMapping("/index")
	public String dashboard(Model model, Principal principal) {
		model.addAttribute("title", "User Dashboard");
		return "regular/profile";
	}
//	To view list of assignment
	@RequestMapping("/show-assignments{page}")
	public String showAssignments(@PathVariable("page") Integer page, Model m) {
		int srNo = 1;
		m.addAttribute("title", "View Assignment List");
		Pageable pageable = PageRequest.of(page, 10);
		Page<Assignment> assignments = this.assignmentRepository.findAll(pageable);
		m.addAttribute("assignments", assignments);
		m.addAttribute("currentPage", page);
		m.addAttribute("totalPages", assignments.getTotalPages());
		m.addAttribute("srNo", srNo);
		return "regular/view-assignment-list";
	}
//	To stat test
	@RequestMapping("/{aId}/exam")
	public String exam(Model model, Principal principal, @PathVariable("aId") Integer aId) {
		Optional<Assignment> assignmentOptional = this.assignmentRepository.findById(aId);
		Assignment assignment = assignmentOptional.get();
		model.addAttribute("title", "Test Started");
		model.addAttribute("assignment", assignment);
		return "regular/exam-page";
	}
	@RequestMapping("/{aId}/submit-exam")
	public String submitExam(@RequestParam Map<String, String> formData, Principal principal, @PathVariable("aId") Integer aId, HttpSession session){
		try {
				Optional<Assignment> assignmentOptional = this.assignmentRepository.findById(aId);
				Assignment assignment = assignmentOptional.get();
				List<Question> questionsList = assignment.getQuestions();
				int numberOfQuestions = questionsList.size();
				int score =0;
				for (Map.Entry<String,String> entry : formData.entrySet())
				{
					int index = Integer.parseInt(entry.getKey());
					if(entry.getValue().equals(questionRepository.findById(index).get().getAnswer()))
						score++;
				}
				if(score>=numberOfQuestions*0.8)
				{
					User user = userRepository.getUserByUserName(principal.getName());
					Result result = new Result();
					result.setUser(user);
					result.setAssignment(assignment);
					result.setResult(formData.toString());
					resultRepository.save(result);
		//			message success.......
					session.setAttribute("message", new Message("Congratulations you passed the exam!!!!!", "success"));
				}
				else
					session.setAttribute("message", new Message("Sorry you did not pass the exam!!!!!", "danger"));

		} catch (Exception e) {
			System.out.println("ERROR " + e.getMessage());
			e.printStackTrace();
			// message error
			session.setAttribute("message", new Message("Something went wrong !! Try again..", "danger"));
		}
		return "redirect:/"+ "a/regular/"+aId+"/exam";
	}
}
