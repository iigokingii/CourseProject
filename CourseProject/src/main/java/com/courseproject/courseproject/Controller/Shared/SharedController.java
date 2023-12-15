package com.courseproject.courseproject.Controller.Shared;

import com.courseproject.courseproject.Entity.Film;
import com.courseproject.courseproject.Entity.User;
import com.courseproject.courseproject.Service.CommentService;
import com.courseproject.courseproject.Service.FilmService;
import com.courseproject.courseproject.Service.VisitService;
import com.courseproject.courseproject.Entity.dto.AddCommentRequest;
import com.courseproject.courseproject.Entity.dto.AllInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SharedController {
	private final FilmService filmService;
	private final CommentService commentService;
	private final VisitService visitService;
	@GetMapping("/getFilms")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public List<Film> getFilms(){
		return filmService.getFilms();
	}
	
	@GetMapping("/GetLikedFilms")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public List<Film> getLikedFilms(){
		
		return filmService.getLikedFilms(User.Id);
	}
	@GetMapping("/GetSavedFilms")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public List<Film> GetSavedFilms(){
		return filmService.GetSavedFilms(User.Id);
	}
	
	@GetMapping("/GetFilm")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public Film GetFilmByID(@RequestParam("filmID") String filmID){
		return filmService.GetFilmByID(filmID);
	}
	
	@PostMapping("/GetFilmAllInfo")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public List<AllInfoResponse> GetFilmAllInfoById(@RequestParam("filmID") String filmID){
		return filmService.GetAllInfoAboutFilmByID(filmID);
	}
	@PostMapping("/AddCommentToFilm")
	@PreAuthorize("hasAnyRole('User','Admin')")
	public void AddComment(@RequestBody AddCommentRequest request, Model model){
		request.setUserId(User.Id.toString());
		commentService.AddComment(request);
	}
	
	@PatchMapping("/NewVisit")
	@PreAuthorize("hasAnyRole('User','Admin')")
	public void AddVisit(){
	 	visitService.AddNewVisit();
	}
}
