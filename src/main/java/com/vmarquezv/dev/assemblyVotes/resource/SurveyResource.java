package com.vmarquezv.dev.assemblyVotes.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vmarquezv.dev.assemblyVotes.domain.request.SurveyRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.SurveyResponseDTO;
import com.vmarquezv.dev.assemblyVotes.service.SurveyService;

@RestController
@RequestMapping(value = "/surveys")
public class SurveyResource {
	
	private static final String ID = "/{id}";
	
	@Autowired
	SurveyService service;
	
	@PostMapping
	public ResponseEntity<SurveyResponseDTO> insert(@RequestBody SurveyRequestDTO surveyRequestDTO) throws Exception {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
				.buildAndExpand(service.insert(surveyRequestDTO).getUser_id()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
