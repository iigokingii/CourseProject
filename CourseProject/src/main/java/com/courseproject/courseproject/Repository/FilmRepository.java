package com.courseproject.courseproject.Repository;

import com.courseproject.courseproject.Repository.nestedObjects.Genre;
import com.courseproject.courseproject.dto.AddNewFilmRequest;
import lombok.AllArgsConstructor;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ORADataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Component
public class FilmRepository {
	private static JdbcTemplate jdbcTemplate;
	public static JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}
	@Autowired
	public FilmRepository(JdbcTemplate jdbcTemplate) {
		FilmRepository.jdbcTemplate= jdbcTemplate;
	}
	private Array CreateCustomTypeGenreInJavaToOracleDb(String[] genres){
		Array a = null;
		try {
			OracleConnection oracleConnection = null;
			Connection connection= jdbcTemplate.getDataSource().getConnection();
			if (connection.isWrapperFor(OracleConnection.class)) {
				oracleConnection = connection.unwrap(OracleConnection.class);
				Map<String, Class<?>> typeMap = oracleConnection.getTypeMap();
				typeMap.put("ORAData", ORADataFactory.class);
				typeMap.put("OracleData", OracleDataFactory.class);
				oracleConnection.setTypeMap(typeMap);
				Object[] objs = new Object[genres.length];
				for (int i =0;i<genres.length;i++){
					objs[i]=new Genre(i,genres[i]);
				}
				a = oracleConnection.createARRAY("GENRE_TYPE_INSTANCE",objs);
			}
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		finally {
			return a;
		}
	}
	private Array CreateCustomTypeDirectorInJavaToOracleDb(String[] genres){
		Array a = null;
		try {
			OracleConnection oracleConnection = null;
			Connection connection= jdbcTemplate.getDataSource().getConnection();
			if (connection.isWrapperFor(OracleConnection.class)) {
				oracleConnection = connection.unwrap(OracleConnection.class);
				Map<String, Class<?>> typeMap = oracleConnection.getTypeMap();
				typeMap.put("ORAData", ORADataFactory.class);
				typeMap.put("OracleData", OracleDataFactory.class);
				oracleConnection.setTypeMap(typeMap);
				Object[] objs = new Object[genres.length];
				for (int i =0;i<genres.length;i++){
					objs[i]=new Genre(i,genres[i]);
				}
				a = oracleConnection.createARRAY("DIRECTOR_TYPE_INSTANCE",objs);
			}
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		finally {
			return a;
		}
	}
	
	private Array CreateCustomTypeActorInJavaToOracleDb(String[] genres){
		Array a = null;
		try {
			OracleConnection oracleConnection = null;
			Connection connection= jdbcTemplate.getDataSource().getConnection();
			if (connection.isWrapperFor(OracleConnection.class)) {
				oracleConnection = connection.unwrap(OracleConnection.class);
				Map<String, Class<?>> typeMap = oracleConnection.getTypeMap();
				typeMap.put("ORAData", ORADataFactory.class);
				typeMap.put("OracleData", OracleDataFactory.class);
				oracleConnection.setTypeMap(typeMap);
				Object[] objs = new Object[genres.length];
				for (int i =0;i<genres.length;i++){
					objs[i]=new Genre(i,genres[i]);
				}
				a = oracleConnection.createARRAY("ACTOR_TYPE_INSTANCE",objs);
			}
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		finally {
			return a;
		}
	}
	
	private Array CreateCustomTypeFactInJavaToOracleDb(String[] genres){
		Array a = null;
		try {
			OracleConnection oracleConnection = null;
			Connection connection= jdbcTemplate.getDataSource().getConnection();
			if (connection.isWrapperFor(OracleConnection.class)) {
				oracleConnection = connection.unwrap(OracleConnection.class);
				Map<String, Class<?>> typeMap = oracleConnection.getTypeMap();
				typeMap.put("ORAData", ORADataFactory.class);
				typeMap.put("OracleData", OracleDataFactory.class);
				oracleConnection.setTypeMap(typeMap);
				Object[] objs = new Object[genres.length];
				for (int i =0;i<genres.length;i++){
					objs[i]=new Genre(i,genres[i]);
				}
				a = oracleConnection.createARRAY("INTERESTING_FACT_TYPE_INSTANCE",objs);
			}
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		finally {
			return a;
		}
	}
	public void Save(AddNewFilmRequest request){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("ADD_FILM")
				.withCatalogName("ADMINFILMFUNCTIONS");
		jdbcCall.declareParameters(
				new SqlParameter("TITLE", Types.VARCHAR),
				new SqlParameter("ORIGINAL_TITLE", Types.VARCHAR),
				new SqlParameter("POSTER", Types.BLOB),
				new SqlParameter("YEAR_OF_POSTING", Types.VARCHAR),
				new SqlParameter("COUNTRY", Types.VARCHAR),
				new SqlParameter("DESCRIPTION", Types.VARCHAR),
				new SqlParameter("RATING_IMDb", Types.FLOAT),
				new SqlParameter("RATING_KP", Types.FLOAT),
				new SqlParameter("BOX_OFFICE_RECEIPTS", Types.FLOAT),
				new SqlParameter("BUDGET", Types.FLOAT),
				new SqlParameter("AGE", OracleTypes.NUMBER),
				new SqlParameter("VIEWING_TIME", Types.VARCHAR),
				new SqlParameter("GENRES", Types.ARRAY),
				new SqlParameter("DIRECTORS", Types.ARRAY),
				new SqlParameter("ACTORS", Types.ARRAY),
				new SqlParameter("INTERESTING_FACT", Types.ARRAY)
		);
		
		var G = CreateCustomTypeGenreInJavaToOracleDb(request.getGenres());
		var D = CreateCustomTypeDirectorInJavaToOracleDb(request.getDirectors());
		var A = CreateCustomTypeActorInJavaToOracleDb(request.getActors());
		var F = CreateCustomTypeFactInJavaToOracleDb(request.getInterestingFact());
		
		Map<String, Object> inParams = new HashMap<>();
		inParams.put("TITLE", request.getTitle());
		inParams.put("ORIGINAL_TITLE", request.getOriginalTitle());
		inParams.put("POSTER", new SqlLobValue(request.getPoster(), new DefaultLobHandler()));
		inParams.put("YEAR_OF_POSTING", request.getYearOfPosting());
		inParams.put("COUNTRY", request.getCountry());
		inParams.put("DESCRIPTION", request.getDescription());
		inParams.put("RATING_IMDb", Float.parseFloat(request.getRatingIMdB()));
		inParams.put("RATING_KP", Float.parseFloat(request.getRatingKP()));
		inParams.put("BOX_OFFICE_RECEIPTS", Float.parseFloat(request.getBoxOfficeReceipts()));
		inParams.put("BUDGET", Float.parseFloat(request.getBudget()));
		inParams.put("AGE", Integer.parseInt(request.getAge()));
		inParams.put("VIEWING_TIME", request.getViewingTime());
		inParams.put("GENRES",G);
		inParams.put("DIRECTORS",D);
		inParams.put("ACTORS",A);
		inParams.put("INTERESTING_FACT",F);
		
		jdbcCall.execute(inParams);
	}
	
}
