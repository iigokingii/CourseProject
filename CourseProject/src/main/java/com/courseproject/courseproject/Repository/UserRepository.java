package com.courseproject.courseproject.Repository;

import com.courseproject.courseproject.Entity.Role;
import com.courseproject.courseproject.Entity.User;
import lombok.AllArgsConstructor;
import oracle.jdbc.internal.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@AllArgsConstructor
@Component
public class UserRepository{
	private static JdbcTemplate jdbcTemplate;
	public static JdbcTemplate getJdbcTemplate(){
		return jdbcTemplate;
	}
	@Autowired
	public UserRepository(JdbcTemplate jdbcTemplate) {
		UserRepository.jdbcTemplate= jdbcTemplate;
	}
	
	public User findByEMAIL(String email)throws UncategorizedSQLException{
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName("UserProfileFunctions")
				.withFunctionName("findByEMAIL")
				.declareParameters(
						new SqlParameter("UEMAIL", Types.VARCHAR),
						new SqlOutParameter("V_USER_CURSOR", OracleTypes.CURSOR,
								(rs,rownum)->{
									User user = new User();
									user.setUSER_PROFILE_ID(rs.getLong("USER_PROFILE_ID"));
									//System.out.println("rs.getLong(0):"+rs.getLong(0));
									user.setLOGIN(rs.getString ("LOGIN"));
									user.setAVATAR(rs.getString("AVATAR"));
									user.setEMAIL(rs.getString("EMAIL"));
									user.setPASSWORD(rs.getString("PASSWORD"));
									String role =rs.getString("USER_ROLE");
									user.setUSER_ROLE(Role.valueOf(role));
									return user;
								})
				);
		Map<String,Object>inParams = new HashMap<>();
		inParams.put("UEMAIL",email);
		Map<String,Object>outParams;
		List<User>users = new ArrayList<>();
		//todo обработка искл
		outParams = jdbcCall.execute(inParams);
		users = (List<User>) outParams.get("V_USER_CURSOR");
		return users.get(0);
	}
	
	public User findByEMAILandPASSWORD(String email,String password){
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName("UserProfileFunctions")
				.withFunctionName("findByEMAILandPASSWORD")
				.declareParameters(
						new SqlParameter("UEMAIL", Types.VARCHAR),
						new SqlParameter("UPASSWORD", Types.VARCHAR),
						new SqlOutParameter("V_USER_CURSOR", OracleTypes.CURSOR,
								(rs,rownum)->{
									User user = new User();
									user.setUSER_PROFILE_ID(rs.getLong("USER_PROFILE_ID"));
									//System.out.println("rs.getLong(0):"+rs.getLong(0));
									user.setLOGIN(rs.getString ("LOGIN"));
									user.setAVATAR(rs.getString("AVATAR"));
									user.setEMAIL(rs.getString("EMAIL"));
									user.setPASSWORD(rs.getString("PASSWORD"));
									String role =rs.getString("USER_ROLE");
									user.setUSER_ROLE(Role.valueOf(role));
									return user;
								})
				);
		Map<String,Object>inParams = new HashMap<>();
		inParams.put("UEMAIL",email);
		inParams.put("UPASSWORD",password);
		Map<String,Object>outParams = jdbcCall.execute(inParams);
		
		@SuppressWarnings("unchecked")
		List<User>users = (List<User>) outParams.get("V_USER_CURSOR");
		return users.get(0);
	}
	
	public User save(User user) throws UncategorizedSQLException {
		SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("ADDUSER")
				.withCatalogName("UserProfileFunctions");
		MapSqlParameterSource inParams = new MapSqlParameterSource()
				.addValue("LOGIN",user.getLOGIN())
				.addValue("AVATAR",user.getAVATAR())
				.addValue("EMAIL",user.getEMAIL())
				.addValue("PASSWORD",user.getPassword())
				.addValue("USER_ROLE",user.getUSER_ROLE());
		jdbcCall.execute(inParams);
		
		return user;
	};
}
