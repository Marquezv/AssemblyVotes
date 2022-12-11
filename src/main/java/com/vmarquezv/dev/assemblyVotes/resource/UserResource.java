package com.vmarquezv.dev.assemblyVotes.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vmarquezv.dev.assemblyVotes.domain.request.UserRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.UserResponseDTO;
import com.vmarquezv.dev.assemblyVotes.exceptions.InvalidFormatException;
import com.vmarquezv.dev.assemblyVotes.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	private static final String ID = "/{id}";
	
	@Autowired
	UserService service;
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> insert(@Valid @RequestBody UserRequestDTO userRequestDTO, Errors err) throws Exception{
		if (err.hasErrors()) {
			throw new InvalidFormatException("CPF - Invalid");
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
				.buildAndExpand(service.insert(userRequestDTO).getUser_id()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<UserResponseDTO>> findAll(){
		return ResponseEntity.ok().body(toCollectionModelList(service.findAll().stream()
				.map(session -> addLink(session)).toList()));
	}
	
	@GetMapping(value = ID)
	public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(addLink(service.findById(id).toResponse()));
	}
	
	private UserResponseDTO addLink(UserResponseDTO res) {
		res.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class)
						.findById(res.getUser_id())).withSelfRel());
		
		res.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class)
						.findAll()).withRel(IanaLinkRelations.COLLECTION));
		
		return res;
	}
	
	private CollectionModel<UserResponseDTO> toCollectionModelList(List<UserResponseDTO> sessionResponseDTO) {
		return CollectionModel.of(sessionResponseDTO);
	}
}
