package com.vmarquezv.dev.assemblyVotes.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.commons.status.SurveyStatus;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Survey;
import com.vmarquezv.dev.assemblyVotes.domain.request.SurveyRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.SurveyResponseDTO;
import com.vmarquezv.dev.assemblyVotes.exceptions.ObjectNotFoundException;
import com.vmarquezv.dev.assemblyVotes.repository.SurveyRepository;

@Service
public class SurveyService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SurveyRepository repository;
	
	public SurveyResponseDTO insert(SurveyRequestDTO surveyReq) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		surveyReq.setCreated_on(timestamp);
		
		surveyReq.setUser(userService.findById(surveyReq.getUser_id()));
		surveyReq.setSurvey_status(SurveyStatus.OPPEND);
		return repository.save(surveyReq.build()).toResponse();
	}
	
	public Survey findById(Long id) {
		return repository.findById(id)
				.orElseThrow(
						() -> new ObjectNotFoundException("SURVEY_ID - NOT_FOUND"));
	}
	
	public List<SurveyResponseDTO> findAll() {
		return repository.findAll().stream().map(survey -> survey.toResponse())
			.collect(Collectors.toList());
	}
}
