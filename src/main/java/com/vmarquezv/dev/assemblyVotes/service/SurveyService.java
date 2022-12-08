package com.vmarquezv.dev.assemblyVotes.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.commons.status.SurveyStatus;
import com.vmarquezv.dev.assemblyVotes.domain.request.SurveyRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.SurveyResponseDTO;
import com.vmarquezv.dev.assemblyVotes.repository.SurveyRepository;

@Service
public class SurveyService {
	
	@Autowired
	SurveyRepository repository;
	
	@Autowired
	UserService userService;
	
	public SurveyResponseDTO insert(SurveyRequestDTO surveyReq) throws Exception {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		surveyReq.setCreated_on(timestamp);
		
		surveyReq.setUser(userService.findById(surveyReq.getUser_id()));
		surveyReq.setSurvey_status(SurveyStatus.OPPEND);
		return repository.save(surveyReq.build()).toResponse();
	}
	
}
