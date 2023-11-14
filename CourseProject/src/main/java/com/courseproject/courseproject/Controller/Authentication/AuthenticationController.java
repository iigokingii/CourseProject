package com.courseproject.courseproject.Controller.Authentication;

import com.courseproject.courseproject.Service.JWT.AuthenticationService;
import com.courseproject.courseproject.dto.JwtAuthenticationResponse;
import com.courseproject.courseproject.dto.SignInRequest;
import com.courseproject.courseproject.dto.SignUpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/auth")

public class AuthenticationController {
	private final AuthenticationService authenticationService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		System.out.println("[AuthenticationController]- AuthenticationControllerConstructor");
		this.authenticationService = authenticationService;
	}
	
	@PostMapping("/signup")
	public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request) {
		System.out.println("[AuthenticationController]-Sign up");
		/*JwtAuthenticationResponse response = authenticationService.signup(request);*/
		return authenticationService.signup(request);
		/*ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		model.addAttribute("rs",response);
		return modelAndView;*/
	}
	
	@PostMapping("/signin")
	public JwtAuthenticationResponse signin(@RequestBody SignInRequest request) {
		System.out.println("[AuthenticationController]-Sign In");
		return authenticationService.signin(request);
		/*JwtAuthenticationResponse response =authenticationService.signin(request);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		model.addAttribute("rs",response);
		return modelAndView;*/
	}
}
