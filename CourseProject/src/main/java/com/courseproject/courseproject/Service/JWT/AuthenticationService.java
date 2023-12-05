package com.courseproject.courseproject.Service.JWT;

import com.courseproject.courseproject.Entity.Role;
import com.courseproject.courseproject.Entity.User;
import com.courseproject.courseproject.Repository.UserRepository;
import com.courseproject.courseproject.Service.UserService;
import com.courseproject.courseproject.dto.JwtAuthenticationResponse;
import com.courseproject.courseproject.dto.SignInRequest;
import com.courseproject.courseproject.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	private final UserRepository userRepository;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private byte[] ConvertPathToByteArr(String path){
		File imageFile = new File(path);
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(imageFile);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[4096];
		int bytesRead;
		while (true) {
			try {
				if (!((bytesRead = fileInputStream.read(buffer)) != -1))
					break;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			byteArrayOutputStream.write(buffer, 0, bytesRead);
		}
		byte[] imageBytes = byteArrayOutputStream.toByteArray();
		try {
			fileInputStream.close();
			byteArrayOutputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return imageBytes;
	}
	
	public JwtAuthenticationResponse signup(SignUpRequest request){
		System.out.println("[AuthenticationService]-signup");
		byte[] imageBytes = ConvertPathToByteArr("D:\\3k1s\\KP\\CourseProject\\src\\main\\resources\\static\\images\\avatars\\DefAvatar.jpg");
		
		var user = User
				.builder()
				.LOGIN(request.getLogin())
				//TODO добавить дефолтный автар
				.AVATAR(imageBytes)
				.EMAIL(request.getEmail())
				.PASSWORD(passwordEncoder.encode(request.getPassword()))
				//TODO заменить на юзера когда будет готов admin view
				.USER_ROLE(Role.ROLE_User)
				.build();
		
		JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
		try {
			userService.save(user);
			var jwt = jwtService.generateToken(user);
			System.out.println("[AuthenticationService]-JWT AT SAVING: "+ jwt);
			jwtAuthenticationResponse.setToken(jwt);
		}
		catch (Exception ex){
			Pattern pattern = Pattern.compile("ORA-\\d+: [^\\r\\n]+");
			Matcher matcher = pattern.matcher(ex.getMessage());
			while (matcher.find()) {
				String errorSubstring = matcher.group();
				jwtAuthenticationResponse.setException(errorSubstring);
				break;
			}
		}
		finally {
			return jwtAuthenticationResponse;
		}
	
	}
	
	
	public JwtAuthenticationResponse signin(SignInRequest request){
		System.out.println("[AuthenticationService]-signin");
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		//TODO заменить на findbyemailandpassword;переделать хэщирование пароля
		
		var user = userRepository.findByEMAIL(request.getEmail());
		
		var jwt = jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().token(jwt).role(user.getUSER_ROLE()).build();
	}
}
