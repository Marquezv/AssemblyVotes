package com.vmarquezv.dev.userApi.resource;

import java.net.URI;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

import com.vmarquezv.dev.userApi.domain.request.SurveyRequestDTO;
import com.vmarquezv.dev.userApi.domain.response.SurveyResponseDTO;
import com.vmarquezv.dev.userApi.service.SurveyService;

@RestController
@RequestMapping(value = "/surveys")
public class SurveyResource {
	
	private static final String ID = "/{id}";
	
	private static SurveyService service;
	
	private static RabbitTemplate rabbitTemplate;
	
	@PostMapping
	public ResponseEntity<SurveyResponseDTO> insert(@RequestBody SurveyRequestDTO surveyRequestDTO) throws Exception {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID)
				.buildAndExpand(service.insert(surveyRequestDTO).getUser_id()).toUri();
		
		String routingKey = "sessions.v1.session-created";
		Message message = new Message(uri.getUserInfo().getBytes());
		rabbitTemplate.send(routingKey, message);
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<CollectionModel<SurveyResponseDTO>> findAll() {
		
		List<SurveyResponseDTO> surveyResponseDTOList = service.findAll().stream()
				.map(survey -> service.getSurveyResponse(survey.getSurvey_id()))
				.map(survey -> addLink(survey)).toList();
		
		return ResponseEntity.ok().body(toCollectionModelList(surveyResponseDTOList));
	}
	
	@GetMapping(value = ID)
	public ResponseEntity<SurveyResponseDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(addLink(service.getSurveyResponse(id)));
	}

	private SurveyResponseDTO addLink(SurveyResponseDTO res) {
		res.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(SurveyResource.class)
						.findById(res.getSurvey_id())).withSelfRel());
		
		res.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(SurveyResource.class)
						.findAll()).withRel(IanaLinkRelations.COLLECTION));
		
		res.getUserResponse().add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder.methodOn(UserResource.class)
						.findById(res.getUser_id())).withRel("users"));
		
		return res;
	}
	
	public CollectionModel<SurveyResponseDTO> toCollectionModelList(List<SurveyResponseDTO> surveyResponseDTO) {
		return CollectionModel.of(surveyResponseDTO);
	}
}
