package com.courseproject.courseproject.Repository;

import com.courseproject.courseproject.Entity.Comment;
import com.courseproject.courseproject.Entity.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component
public class CommentRepository {
	private static JdbcTemplate jdbcTemplate;
	public static JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}
	@Autowired
	public CommentRepository(JdbcTemplate jdbcTemplate) {
		CommentRepository.jdbcTemplate= jdbcTemplate;
	}
	public void AddComment(Comment comment){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("ADD_COMMENT")
				.withCatalogName("ADMINFILMFUNCTIONS");
		jdbcCall.declareParameters(
				new SqlParameter("USER_REVIEW_TEXT", Types.VARCHAR),
				new SqlParameter("DATE_OF_REVIEW", Types.DATE),
				new SqlParameter("USER_PROFILE_ID", Types.NUMERIC),
				new SqlParameter("ALL_INFORMATION_ABOUT_FILM_ID", Types.NUMERIC)
		);
		java.sql.Date oracleDate = new java.sql.Date(comment.getDATE_OF_REVIEW().getTime());
		Map<String, Object> inParams = new HashMap<>();
		inParams.put("USER_REVIEW_TEXT", comment.getUSER_REVIEW_TEXT());
		inParams.put("DATE_OF_REVIEW", oracleDate);
		inParams.put("USER_PROFILE_ID", comment.getUSER_PROFILE_ID());
		inParams.put("ALL_INFORMATION_ABOUT_FILM_ID", comment.getALL_INFORMATION_ABOUT_FILM_ID());
		
		jdbcCall.execute(inParams);
	}
	public void DeleteComment(int id){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("DELETE_COMMENT")
				.withCatalogName("ADMINFILMFUNCTIONS");
		MapSqlParameterSource inParams = new MapSqlParameterSource()
				.addValue("COMMENT_ID",id);
		jdbcCall.execute(inParams);
	}
	
}
