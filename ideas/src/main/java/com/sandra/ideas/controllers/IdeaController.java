package com.sandra.ideas.controllers;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sandra.ideas.models.Idea;
import com.sandra.ideas.models.User;
import com.sandra.ideas.services.IdeaService;
import com.sandra.ideas.services.UserService;

@Controller
@RequestMapping("/ideas")
public class IdeaController {

	private IdeaService ideaService;
	private UserService userService;
	
	public IdeaController(IdeaService ideaService, UserService userService) {
		this.userService = userService;
		this.ideaService = ideaService;
	}
	
	@RequestMapping("/new")
	public String addCoursePage(@Valid @ModelAttribute("idea") Idea idea) {
		return "addIdea.jsp";
	}
	
	@PostMapping("/new")
	public String addCourse(@Valid @ModelAttribute("idea") Idea idea, BindingResult result) {
		if (result.hasErrors()) {
			return "addIdea.jsp";
		}
		idea.setLikes(0);
		ideaService.create(idea);
		return "redirect:/";
	}
	
	@RequestMapping("/{id}")
	public String showCourse(Model model, @PathVariable("id") Long idea_id, Principal principal) {
		Idea c = (Idea)ideaService.findById(idea_id);
		model.addAttribute("idea", c);
		
		String email = principal.getName();
		User u = userService.findByEmail(email);
		model.addAttribute("u", u);
		System.out.println(u.getFirstName());
		
		
		List<User> users = c.getUsers();

		
		return "showIdea.jsp";
	}
	
	
	@PostMapping("/{id}/edit")
	public String editCourse(@Valid @ModelAttribute("idea") Idea idea, @PathVariable("id") Long idea_id, BindingResult result) {
		if (result.hasErrors()) {
			return "editIdea.jsp";
		}
		ideaService.update(idea);
		return "redirect:/";
	}
	
	@RequestMapping("/{id}/edit")
	public String editCoursePage(@PathVariable("id") Long idea_id, Model model, Principal principal) {
//		String email = principal.getName();
//        User u = userService.findByEmail(email);
//        Idea c = (Idea)ideaService.findById(idea_id);
//        String m = c.getCreator();
//		String n = u.getFirstName();
//        if (n == m ) {
		model.addAttribute("idea", (Idea)ideaService.findById(idea_id));
		return "editIdea.jsp";
//        }
//        else {
//        	return "redirect:/";
//        }
	}
	
	
	
	@RequestMapping("/{id}/like")
	public String signUp(Principal principal, @PathVariable("id") Long idea_id, Model model) {
		Idea i = (Idea)ideaService.findById(idea_id);
		String email = principal.getName();
		User e = userService.findByEmail(email);
		Date d = new Date();
		
		List<User> users = i.getUsers();
		users.add(e);
		i.setUsers(users);
		e.setUpdatedAt(d);
		
		int member = i.getLikes();
		member += 1;
		i.setLikes(member);
		
		ideaService.update(i);
		
		return "redirect:/ideas/";
	}
	
	
	@RequestMapping("/{id}/destroy")
	public String deleteCourse(@PathVariable("id") Long idea_id) {
		ideaService.destroy(idea_id);
		return "redirect:/";
	}
	
	@RequestMapping("/{id}/quit")
	public String leaveCourse(@PathVariable("id") Long idea_id, Principal principal) {
		Idea i = (Idea)ideaService.findById(idea_id);
		
		String email = principal.getName();
		User e = userService.findByEmail(email);
		Date d = new Date();
		
		List<User> users = i.getUsers();	
		users.remove(e);
		i.setUsers(users);
		e.setUpdatedAt(d);
		int member = i.getLikes();
		if (member == 0) {
			i.setLikes(0);
			ideaService.update(i);
			return "redirect:/";
		}
		else {
			member -= 1;
		i.setLikes(member);
		
		ideaService.update(i);
		return "redirect:/";
	}
	}

}
