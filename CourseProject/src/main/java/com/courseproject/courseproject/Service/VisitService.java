package com.courseproject.courseproject.Service;

import com.courseproject.courseproject.Repository.CommentRepository;
import com.courseproject.courseproject.Repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VisitService {
	private final VisitRepository visitRepository;
	
	public void AddNewVisit(){
		visitRepository.AddNewVisit();
	}
}
