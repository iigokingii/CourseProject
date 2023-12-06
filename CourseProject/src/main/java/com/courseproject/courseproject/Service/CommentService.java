package com.courseproject.courseproject.Service;

import com.courseproject.courseproject.Entity.Comment;
import com.courseproject.courseproject.Repository.CommentRepository;
import com.courseproject.courseproject.Repository.FilmRepository;
import com.courseproject.courseproject.dto.AddCommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	public void AddComment(AddCommentRequest request){
		Long userid = Long.parseLong(request.getUserId());
		Long filmid = Long.parseLong(request.getFilmId());
		var comment = Comment
				.builder()
				.USER_PROFILE_ID(userid)
				.USER_REVIEW_TEXT(request.getComment())
				.ALL_INFORMATION_ABOUT_FILM_ID(filmid)
				.DATE_OF_REVIEW(request.getDate())
				.build();
		commentRepository.AddComment(comment);
	}
	public void DeleteComment(String Stringid){
		int id = Integer.parseInt(Stringid);
		commentRepository.DeleteComment(id);
	}
}
