package com.courseproject.courseproject.Repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component
public class VisitRepository {
	private static JdbcTemplate jdbcTemplate;
	public static JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}
	@Autowired
	public VisitRepository(JdbcTemplate jdbcTemplate) {
		VisitRepository.jdbcTemplate= jdbcTemplate;
	}
	public void AddNewVisit(){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("ADD_NEW_VISIT")
				.withCatalogName("SHAREDFUNCTIONS");
		jdbcCall.declareParameters(new SqlParameter("DATE_OF_VISIT", Types.DATE));
		Date currentDate = new Date();
		// Конвертация в тип java.sql.Date
		java.sql.Date oracleDate = new java.sql.Date(currentDate.getTime());
		
		Map<String, Object> inParams = new HashMap<>();
		inParams.put("DATE_OF_VISIT", oracleDate);
		
		jdbcCall.execute(inParams);
	}
}
