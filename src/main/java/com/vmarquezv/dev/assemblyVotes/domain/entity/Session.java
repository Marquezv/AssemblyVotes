package com.vmarquezv.dev.assemblyVotes.domain.entity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedHashSet;

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
	
	@Column(name = "OPENING_DATE")
	private Timestamp opening_date;
	
	@Column(name = "CREATION_DATE")
	private Timestamp creation_date;
	
	@Column(name = "SESSION_STATUS")
	private Integer session_status;
	
	@Column(name = "AMOUNT_VOTES")
	private Integer amount_votes;
	
	@Column(name = "UP_VOTES")
	private Integer up_votes;
	
	@Column(name = "DOWN_VOTES")
	private Integer down_votes;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Collection<User> votos = new LinkedHashSet<User>();
	
}
