package com.courseproject.courseproject.Controller.Shared;

import com.courseproject.courseproject.Entity.Film;
import com.courseproject.courseproject.Service.FilmService;
import com.courseproject.courseproject.Service.UserService;
import com.courseproject.courseproject.dto.AllInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SharedController {
	private final UserService userService;
	private final FilmService filmService;
	@GetMapping("/getFilms")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public List<Film> getFilms(){
		return filmService.getFilms();
	}
	
	@PostMapping("/GetFilm")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public Film GetFilmByID(@RequestParam("filmID") String filmID){
		return filmService.GetFilmByID(filmID);
	}
	
	@PostMapping("/GetFilmAllInfo")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public List<AllInfoResponse> GetFilmAllInfoById(@RequestParam("filmID") String filmID){
		return filmService.GetAllInfoAboutFilmByID(filmID);
	}
	
	
}
