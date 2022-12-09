package com.vmarquezv.dev.assemblyVotes.resource;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vmarquezv.dev.assemblyVotes.domain.request.VoteRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.VoteResponseDTO;
import com.vmarquezv.dev.assemblyVotes.service.VoteService;

@RestController
@RequestMapping(value = "/votes")
public class VoteResource {
	
	private static final String ID = "/{id}";
	
	@Autowired
	VoteService service;
	
	@PostMapping
	public ResponseEntity<VoteResponseDTO> insert(@RequestBody VoteRequestDTO voteRequestDTO) throws Exception {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
				.buildAndExpand(service.insert(voteRequestDTO).getVote_id().getUser_id()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
