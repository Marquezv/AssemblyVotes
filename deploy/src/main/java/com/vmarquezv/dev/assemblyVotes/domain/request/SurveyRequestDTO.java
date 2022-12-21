package com.vmarquezv.dev.assemblyVotes.domain.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Survey;
import com.vmarquezv.dev.assemblyVotes.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SurveyRequestDTO {
	
	private Long survey_id;

	private String description;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime created_on = LocalDateTime.now();
	
	private Long user_id;
	
	private User user;
	
	public Survey build() {
		Survey survey = new Survey()
				.setDescription(this.description)
				.setUser(this.user)
				.setCreation_on(this.created_on);
		
		return survey;
	}
}
