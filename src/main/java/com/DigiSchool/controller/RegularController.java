package com.DigiSchool.controller;
import java.security.Principal;
import java.util.Optional;
import com.DigiSchool.Model.Assignment;
import com.DigiSchool.Repository.AssignmentRepository;
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

@Controller
@RequestMapping("/a/regular")
public class RegularController {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AssignmentRepository assignmentRepository;

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
		return "user/profile";
	}
//	To view list of assignment
	@RequestMapping("/show-assignments{page}")
	public String showAssignments(@PathVariable("page") Integer page, Model m) {
		int srNo = 1;
		m.addAttribute("title", "Show Assignment");
		Pageable pageable = PageRequest.of(page, 10);
		Page<Assignment> assignments = this.assignmentRepository.findAll(pageable);
		m.addAttribute("assignments", assignments);
		m.addAttribute("currentPage", page);
		m.addAttribute("totalPages", assignments.getTotalPages());
		m.addAttribute("srNo", srNo);
		return "user/show_assignments";
	}
//	To stat test
	@RequestMapping("/{aId}/exam")
	public String exam(Model model, Principal principal, @PathVariable("aId") Integer aId) {
		Optional<Assignment> assignmentOptional = this.assignmentRepository.findById(aId);
		Assignment assignment = assignmentOptional.get();
		model.addAttribute("title", "Test Started");
		model.addAttribute("assignment", assignment);
		return "user/exam-page";
	}

}
