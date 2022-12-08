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

import com.vmarquezv.dev.assemblyVotes.domain.request.UserRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.UserResponseDTO;
import com.vmarquezv.dev.assemblyVotes.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	private static final String ID = "/{id}";
	
	@Autowired
	UserService service;
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> insert(@RequestBody UserRequestDTO userRequestDTO) throws Exception {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
				.buildAndExpand(service.insert(userRequestDTO).getUser_id()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> findAll(){
		return ResponseEntity.ok().body(service.findAll());
	}
	
}
