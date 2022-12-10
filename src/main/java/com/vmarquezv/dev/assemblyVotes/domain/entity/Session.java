package com.vmarquezv.dev.assemblyVotes.domain.entity;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import com.vmarquezv.dev.assemblyVotes.domain.response.SessionResponseDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Entity
@Table(name = "SESSIONS")
@ToString
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Session {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "SURVEY_ID")
    private Survey survey;
	
	@OneToOne
	@JoinColumn(name = "USER_ID")
	private User user;
	
	@Column(name = "STARTED_ON")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date started_on;
	
	@Column(name = "CLOSED_ON")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date closed_on;
	
	@Column(name = "CREATED_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date created_on;
	
	@ColumnDefault("0")
	@Column(name = "AMOUNT_VOTES", nullable = false)
	private Integer amount_votes;
	
	@ColumnDefault("0")
	@Column(name = "UP_VOTES", nullable = false)
	private Integer up_votes;
	
	@ColumnDefault("0")
	@Column(name = "DOWN_VOTES", nullable = false)
	private Integer down_votes;
	
	@Column(name = "ACCESS_STATUS")
	private Integer access_status;
	
	@Column(name = "SESSION_STATUS")
	private Integer session_status;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<User> allowed_users = new LinkedHashSet<User>();
	
	public SessionResponseDTO toResponse() {
		SessionResponseDTO surveyRes = new SessionResponseDTO()
				.setSession_id(this.id)
				.setUser_id(this.user.getId())
				.setSurvey_id(this.survey.getId())
				.setSurvey_description(this.survey.getDescription())
				.setStarted_on(this.started_on)
				.setClosed_on(this.closed_on)
				.setCreated_on(this.created_on)
				.setAmount_votes(this.amount_votes)
				.setUp_votes(this.up_votes)
				.setDown_votes(this.down_votes)
				.setAccess_status(this.access_status)
				.setSession_status(this.session_status);
		
		return surveyRes;
	}
	
}
