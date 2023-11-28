package com.courseproject.courseproject.Entity;

import com.courseproject.courseproject.Repository.nestedObjects.Actor;
import com.courseproject.courseproject.Repository.nestedObjects.Director;
import com.courseproject.courseproject.Repository.nestedObjects.Fact;
import com.courseproject.courseproject.Repository.nestedObjects.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Array;
import java.sql.Types;
import java.util.List;
import java.util.Set;

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
