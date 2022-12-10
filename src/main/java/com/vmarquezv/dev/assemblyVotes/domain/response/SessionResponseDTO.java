package com.vmarquezv.dev.assemblyVotes.domain.response;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vmarquezv.dev.assemblyVotes.commons.status.AccessStatus;
import com.vmarquezv.dev.assemblyVotes.commons.status.SessionStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionResponseDTO {
	
	private Long session_id;
	
	private Long user_id;
	
	private Long survey_id;
	
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
