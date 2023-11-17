package com.courseproject.courseproject.Controller.Authentication;

import com.courseproject.courseproject.Service.JWT.AuthenticationService;
import com.courseproject.courseproject.dto.JwtAuthenticationResponse;
import com.courseproject.courseproject.dto.SignInRequest;
import com.courseproject.courseproject.dto.SignUpRequest;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
@RequestMapping("/api/auth")

public class AuthenticationController {
	private final AuthenticationService authenticationService;
	
	public AuthenticationController(AuthenticationService authenticationService) {
		System.out.println("[AuthenticationController]- AuthenticationControllerConstructor");
		this.authenticationService = authenticationService;
	}
	
	@PostMapping("/signup")
	@ResponseBody
	public JwtAuthenticationResponse signup(@RequestBody SignUpRequest request,Model model) {
		System.out.println("[AuthenticationController]-Sign up");
		JwtAuthenticationResponse jwtAuthenticationResponse=null;
		try {
			jwtAuthenticationResponse=authenticationService.signup(request);;
			return jwtAuthenticationResponse;
		}
		catch (SQLException ex){
			jwtAuthenticationResponse.setException(ex.getMessage());
			return jwtAuthenticationResponse;
		}
		
	}
	
	@PostMapping("/signin")
	@ResponseBody
	public JwtAuthenticationResponse signin(@RequestBody SignInRequest request, Model model, HttpServletResponse response) {
		System.out.println("[AuthenticationController]-Sign In");
		return authenticationService.signin(request);
	}
}
