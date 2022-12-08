package com.vmarquezv.dev.assemblyVotes.domain.response;

import java.sql.Timestamp;

import com.vmarquezv.dev.assemblyVotes.commons.status.SurveyStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResponseDTO {
	
	private Long survey_id;
	
	private Long user_id;
	
	private String description;

	private SurveyStatus survey_status;
	
	private Timestamp created_on;
	
}
