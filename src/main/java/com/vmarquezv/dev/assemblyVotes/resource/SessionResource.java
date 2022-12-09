package com.vmarquezv.dev.assemblyVotes.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vmarquezv.dev.assemblyVotes.domain.request.SessionRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.SessionResponseDTO;
import com.vmarquezv.dev.assemblyVotes.service.SessionService;

@RestController
@RequestMapping(value = "/session")
public class SessionResource {

	private static final String ID = "/{id}";
	
	@Autowired
	SessionService service;
	
	@PostMapping
	public ResponseEntity<SessionResponseDTO> insert(@RequestBody SessionRequestDTO sessionRequestDTO) throws Exception {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
				.buildAndExpand(service.insert(sessionRequestDTO).getSurvey_id()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<SessionResponseDTO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
}
