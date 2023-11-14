package com.courseproject.courseproject.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfig {
	@Bean
	public PasswordEncoder passwordEncoder(){
		
		System.out.println("[PasswordConfig]-PasswordEncoder Method");
		return new BCryptPasswordEncoder();
	}
}
