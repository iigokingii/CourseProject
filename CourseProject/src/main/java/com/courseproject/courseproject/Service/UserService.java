package com.courseproject.courseproject.Service;

import com.courseproject.courseproject.Entity.Role;
import com.courseproject.courseproject.Entity.User;
import com.courseproject.courseproject.Repository.UserRepository;
import com.courseproject.courseproject.dto.AddUserResponse;
import com.courseproject.courseproject.dto.UserModelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserDetailsService userDetailsService(){
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,UncategorizedSQLException{
				return userRepository.findByEMAIL(username);
			}
		};
		/*return username -> userRepository.findByEMAIL(username);*/
		
	}
	public void save(User newUser) throws UncategorizedSQLException {
		userRepository.save(newUser);
	}
	public List<User> GetAllUsers(){
		return userRepository.GetAllUsers();
	}
	
	public void DeleteUserByID(String UserID){
		userRepository.DeleteUserByID(Long.parseLong(UserID));
	}
	public User GetUserById(String UserID){
		return userRepository.GetUserById(Long.parseLong(UserID));
	}
	public AddUserResponse AddUser(@RequestBody UserModelRequest request){
		String encodedPass =passwordEncoder.encode(request.getPassword());
		var user = User
				.builder()
				.LOGIN(request.getLogin())
				.AVATAR(request.getAvatar())
				.EMAIL(request.getEmail())
				.PASSWORD(encodedPass)
				.USER_ROLE(request.getUser_ROLE())
				.build();
		AddUserResponse addUserResponse = new AddUserResponse();
		try {
			Long id = userRepository.SaveGetID(user);
			addUserResponse.setAVATAR(user.getAVATAR());
			addUserResponse.setID(id);
			addUserResponse.setEncodedPass(encodedPass);
		}
		catch (UncategorizedSQLException ex){
			Pattern pattern = Pattern.compile("ORA-\\d+: [^\\r\\n]+");
			Matcher matcher = pattern.matcher(ex.getMessage());
			while (matcher.find()) {
				String errorSubstring = matcher.group();
				addUserResponse.setException(errorSubstring);
				return addUserResponse;
			}
		}
		finally {
			return addUserResponse;
		}
		
		
	}
	public AddUserResponse UpdateUserData(@RequestBody UserModelRequest request){
		String encodedPass = passwordEncoder.encode(request.getPassword());
		AddUserResponse addUserResponse = new AddUserResponse();
		addUserResponse.setAVATAR(request.getAvatar());
		addUserResponse.setID(Long.parseLong(request.getUser_PROFILE_ID()));
		addUserResponse.setEncodedPass(encodedPass);
		var user = User
				.builder()
				.USER_PROFILE_ID(Long.parseLong(request.getUser_PROFILE_ID()))
				.LOGIN(request.getLogin())
				.AVATAR(request.getAvatar())
				.EMAIL(request.getEmail())
				.PASSWORD(encodedPass)
				.USER_ROLE(request.getUser_ROLE())
				.build();
		userRepository.UpdateUser(user);
		return addUserResponse;
	}
	
}
