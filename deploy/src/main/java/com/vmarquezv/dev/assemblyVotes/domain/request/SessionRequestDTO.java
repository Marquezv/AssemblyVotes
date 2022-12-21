package com.vmarquezv.dev.assemblyVotes.domain.request;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vmarquezv.dev.assemblyVotes.commons.status.AccessStatus;
import com.vmarquezv.dev.assemblyVotes.commons.status.SessionStatus;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Session;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Survey;
import com.vmarquezv.dev.assemblyVotes.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequestDTO {
	
	private Long session_id;
	
	private Survey survey;
	
	private Long survey_id;
	
	private User user;
	
	private Long user_id;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime started_on;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime closed_on;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime created_on = LocalDateTime.now();
	
	private Integer amount_votes;
	
	private Integer up_votes;
	
	private Integer down_votes;
	
	private AccessStatus access_status;
	
	private SessionStatus session_status;
	
	private List<User> allowed_users;
	
	public Session build() {
		Session session = new Session()
				.setSurvey(this.survey)
				.setUser(this.user)
				.setStarted_on(this.started_on)
				.setClosed_on(this.closed_on)
				.setCreated_on(this.created_on)
				.setAmount_votes(this.amount_votes)
				.setUp_votes(this.up_votes)
				.setDown_votes(this.down_votes)
				.setAccess_status(this.access_status)
				.setSession_status(this.session_status);
				
		return session;
	}
}
