package com.vmarquezv.dev.assemblyVotes.domain.request;

import java.sql.Timestamp;

import com.vmarquezv.dev.assemblyVotes.domain.entity.Session;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Survey;
import com.vmarquezv.dev.assemblyVotes.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequestDTO {
	
	private Long session_id;
	
	private Timestamp creation_on;
	
	private Survey survey;
	
	private Long survey_id;
	
	private User user;
	
	private Long user_id;
	
	private String started_on;
	
	private String closed_on;
	
	private Timestamp created_on;
	
	private Integer amount_votes;
	
	private Integer up_votes;
	
	private Integer down_votes;
	
	private Integer access_status;
	
	private Integer session_status;

	public Session build() {
		Session session = new Session()
				.setSurvey(this.survey)
				.setUser(this.user)
				.setStarted_on(Timestamp.valueOf(started_on))
				.setClosed_on(Timestamp.valueOf(closed_on))
				.setCreated_on(this.created_on)
				.setAmount_votes(this.amount_votes)
				.setUp_votes(this.up_votes)
				.setDown_votes(this.down_votes)
				.setAccess_status(this.access_status)
				.setSession_status(this.session_status);
				
		return session;
	}
}
