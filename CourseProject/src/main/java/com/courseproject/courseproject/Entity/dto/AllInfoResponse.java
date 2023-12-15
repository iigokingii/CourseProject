package com.courseproject.courseproject.Entity.dto;

import com.courseproject.courseproject.Entity.Role;
import com.courseproject.courseproject.Entity.nestedObjects.Actor;
import com.courseproject.courseproject.Entity.nestedObjects.Director;
import com.courseproject.courseproject.Entity.nestedObjects.Fact;
import com.courseproject.courseproject.Entity.nestedObjects.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllInfoResponse {
	Long ALL_INFORMATION_ABOUT_FILM_ID;
	String TITLE;
	String ORIGINAL_TITLE;
	byte[] POSTER;
	String YEAR_OF_POSTING;
	String COUNTRY;
	String DESCRIPTION;
	Float RATING_IMDb;
	Float RATING_KP;
	Float BOX_OFFICE_RECEIPTS;
	Float BUDGET;
	Long AGE;
	String VIEWING_TIME;
	List<Genre> GENRES;
	List<Director> DIRECTORS;
	List<Actor> ACTORS;
	List<Fact> INTERESTING_FACT;
	String LOGIN;
	byte[] AVATAR;
	Role USER_ROLE;
	String USER_REVIEW_TEXT;
	Date DATE_OF_REVIEW;
	Long USERS_REVIEWS_ON_MOVIE_ID;
	
}
