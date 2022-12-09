package com.vmarquezv.dev.assemblyVotes.domain.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	private Date started_on;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date closed_on;
    
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date created_on;
	
	private Integer amount_votes;
	
	private Integer up_votes;
	
	private Integer down_votes;
	
	private Integer access_status;
	
	private Integer session_status;
}
