package com.vmarquezv.dev.assemblyVotes.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vmarquezv.dev.assemblyVotes.domain.request.SessionRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.SessionResponseDTO;
import com.vmarquezv.dev.assemblyVotes.service.AllowedUserSessionService;
import com.vmarquezv.dev.assemblyVotes.service.SessionService;

@RestController
@RequestMapping(value = "/sessions")
public class SessionResource {

	
	private static final String SESSION_ID = "/{id}";
	private static final String USER_ID = "/users/{user_id}";
	private static final String SURVEY_ID = "/surveys/{survey_id}";
	private static final String ADDUSERSESSION = "/add";
	
	@Autowired
	SessionService service;
	
	@Autowired
	AllowedUserSessionService allowedUserSessionService;
	
	@PostMapping
	public ResponseEntity<SessionResponseDTO> insert(@RequestBody SessionRequestDTO sessionRequestDTO) throws Exception {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(SESSION_ID)
				.buildAndExpand(service.insert(sessionRequestDTO).getSession_id()).toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@PostMapping(value = ADDUSERSESSION)
	public ResponseEntity<SessionResponseDTO> addUserSession(@RequestBody SessionRequestDTO sessionRequestDTO) throws Exception {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(SESSION_ID)
				.buildAndExpand(service.addUserSession(sessionRequestDTO).getSession_id()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<SessionResponseDTO>> findAll() {
		return ResponseEntity.ok().body(toCollectionModelList(service.findAll().stream()
				.map(session -> addLink(session)).collect(Collectors.toList())));
	}
	
	@GetMapping(value = USER_ID)
	public ResponseEntity<CollectionModel<SessionResponseDTO>> findAllUserCanVote(@PathVariable Long user_id) {
		
		return ResponseEntity.ok().body(toCollectionModelList(service.findAllUserCanVote(user_id).stream()
				.map(session -> addLink(session)).collect(Collectors.toList())));
	}
	
	@GetMapping(value = SURVEY_ID)
	public ResponseEntity<CollectionModel<SessionResponseDTO>> findAllSurvey(@PathVariable Long survey_id) {
		
		return ResponseEntity.ok().body(toCollectionModelList(service.findAllSurvey(survey_id).stream()
				.map(session -> addLink(session)).collect(Collectors.toList())));
	}
	
	
	@GetMapping(value = SESSION_ID)
	public ResponseEntity<SessionResponseDTO> findById(@PathVariable Long id){
		SessionResponseDTO res = service.findById(id);
		return ResponseEntity.ok().body(addLink(res));
	}
	
	
	
	private SessionResponseDTO addLink(SessionResponseDTO res) {
		res.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(SessionResource.class)
						.findById(res.getSession_id())).withSelfRel());
		
		res.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(SessionResource.class)
						.findAll()).withRel(IanaLinkRelations.COLLECTION));
		
		res.getUserResponse().add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class)
						.findById(res.getUser_id())).withRel("users"));
		
		res.getSurveyResponse().add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(SurveyResource.class)
						.findById(res.getSurvey_id())).withRel("surveys"));
		return res;
	}
	
	private CollectionModel<SessionResponseDTO> toCollectionModelList(List<SessionResponseDTO> sessionResponseDTO) {
		return CollectionModel.of(sessionResponseDTO);
	}
}
