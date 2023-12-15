package com.courseproject.courseproject.Repository;

import com.courseproject.courseproject.Entity.Film;
import com.courseproject.courseproject.Entity.Role;
import com.courseproject.courseproject.Entity.nestedObjects.Actor;
import com.courseproject.courseproject.Entity.nestedObjects.Director;
import com.courseproject.courseproject.Entity.nestedObjects.Fact;
import com.courseproject.courseproject.Entity.nestedObjects.Genre;
import com.courseproject.courseproject.Entity.dto.AllInfoResponse;
import com.courseproject.courseproject.Entity.dto.NewFilmRequest;
import lombok.AllArgsConstructor;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDataFactory;
import oracle.jdbc.internal.OracleTypes;
import oracle.sql.ORADataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
				typeMap.put("ORAData", ORADataFactory.class);//интерфейс, который позволяет связать пользовательский Java-класс с соответствующим типом данных Oracle
				typeMap.put("OracleData", OracleDataFactory.class);//"OracleData" предоставляет дополнительные возможности для работы с расширенными типами данных Oracle, такими как пользовательские типы объектов (object types) и коллекции
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
	
	public void Save(NewFilmRequest request){
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
									
//									var A = rs.getArray("GENRES");
//									Object[] arrayData = (Object[]) A.getArray();
//									List<Genre> genres = ConvertOraArrayIntoListGenres(A);
									
									film.setGENRES(ConvertOraArrayIntoListGenres(rs.getArray("GENRES")));
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
	
	
	public List<Film> getLikedFilms(Long userID){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName("SharedFunctions")
				.withFunctionName("GET_ALL_LIKED_FILMS")
				.declareParameters(
						new SqlParameter("USER_PROFILE_ID",Types.NUMERIC),
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

//									var A = rs.getArray("GENRES");
//									Object[] arrayData = (Object[]) A.getArray();
//									List<Genre> genres = ConvertOraArrayIntoListGenres(A);
									
									film.setGENRES(ConvertOraArrayIntoListGenres(rs.getArray("GENRES")));
									film.setDIRECTORS(ConvertOraArrayIntoListDirectors(rs.getArray("DIRECTORS")));
									film.setACTORS(ConvertOraArrayIntoListActors(rs.getArray("ACTORS")));
									film.setINTERESTING_FACT(ConvertOraArrayIntoListFacts(rs.getArray("INTERESTING_FACT")));
									
									return film;
								})
				);
		Map<String,Object>inParams = new HashMap<>();
		inParams.put("USER_PROFILE_ID",userID);
		Map<String,Object>outParams = jdbcCall.execute(inParams);
		
		List<Film> films = (List<Film>)outParams.get("RESULT");
		return films;
	}
	public List<Film> GetSavedFilms(Long userID){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName("SharedFunctions")
				.withFunctionName("GET_ALL_SAVED_FILMS")
				.declareParameters(
						new SqlParameter("USER_PROFILE_ID",Types.NUMERIC),
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

//									var A = rs.getArray("GENRES");
//									Object[] arrayData = (Object[]) A.getArray();
//									List<Genre> genres = ConvertOraArrayIntoListGenres(A);
									
									film.setGENRES(ConvertOraArrayIntoListGenres(rs.getArray("GENRES")));
									film.setDIRECTORS(ConvertOraArrayIntoListDirectors(rs.getArray("DIRECTORS")));
									film.setACTORS(ConvertOraArrayIntoListActors(rs.getArray("ACTORS")));
									film.setINTERESTING_FACT(ConvertOraArrayIntoListFacts(rs.getArray("INTERESTING_FACT")));
									
									return film;
								})
				);
		Map<String,Object>inParams = new HashMap<>();
		inParams.put("USER_PROFILE_ID",userID);
		Map<String,Object>outParams = jdbcCall.execute(inParams);
		
		List<Film> films = (List<Film>)outParams.get("RESULT");
		return films;
	}
	
	
	
	
	
	
	
	
	public void DeleteFilm(int id){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("DELETE_FILM_BY_ID")
				.withCatalogName("ADMINFILMFUNCTIONS");
		MapSqlParameterSource inParams = new MapSqlParameterSource()
				.addValue("FILM_ID",id);
		
		jdbcCall.execute(inParams);
	}
	public Film GetFilmByID(int id){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withFunctionName("FIND_BY_ID")
				.withCatalogName("SHAREDFUNCTIONS")
				.declareParameters(
						new SqlParameter("FILM_ID", Types.BIGINT),
						new SqlOutParameter("V_FILM_CURSOR", OracleTypes.CURSOR,
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
									film.setGENRES(ConvertOraArrayIntoListGenres(rs.getArray("GENRES")));
									film.setDIRECTORS(ConvertOraArrayIntoListDirectors(rs.getArray("DIRECTORS")));
									film.setACTORS(ConvertOraArrayIntoListActors(rs.getArray("ACTORS")));
									film.setINTERESTING_FACT(ConvertOraArrayIntoListFacts(rs.getArray("INTERESTING_FACT")));
									
									return film;
								})
				);
		Map<String,Object>inParams = new HashMap<>();
		inParams.put("FILM_ID",id);
		Map<String,Object>outParams;
		List<Film>films = new ArrayList<>();
		
		outParams = jdbcCall.execute(inParams);
		films = (List<Film>) outParams.get("V_FILM_CURSOR");
		return films.get(0);
	}
	public void Update(NewFilmRequest request){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("UPDATE_FILM")
				.withCatalogName("ADMINFILMFUNCTIONS");
		jdbcCall.declareParameters(
				new SqlParameter("FILM_ID", Types.NUMERIC),
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
		inParams.put("FILM_ID",request.getFilmID());
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
	
	public List<AllInfoResponse> GetAllInfoAboutFilmByID(int id){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withFunctionName("GET_ALL_BY_Id")
				.withCatalogName("SHAREDFUNCTIONS")
				.declareParameters(
						new SqlParameter("FILM_ID", Types.NUMERIC),
						new SqlOutParameter("V_FILM_CURSOR", OracleTypes.CURSOR,
								(rs,rownum)->{
									AllInfoResponse film = new AllInfoResponse();
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
									film.setGENRES(ConvertOraArrayIntoListGenres(rs.getArray("GENRES")));
									film.setDIRECTORS(ConvertOraArrayIntoListDirectors(rs.getArray("DIRECTORS")));
									film.setACTORS(ConvertOraArrayIntoListActors(rs.getArray("ACTORS")));
									film.setINTERESTING_FACT(ConvertOraArrayIntoListFacts(rs.getArray("INTERESTING_FACT")));
									film.setUSER_REVIEW_TEXT(rs.getString("USER_REVIEW_TEXT"));
									film.setDATE_OF_REVIEW(rs.getDate("DATE_OF_REVIEW"));
									film.setLOGIN(rs.getString("LOGIN"));
									film.setAVATAR(rs.getBytes("AVATAR"));
									String role = rs.getString("USER_ROLE");
									if(role!=null)
										film.setUSER_ROLE(Role.valueOf(role));
									film.setUSERS_REVIEWS_ON_MOVIE_ID(rs.getLong("USERS_REVIEWS_ON_MOVIE_ID"));
									return film;
								})
				);
		Map<String,Object>inParams = new HashMap<>();
		inParams.put("FILM_ID",id);
		Map<String,Object>outParams;
		List<AllInfoResponse>films = new ArrayList<>();
		
		outParams = jdbcCall.execute(inParams);
		films = (List<AllInfoResponse>) outParams.get("V_FILM_CURSOR");
		return films;
		
		
		
		
		
//		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
//				.withFunctionName("GET_ALL_INFORMATION_ABOUT_FILM_BY_ID")
//				.withCatalogName("ADMINFILMFUNCTIONS")
//				.declareParameters(
//						new SqlParameter("FILM_ID", Types.BIGINT),
//						new SqlOutParameter("V_FILM_CURSOR", OracleTypes.CURSOR,
//								(rs,rownum)->{
//									AllInfoResponse film = new AllInfoResponse();
//									film.setALL_INFORMATION_ABOUT_FILM_ID(rs.getLong("ALL_INFORMATION_ABOUT_FILM_ID"));
//									film.setTITLE(rs.getString("TITLE"));
//									film.setORIGINAL_TITLE(rs.getString( "ORIGINAL_TITLE"));
//									film.setPOSTER(rs.getBytes("POSTER"));
//									film.setYEAR_OF_POSTING(rs.getString("YEAR_OF_POSTING"));
//									film.setCOUNTRY(rs.getString("COUNTRY"));
//									film.setDESCRIPTION(rs.getString("DESCRIPTION"));
//									film.setRATING_IMDb(rs.getFloat("RATING_IMDb"));
//									film.setRATING_KP(rs.getFloat("RATING_KP"));
//									film.setBOX_OFFICE_RECEIPTS(rs.getFloat("BOX_OFFICE_RECEIPTS"));
//									film.setAGE(rs.getLong("AGE"));
//									film.setVIEWING_TIME(rs.getString("VIEWING_TIME"));
//									film.setBUDGET(rs.getFloat("BUDGET"));
//									film.setGENRES(ConvertOraArrayIntoListGenres(rs.getArray("GENRES")));
//									film.setDIRECTORS(ConvertOraArrayIntoListDirectors(rs.getArray("DIRECTORS")));
//									film.setACTORS(ConvertOraArrayIntoListActors(rs.getArray("ACTORS")));
//									film.setINTERESTING_FACT(ConvertOraArrayIntoListFacts(rs.getArray("INTERESTING_FACT")));
//									//film.setUSER_REVIEW_TEXT(rs.getString("USER_REVIEW_TEXT"));
//									//film.setDATE_OF_REVIEW(rs.getDate("DATE_OF_REVIEW"));
//									//film.setLOGIN(rs.getString("LOGIN"));
//									//film.setAVATAR(rs.getBytes("AVATAR"));
//									//String role = rs.getString("USER_ROLE");
//									//film.setUSER_ROLE(Role.valueOf(role));
//									return film;
//								})
//				);
//		Map<String,Object>inParams = new HashMap<>();
//		inParams.put("FILM_ID",id);
//		Map<String,Object>outParams;
//		List<AllInfoResponse>films = new ArrayList<>();
//
//		outParams = jdbcCall.execute(inParams);
//		films = (List<AllInfoResponse>) outParams.get("V_FILM_CURSOR");
//		return films.get(0);
	}
	
	
	
}
