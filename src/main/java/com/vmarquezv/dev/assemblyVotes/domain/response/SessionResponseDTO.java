package com.vmarquezv.dev.assemblyVotes.domain.response;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vmarquezv.dev.assemblyVotes.commons.status.AccessStatus;
import com.vmarquezv.dev.assemblyVotes.commons.status.SessionStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Relation(collectionRelation = "sessions")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponseDTO extends RepresentationModel<SessionResponseDTO>{
	
	private Long session_id;
	
	@JsonIgnore
	private Long user_id;
	
	private UserResponseDTO userResponse;
	
	@JsonIgnore
	private Long survey_id;
	
	private SurveyResponseDTO surveyResponse;
	
	private String survey_description;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime started_on;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime closed_on;
    
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime created_on;
	
	private Integer amount_votes;
	
	private Integer up_votes;
	
	private Integer down_votes;
	
	private AccessStatus access_status;
	
	private SessionStatus session_status;
	
	private List<AllowedUserSessionResponseDTO> allowedUserSession;
	
}
