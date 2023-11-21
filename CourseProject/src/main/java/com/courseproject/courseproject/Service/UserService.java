package com.courseproject.courseproject.Service;

import com.courseproject.courseproject.Entity.User;
import com.courseproject.courseproject.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	public UserDetailsService userDetailsService(){
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException,UncategorizedSQLException{
				return userRepository.findByEMAIL(username);
			}
		};
		/*return username -> userRepository.findByEMAIL(username);*/
		
	}
	public User save(User newUser) throws UncategorizedSQLException {
		return userRepository.save(newUser);
	}
}
