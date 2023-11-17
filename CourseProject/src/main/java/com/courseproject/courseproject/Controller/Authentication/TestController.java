package com.courseproject.courseproject.Controller.Authentication;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1/test")

//TODO Cookie удаляется по нажатию на кнопку, если не нажать остануться на 2 дня
public class TestController {
	
	@GetMapping("/anon")
	public String anonEndPoint() {
		
		System.out.println("[TestController]-anonEndPoint");
		return "everyone can see this";
	}
	
	@GetMapping("/users")
	@PreAuthorize("hasRole('USER')")
	public ModelAndView usersEndPoint(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("Role","users");
		modelAndView.setViewName("User/User");
		return new ModelAndView("User/User");
	}
	@GetMapping("/admins")
	@PreAuthorize("hasRole('ADMIN')")
	public ModelAndView redirectToNewPage(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		model.addAttribute("Role","admins");
		modelAndView.setViewName("Admin/Admin");
		return modelAndView;
	}
}