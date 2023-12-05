package com.courseproject.courseproject.Controller.Shared;

import com.courseproject.courseproject.Entity.Film;
import com.courseproject.courseproject.Service.FilmService;
import com.courseproject.courseproject.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
	
	@GetMapping("/GetFilm")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public Film GetFilmByID(@RequestParam("filmID") String filmID){
		return filmService.GetFilmByID(filmID);
	}
	
}
