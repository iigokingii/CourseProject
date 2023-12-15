package com.courseproject.courseproject.Service;

import com.courseproject.courseproject.Entity.WatchLater;
import com.courseproject.courseproject.Repository.SavedFilmsRepository;
import com.courseproject.courseproject.Entity.dto.AddToSaved;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavedFilmsService {
	private final SavedFilmsRepository savedFilmsRepository;
	public void AddFilmToSaved(AddToSaved request){
		WatchLater watchLater = WatchLater
				.builder()
				.USER_PROFILE_ID(Long.parseLong(request.getUserId()))
				.ALL_INFORMATION_ABOUT_FILM(Long.parseLong(request.getFilmId()))
				.build();
		savedFilmsRepository.AddFilmToSaved(watchLater);
	}
}
