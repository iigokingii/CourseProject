package com.courseproject.courseproject.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name ="USER_LIKE")
public class LikedFilm {
	@Id
	@GeneratedValue()
	Long USER_LIKE_ID;
	Long ALL_INFORMATION_ABOUT_FILM;
	Long USER_PROFILE_ID;
}
