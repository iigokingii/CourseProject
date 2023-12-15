package com.courseproject.courseproject.Controller.Authentication;

import com.courseproject.courseproject.Service.JWT.AuthenticationService;
import com.courseproject.courseproject.Entity.dto.JwtAuthenticationResponse;
import com.courseproject.courseproject.Entity.dto.SignInRequest;
import com.courseproject.courseproject.Entity.dto.SignUpRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
		return authenticationService.signup(request);
		
		
		
//		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
//		try {
//			jwtAuthenticationResponse=authenticationService.signup(request);
//			return jwtAuthenticationResponse;
//		}
//		catch (Exception ex){
//			Pattern pattern = Pattern.compile("ORA-\\d+: [^\\r\\n]+");
//			Matcher matcher = pattern.matcher(ex.getMessage());
//			while (matcher.find()) {
//				String errorSubstring = matcher.group();
//				jwtAuthenticationResponse.setException(errorSubstring);
//				break;
//			}
//		}
//		finally {
//			return jwtAuthenticationResponse;
//		}
		
	}
	
	@PostMapping("/signin")
	@ResponseBody
	public JwtAuthenticationResponse signin(@RequestBody SignInRequest request, Model model, HttpServletResponse response) {
		System.out.println("[AuthenticationController]-Sign In");
		JwtAuthenticationResponse jwtAuthenticationResponse  = new JwtAuthenticationResponse();
		try {
			jwtAuthenticationResponse = authenticationService.signin(request);
		}
		catch (Exception ex){
			/*Pattern pattern = Pattern.compile("ORA-\\d+: [^\\r\\n]+");
			Matcher matcher = pattern.matcher(ex.getMessage());*/
			String regex = "ORA-20002: USER DO NOT FIND,CHECK CREDENTIALS";
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(ex.getMessage());
			if(matcher.find()){
				String errorSubstring = matcher.group();
				jwtAuthenticationResponse.setException(errorSubstring);
			}
			else{
				String badCredentials = ex.getMessage().substring(ex.getMessage().lastIndexOf(":") + 1);
				jwtAuthenticationResponse.setException(badCredentials);
			}
		}
		finally {
			return jwtAuthenticationResponse;
		}
		
	}
}
