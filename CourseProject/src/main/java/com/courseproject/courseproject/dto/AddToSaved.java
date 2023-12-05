package com.courseproject.courseproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddToSaved {
	String filmId;
	String userId;
}
