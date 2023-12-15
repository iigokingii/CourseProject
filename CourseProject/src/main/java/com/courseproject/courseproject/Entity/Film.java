package com.courseproject.courseproject.Entity;

import com.courseproject.courseproject.Entity.nestedObjects.Actor;
import com.courseproject.courseproject.Entity.nestedObjects.Director;
import com.courseproject.courseproject.Entity.nestedObjects.Fact;
import com.courseproject.courseproject.Entity.nestedObjects.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//TODO entity не дает создать поле
//@Entity
@ToString
@Table(name ="ALL_INFORMATION_ABOUT_FILM")
public class Film {
	@Id
	@GeneratedValue()
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
	
}
