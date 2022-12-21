package com.vmarquezv.dev.userApi.domain.entity;

import java.time.LocalDateTime;

import com.vmarquezv.dev.userApi.domain.response.SurveyResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Entity
@Table(name = "SURVEYS")
@ToString
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Survey {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "CREATED_ON")
	private LocalDateTime creation_on;
	
	@ManyToOne
    @JoinColumn(name = "USER_ID")
	private User user;
	

	public SurveyResponseDTO toResponse() {
		SurveyResponseDTO surveyRes = new SurveyResponseDTO()
				.setDescription(this.description)
				.setCreated_on(this.creation_on)
				.setUser_id(this.user.getId())
				.setSurvey_id(this.id);
		return surveyRes;
	}
}
