package com.courseproject.courseproject;

//import com.courseproject.courseproject.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootApplication
public class CourseProjectApplication /*implements CommandLineRunner*/ {
	//@Autowired
	//private JdbcTemplate jdbcTemplate;
	public static void main(String[] args) {
		//netstat -ano | findstr 8080
		//taskkill /f /pid 8612
		SpringApplication.run(CourseProjectApplication.class, args);
	}
	/*@Override
	public void run(String... args) throws Exception {
		String sql = "SELECT * FROM USER_PROFILE";
		
		List<User> students = jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(User.class));
		
		students.forEach(System.out :: println);
	}*/
}
