package com.courseproject.courseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddNewFilmRequest {
	byte[]poster;
	String trailer;
	String movieTitle;
	String movieOriginalTitle;
	String yearOfPosting;
	String country;
	String ratingIMdB;
	String ratingKP;
	String boxOfficeReceipts;
	String budget;
	String age;
	String viewingTime;
	String[] directors;
	String[] actors;
	String[] interestingFact;
	String description;
}
