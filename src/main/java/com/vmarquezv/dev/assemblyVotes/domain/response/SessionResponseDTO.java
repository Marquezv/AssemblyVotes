package com.vmarquezv.dev.assemblyVotes.domain.response;

import java.sql.Timestamp;

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
	
	private Timestamp started_on;
	
	private Timestamp closed_on;
	
	private Timestamp created_on;
	
	private Integer amount_votes;
	
	private Integer up_votes;
	
	private Integer down_votes;
	
	private Integer access_status;
	
	private Integer session_status;
}
