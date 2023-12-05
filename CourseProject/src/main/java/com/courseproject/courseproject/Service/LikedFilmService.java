package com.courseproject.courseproject.Service;

import com.courseproject.courseproject.Entity.LikedFilm;
import com.courseproject.courseproject.Repository.LikedFilmsRepository;
import com.courseproject.courseproject.dto.AddToLiked;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikedFilmService {
	private final LikedFilmsRepository likedFilmsRepository;
	public void AddFilmToLiked(AddToLiked addToLiked){
		LikedFilm likedFilm = LikedFilm
				.builder()
				.USER_PROFILE_ID(Long.parseLong(addToLiked.getUserId()))
				.ALL_INFORMATION_ABOUT_FILM(Long.parseLong(addToLiked.getFilmId()))
				.build();
		likedFilmsRepository.AddFilmToLiked(likedFilm);
	}
}
