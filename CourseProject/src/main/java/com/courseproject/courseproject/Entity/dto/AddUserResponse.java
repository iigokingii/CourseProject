package com.courseproject.courseproject.Entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddUserResponse {
	Long ID;
	String EncodedPass;
	String exception="";
	byte[] AVATAR;
}
