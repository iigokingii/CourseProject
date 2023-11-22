package com.courseproject.courseproject.dto;

import com.courseproject.courseproject.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModelRequest {
	String user_PROFILE_ID;
	String login;
	String email;
	Role user_ROLE;
	byte[] avatar;
	String password;
}
