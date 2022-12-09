package com.vmarquezv.dev.assemblyVotes.domain.entity;

import java.util.Date;

import com.vmarquezv.dev.assemblyVotes.domain.response.UserResponseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Entity
@Table(name = "USERS")
@ToString
@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "CPF", unique = true)
	private String cpf;
	
	@Column(name = "CREATED_ON")
	private Date created_on;
	
	public UserResponseDTO toResponse() {
		UserResponseDTO userRes = new UserResponseDTO()
				.setUser_id(this.id)
				.setUsername(this.username)
				.setCreated_on(this.created_on);
			return userRes;
	}
}