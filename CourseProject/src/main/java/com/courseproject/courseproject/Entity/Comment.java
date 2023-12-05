package com.courseproject.courseproject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name ="USERS_REVIEWS_ON_MOVIE")
public class Comment {
	@Id
	@GeneratedValue()
	Long USERS_REVIEWS_ON_MOVIE_ID;
	String USER_REVIEW_TEXT;
	Date DATE_OF_REVIEW;
	Long USER_PROFILE_ID;
	Long ALL_INFORMATION_ABOUT_FILM_ID;
}
