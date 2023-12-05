package com.courseproject.courseproject.Repository;

import com.courseproject.courseproject.Entity.LikedFilm;
import com.courseproject.courseproject.Entity.WatchLater;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;
@AllArgsConstructor
@Component
public class LikedFilmsRepository {
	private static JdbcTemplate jdbcTemplate;
	public static JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}
	@Autowired
	public LikedFilmsRepository(JdbcTemplate jdbcTemplate) {
		LikedFilmsRepository.jdbcTemplate= jdbcTemplate;
	}	
	public void AddFilmToLiked(LikedFilm likedFilm){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("ADD_TO_LIKED")
				.withCatalogName("SHAREDFUNCTIONS");
		
		jdbcCall.declareParameters(
				new SqlParameter("USER_PROFILE_ID", Types.NUMERIC),
				new SqlParameter("ALL_INFORMATION_ABOUT_FILM_ID", Types.NUMERIC)
		);
		
		Map<String, Object> inParams = new HashMap<>();
		inParams.put("USER_PROFILE_ID", likedFilm.getUSER_PROFILE_ID());
		inParams.put("ALL_INFORMATION_ABOUT_FILM_ID", likedFilm.getALL_INFORMATION_ABOUT_FILM());
		
		jdbcCall.execute(inParams);
	}
	
}
