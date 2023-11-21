package com.courseproject.courseproject.Controller.Authentication;

import com.courseproject.courseproject.Service.JWT.AuthenticationService;
import com.courseproject.courseproject.dto.JwtAuthenticationResponse;
import com.courseproject.courseproject.dto.SignInRequest;
import com.courseproject.courseproject.dto.SignUpRequest;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		JwtAuthenticationResponse jwtAuthenticationResponse;
		try {
			jwtAuthenticationResponse=authenticationService.signup(request);;
			return jwtAuthenticationResponse;
		}
		catch (UncategorizedSQLException ex){
			jwtAuthenticationResponse = new JwtAuthenticationResponse();
			//TODO ЗАМЕНИТЬ РЕГУЛЯРКУ
			String regex = "ORA-20000: ([^\\\\s]+)";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(ex.getMessage());
			String oraErrorMessage="";
			if (matcher.find()) {
				oraErrorMessage = matcher.group(1);
				System.out.println(oraErrorMessage);
			}
			jwtAuthenticationResponse.setException(oraErrorMessage);
			return jwtAuthenticationResponse;
		}
		
	}
	
	@PostMapping("/signin")
	@ResponseBody
	public JwtAuthenticationResponse signin(@RequestBody SignInRequest request, Model model, HttpServletResponse response) {
		System.out.println("[AuthenticationController]-Sign In");
		JwtAuthenticationResponse jwtAuthenticationResponse;
		try {
			jwtAuthenticationResponse = authenticationService.signin(request);
			return jwtAuthenticationResponse;
		}
		catch (Exception ex){
			jwtAuthenticationResponse = new JwtAuthenticationResponse();
			jwtAuthenticationResponse.setException(ex.getMessage());
			return jwtAuthenticationResponse;
		}
	}
}
