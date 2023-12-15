package com.courseproject.courseproject.Service;

import com.courseproject.courseproject.Entity.Film;
import com.courseproject.courseproject.Repository.FilmRepository;
import com.courseproject.courseproject.Entity.dto.AllInfoResponse;
import com.courseproject.courseproject.Entity.dto.NewFilmRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	public List<Film> getLikedFilms(Long userID){
		return filmRepository.getLikedFilms(userID);
	}
	public List<Film> GetSavedFilms(Long userID){
		return filmRepository.GetSavedFilms(userID);
	}
	
	
	public void DeleteFilm(String filmID){
		int id = Integer.parseInt(filmID);
		filmRepository.DeleteFilm(id);
	}
	public Film GetFilmByID(String filmID){
		int id = Integer.parseInt(filmID);
		return filmRepository.GetFilmByID(id);
	}
	public List<AllInfoResponse> GetAllInfoAboutFilmByID(String filmID){
		int id = Integer.parseInt(filmID);
		return filmRepository.GetAllInfoAboutFilmByID(id);
	}
	public void Update(NewFilmRequest request){
		filmRepository.Update(request);
	}
	
}
