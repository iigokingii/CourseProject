package com.courseproject.courseproject.Controller.Main;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
//	@GetMapping("/")
//	public ModelAndView index(Model model) {
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("SignUpHTML/SignUpHTML");
//		return modelAndView;
//	}
	@GetMapping({"/","/SignUp"})
	public ModelAndView SignUp(Model model){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("SignUpHTML/SignUp");
		return modelAndView;
	}
	@GetMapping("/SignIn")
	public ModelAndView SignIn(Model model){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("SignInHTML/SignIn");
		return modelAndView;
	}
	
	
	
}
