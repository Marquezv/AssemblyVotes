package com.vmarquezv.dev.assemblyVotes.domain.response;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Relation(collectionRelation = "surveys")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponseDTO extends RepresentationModel<SurveyResponseDTO>{
	
	private Long survey_id;
	
	private Long user_id;
	
	private UserResponseDTO userResponse;

	private String description;
	
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime created_on;
	
	
}
