package com.courseproject.courseproject.Entity.dto;

import com.courseproject.courseproject.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthenticationResponse {
	String token;
	String exception="";
	Role role;
}
