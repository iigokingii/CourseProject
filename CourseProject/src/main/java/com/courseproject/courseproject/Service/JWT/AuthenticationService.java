package com.courseproject.courseproject.Service.JWT;

import com.courseproject.courseproject.Entity.Role;
import com.courseproject.courseproject.Entity.User;
import com.courseproject.courseproject.Repository.UserRepository;
import com.courseproject.courseproject.Service.UserService;
import com.courseproject.courseproject.dto.JwtAuthenticationResponse;
import com.courseproject.courseproject.dto.SignInRequest;
import com.courseproject.courseproject.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository userRepository;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	public JwtAuthenticationResponse signup(SignUpRequest request) throws SQLException {
		System.out.println("[AuthenticationService]-signup");
		var user = User
				.builder()
				.LOGIN(request.getLogin())
				//TODO добавить дефолтный автар
				.AVATAR("")
				.EMAIL(request.getEmail())
				.PASSWORD(passwordEncoder.encode(request.getPassword()))
				//TODO заменить на юзера когда будет готов admin view
				.USER_ROLE(Role.ROLE_ADMIN)
				.build();
		
		user = userService.save(user);
		var jwt = jwtService.generateToken(user);
		System.out.println("[AuthenticationService]-JWT AT SAVING: "+ jwt);
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}
	
	
	public JwtAuthenticationResponse signin(SignInRequest request) {
		System.out.println("[AuthenticationService]-signin");
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		//TODO заменить на findbyemailandpassword;переделать хэщирование пароля
		
		var user = userRepository.findByEMAIL(request.getEmail());
		var jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}
}
