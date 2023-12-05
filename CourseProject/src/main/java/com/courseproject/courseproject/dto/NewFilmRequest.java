package com.courseproject.courseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NewFilmRequest {
	String filmID;
	byte[]poster;
	String trailer;
	String title;
	String originalTitle;
	String yearOfPosting;
	String country;
	String ratingIMdB;
	String ratingKP;
	String boxOfficeReceipts;
	String budget;
	String age;
	String viewingTime;
	String[] genres;
	String[] directors;
	String[] actors;
	String[] interestingFact;
	String description;
}
