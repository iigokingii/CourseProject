package com.courseproject.courseproject.Service;

import com.courseproject.courseproject.Entity.User;
import com.courseproject.courseproject.Repository.FilmRepository;
import com.courseproject.courseproject.Repository.UserRepository;
import com.courseproject.courseproject.dto.AddNewFilmRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilmService {
	private final FilmRepository filmRepository;
	private final PasswordEncoder passwordEncoder;
	
	public void Save(AddNewFilmRequest newFilm){
		filmRepository.Save(newFilm);
	}
	
}
