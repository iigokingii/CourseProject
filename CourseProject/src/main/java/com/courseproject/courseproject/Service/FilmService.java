package com.courseproject.courseproject.Service;

import com.courseproject.courseproject.Entity.Comment;
import com.courseproject.courseproject.Entity.Film;
import com.courseproject.courseproject.Repository.FilmRepository;
import com.courseproject.courseproject.dto.AddCommentRequest;
import com.courseproject.courseproject.dto.NewFilmRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {
	private final FilmRepository filmRepository;
	private final PasswordEncoder passwordEncoder;
	
	public void Save(NewFilmRequest newFilm){
		filmRepository.Save(newFilm);
	}
	public List<Film> getFilms(){
		return filmRepository.getFilms();
	}
	public void DeleteFilm(String filmID){
		int id = Integer.parseInt(filmID);
		filmRepository.DeleteFilm(id);
	}
	public Film GetFilmByID(String filmID){
		int id = Integer.parseInt(filmID);
		return filmRepository.GetFilmByID(id);
	}
	public void Update(NewFilmRequest request){
		filmRepository.Update(request);
	}
	public void AddComment(AddCommentRequest request){
		Long userid = Long.parseLong(request.getUserId());
		Long filmid = Long.parseLong(request.getFilmId());
		var comment = Comment
				.builder()
				.USER_PROFILE_ID(userid)
				.USER_REVIEW_TEXT(request.getComment())
				.ALL_INFORMATION_ABOUT_FILM_ID(filmid)
				.DATE_OF_REVIEW(request.getDate())
				.build();
	}
	
	
}
