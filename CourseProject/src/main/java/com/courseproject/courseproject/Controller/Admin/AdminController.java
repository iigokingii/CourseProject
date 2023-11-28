package com.courseproject.courseproject.Controller.Admin;

import com.courseproject.courseproject.Entity.User;
import com.courseproject.courseproject.Service.FilmService;
import com.courseproject.courseproject.Service.UserService;
import com.courseproject.courseproject.dto.AddNewFilmRequest;
import com.courseproject.courseproject.dto.AddUserResponse;
import com.courseproject.courseproject.dto.UserModelRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.security.access.prepost.PreAuthorize;
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
	public void AddNewFilmToDb(@RequestBody AddNewFilmRequest request){
		filmService.Save(request);
	}
	//All films requests
	@GetMapping("/AllFilms")
	@PreAuthorize("hasAnyRole('Admin','User')")
	public ModelAndView allFilms(){
		return new ModelAndView("Admin/AllFilms");
	}
	
	
	
	//Admin main page requests
	@GetMapping("/AdminMainPage")
	@PreAuthorize("hasRole('Admin')")
	public ModelAndView AdminMainPage(){
		return new ModelAndView("Admin/AdminMainPage");
	}
	
}
