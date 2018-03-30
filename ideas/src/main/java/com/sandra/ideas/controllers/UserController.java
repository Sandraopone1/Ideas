package com.sandra.ideas.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sandra.ideas.models.Idea;
import com.sandra.ideas.models.User;
import com.sandra.ideas.services.IdeaService;
import com.sandra.ideas.services.UserService;
import com.sandra.ideas.validator.UserValidator;

@Controller
public class UserController {
	private UserService userService;
	private UserValidator userValidator;
	private IdeaService  ideaService;
	
    public UserController(UserService userService, UserValidator userValidator, IdeaService  ideaService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.ideaService = ideaService;
       

    }
    @RequestMapping("/registration")
    public String registerForm(@Valid @ModelAttribute("user") User user) {
        return "registrationPage.jsp";
    }
    
    @RequestMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error, @RequestParam(value="logout", required=false) String logout, Model model, @Valid @ModelAttribute("user") User user) {
        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }
        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }

        return "loginPage.jsp";
    }
    
    @PostMapping("/registration")
    public String registration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session) {
    	 	userValidator.validate(user, result);
    	 	if (result.hasErrors()) {
            return "loginPage.jsp";
        }
    	 	if (userService.findByEmail(user.getEmail()) != null) {
    	 		model.addAttribute("emailError", "A user with this email address already exists.");
    	 		return "loginPage.jsp";
    	 	}
    	 	if(userService.all().size() < 1) {
    	 		userService.saveUserWithAdminRole(user);
    	 	}
	    	 else {
				userService.saveWithUserRole(user);
			}
        return "redirect:/login";
    }
    
    @RequestMapping(value = {"/", "/ideas"})
    public String home(Principal principal, Model model) {
        String email = principal.getName();
        User u = userService.findByEmail(email);
        List<Idea> allIdeas = ideaService.all();
        model.addAttribute("currentUser", u);
       model.addAttribute("allIdeas", allIdeas);
        return "homePage.jsp";
    }
    
    @RequestMapping("/admin")
    public String adminPage(Principal principal, Model model) {
    	String email = principal.getName();
    	model.addAttribute("currentUser", userService.findByEmail(email));
    	return "adminPage.jsp";
    }
}
