package com.vmarquezv.dev.assemblyVotes.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.domain.request.SessionRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.SessionResponseDTO;
import com.vmarquezv.dev.assemblyVotes.repository.SessionRepository;

@Service
public class SessionService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SurveyService surveyService;
	
	@Autowired
	SessionRepository repository;
	
	public SessionResponseDTO  insert(SessionRequestDTO sessionReq) {
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		sessionReq.setCreated_on(timestamp);
		sessionReq.setUser(userService.findById(sessionReq.getUser_id()));
		sessionReq.setSurvey(surveyService.findById(sessionReq.getSurvey_id()));
		System.out.println(sessionReq.build());
		return repository.save(sessionReq.build()).toResponse();
	}

	public List<SessionResponseDTO> findAll() {
		return repository.findAll().stream()
				.map(session -> session.toResponse()).collect(Collectors.toList());
	}
}
