package com.courseproject.courseproject.Controller.User;

import com.courseproject.courseproject.Entity.User;
import com.courseproject.courseproject.Service.FilmService;
import com.courseproject.courseproject.Service.UserService;
import com.courseproject.courseproject.dto.AddCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final FilmService filmService;
	
	@GetMapping("/UserMainPage")
	@PreAuthorize("hasRole('User')")
	public ModelAndView AllUsersPage(){
		return new ModelAndView("User/UserMainPage");
	}
	@GetMapping("/UserFilmView")
	@PreAuthorize("hasRole('User')")
	public ModelAndView AllUsersPage(@RequestParam("filmID") String filmID, Model model){
		ModelAndView modelAndView = new ModelAndView("User/FilmView");
		model.addAttribute("filmID",filmID);
		return modelAndView;
	}
	
	@PostMapping("/AddCommentToFilm")
	@PreAuthorize("hasRole('User')")
	public void AddComment(@RequestBody AddCommentRequest request, Model model){
		request.setUserId(User.Id.toString());
		filmService.AddComment(request);
		
		
		/*ModelAndView modelAndView = new ModelAndView("User/FilmView");
		model.addAttribute("filmID",filmID);
		return modelAndView;*/
	}
	
	
}
