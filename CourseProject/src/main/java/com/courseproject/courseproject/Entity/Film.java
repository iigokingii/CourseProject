package com.courseproject.courseproject.Entity;

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
@Entity
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
	Long FRAME;
	String VIEWING_TIME;
	
	//@OneToMany(cascade = CascadeType.ALL)
	//Object[] genres;
	
}
