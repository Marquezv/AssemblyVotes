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
public class SurveyResponseDTO {
	
	private Long survey_id;
	
	private Long user_id;
	
	private String description;

	private Integer survey_status;
	
	private Timestamp created_on;
	
	private UserResponseDTO userRes;
}
