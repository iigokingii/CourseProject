package com.courseproject.courseproject.Controller.Admin;

import com.courseproject.courseproject.Entity.Film;
import com.courseproject.courseproject.Entity.User;
import com.courseproject.courseproject.Service.FilmService;
import com.courseproject.courseproject.Service.UserService;
import com.courseproject.courseproject.dto.NewFilmRequest;
import com.courseproject.courseproject.dto.AddUserResponse;
import com.courseproject.courseproject.dto.UserModelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminController {
	private final UserService userService;
	private final FilmService filmService;
	//All users Page requests
	@GetMapping("/AllUsersPage")
	@PreAuthorize("hasRole('Admin')")
	public ModelAndView AllUsersPage(){
		return new ModelAndView("Admin/AllUsers");
	}
	@GetMapping("/getUsers")
	@PreAuthorize("hasRole('Admin')")
	public List<User> GetUsers(){
		return userService.GetAllUsers();
	}
	@DeleteMapping("/DeleteUser")
	@PreAuthorize("hasRole('Admin')")
	public void DeleteUser(@RequestParam("userID") String userID){
		userService.DeleteUserByID(userID);
	}
	@PostMapping("/AddUser")
	@PreAuthorize("hasRole('Admin')")
	public AddUserResponse AddUser(@RequestBody UserModelRequest request){
		return userService.AddUser(request);
	}
	@PostMapping("/UpdateUser")
	@PreAuthorize("hasRole('Admin')")
	public AddUserResponse UpdateUser(@RequestBody UserModelRequest request){
		return userService.UpdateUserData(request);
	}
	@GetMapping("/GetUserByID")
	@PreAuthorize("hasRole('Admin')")
	public User GetUserByID(@RequestParam("userID") String userID){
		return userService.GetUserById(userID);
	}
	//Add new film requests
	@GetMapping("/addNewFilm")
	@PreAuthorize("hasRole('Admin')")
	public ModelAndView AddNewFilm(){
		return new ModelAndView("Admin/AddNewFilm");
	}
	@PostMapping("/addNewFilm")
	@PreAuthorize("hasRole('Admin')")
	public void AddNewFilmToDb(@RequestBody NewFilmRequest request){
		filmService.Save(request);
	}
	//All films requests
	@GetMapping("/AllFilms")
	@PreAuthorize("hasRole('Admin')")
	public ModelAndView allFilms(){
		return new ModelAndView("Admin/AllFilms");
	}
	
	@DeleteMapping("/deleteFilm")
	@PreAuthorize("hasRole('Admin')")
	public void DeleteFilm(@RequestParam("filmID") String filmID){
		filmService.DeleteFilm(filmID);
	}
	@GetMapping("/UpdateFilm")
	@PreAuthorize("hasRole('Admin')")
	public ModelAndView GetFilmByID(@RequestParam("filmID") String filmID, Model model){
		ModelAndView modelAndView = new ModelAndView("Admin/AddNewFilm");
		model.addAttribute("filmID",filmID);
		return modelAndView;
	}
	@PutMapping("/UpdateFilm")
	@PreAuthorize("hasRole('Admin')")
	public void UpdateFilm(@RequestBody NewFilmRequest request){
		filmService.Update(request);
	}
	
	//Admin main page requests
	@GetMapping("/AdminMainPage")
	@PreAuthorize("hasRole('Admin')")
	public ModelAndView AdminMainPage(){
		return new ModelAndView("Admin/AdminMainPage");
	}
}
