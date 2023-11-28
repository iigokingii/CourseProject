package com.courseproject.courseproject.Repository;

import com.courseproject.courseproject.Entity.Film;
import com.courseproject.courseproject.Entity.Role;
import com.courseproject.courseproject.Entity.User;
import com.courseproject.courseproject.Repository.nestedObjects.Actor;
import com.courseproject.courseproject.Repository.nestedObjects.Director;
import com.courseproject.courseproject.Repository.nestedObjects.Fact;
import com.courseproject.courseproject.Repository.nestedObjects.Genre;
import com.courseproject.courseproject.dto.AddNewFilmRequest;
import lombok.AllArgsConstructor;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ORADataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
	private List<Genre> ConvertOraArrayIntoListGenres(Array genreArr){
		List<Genre> genres = new ArrayList();
		try {
			Object [] arr = (Object[])genreArr.getArray();
			for (var elem:arr) {
				if(elem instanceof oracle.sql.STRUCT){
					oracle.sql.STRUCT struct = (oracle.sql.STRUCT) elem;
					Object[] attributes = struct.getAttributes();
					Genre genre = new Genre();
					genre.setGenreId(((BigDecimal)attributes[0]).intValue());
					genre.setGenreName((String) attributes[1]);
					genres.add(genre);
				}
			}
			
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		finally {
			return genres;
		}
	}
	private List<Director> ConvertOraArrayIntoListDirectors(Array directorArr){
		List<Director> genres = new ArrayList();
		try {
			Object [] arr = (Object[])directorArr.getArray();
			for (var elem:arr) {
				if(elem instanceof oracle.sql.STRUCT){
					oracle.sql.STRUCT struct = (oracle.sql.STRUCT) elem;
					Object[] attributes = struct.getAttributes();
					Director director = new Director();
					director.setDirectorId(((BigDecimal)attributes[0]).intValue());
					director.setDirectorName((String) attributes[1]);
					genres.add(director);
				}
			}
			
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		finally {
			return genres;
		}
	}
	
	private List<Actor> ConvertOraArrayIntoListActors(Array actorArr){
		List<Actor> actors = new ArrayList();
		try {
			Object [] arr = (Object[])actorArr.getArray();
			for (var elem:arr) {
				if(elem instanceof oracle.sql.STRUCT){
					oracle.sql.STRUCT struct = (oracle.sql.STRUCT) elem;
					Object[] attributes = struct.getAttributes();
					Actor actor = new Actor();
					actor.setActorId(((BigDecimal)attributes[0]).intValue());
					actor.setActorName((String) attributes[1]);
					actors.add(actor);
				}
			}
			
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		finally {
			return actors;
		}
	}
	private List<Fact> ConvertOraArrayIntoListFacts(Array factArr){
		List<Fact> facts = new ArrayList();
		try {
			Object [] arr = (Object[])factArr.getArray();
			for (var elem:arr) {
				if(elem instanceof oracle.sql.STRUCT){
					oracle.sql.STRUCT struct = (oracle.sql.STRUCT) elem;
					Object[] attributes = struct.getAttributes();
					Fact fact = new Fact();
					fact.setFactId(((BigDecimal)attributes[0]).intValue());
					fact.setFact((String) attributes[1]);
					facts.add(fact);
				}
			}
		}
		catch (Exception ex){
			System.out.println(ex.getMessage());
		}
		finally {
			return facts;
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
	public List<Film> getFilms(){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName("SharedFunctions")
				.withFunctionName("GET_ALL_DATA_FROM_TABLE")
				.declareParameters(
						new SqlParameter("table_name",Types.VARCHAR),
						new SqlOutParameter("RESULT",OracleTypes.CURSOR,
								(rs,rownum)->{
									Film film = new Film();
									film.setALL_INFORMATION_ABOUT_FILM_ID(rs.getLong("ALL_INFORMATION_ABOUT_FILM_ID"));
									film.setTITLE(rs.getString("TITLE"));
									film.setORIGINAL_TITLE(rs.getString( "ORIGINAL_TITLE"));
									film.setPOSTER(rs.getBytes("POSTER"));
									film.setYEAR_OF_POSTING(rs.getString("YEAR_OF_POSTING"));
									film.setCOUNTRY(rs.getString("COUNTRY"));
									film.setDESCRIPTION(rs.getString("DESCRIPTION"));
									film.setRATING_IMDb(rs.getFloat("RATING_IMDb"));
									film.setRATING_KP(rs.getFloat("RATING_KP"));
									film.setBOX_OFFICE_RECEIPTS(rs.getFloat("BOX_OFFICE_RECEIPTS"));
									film.setAGE(rs.getLong("AGE"));
									film.setVIEWING_TIME(rs.getString("VIEWING_TIME"));
									film.setBUDGET(rs.getFloat("BUDGET"));
									
									var A = rs.getArray("GENRES");
									Object[] arrayData = (Object[]) A.getArray();
									List<Genre> genres = ConvertOraArrayIntoListGenres(A);
//									film.setGENRES((rs.getArray("GENRES")).getArray());
//									var b = A.getArray();
//									var G = rs.getArray("GENRES");
//									var c = G.getResultSet();
									
									film.setGENRES(genres);
									film.setDIRECTORS(ConvertOraArrayIntoListDirectors(rs.getArray("DIRECTORS")));
									film.setACTORS(ConvertOraArrayIntoListActors(rs.getArray("ACTORS")));
									film.setINTERESTING_FACT(ConvertOraArrayIntoListFacts(rs.getArray("INTERESTING_FACT")));
									
									return film;
								})
				);
		Map<String,Object>inParams = new HashMap<>();
		inParams.put("table_name","ALL_INFORMATION_ABOUT_FILM");
		Map<String,Object>outParams = jdbcCall.execute(inParams);
		
		List<Film> films = (List<Film>)outParams.get("RESULT");
		return films;
	}
}
