package com.vmarquezv.dev.assemblyVotes.domain.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vmarquezv.dev.assemblyVotes.commons.annotation.Cpf;
import com.vmarquezv.dev.assemblyVotes.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
	
	private Long user_id;
	
	private String username;
	
	private String password;
	
	@Cpf
	private String cpf;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime created_on = LocalDateTime.now();
	
	public User build() {
		User user = new User()
				.setUsername(this.username)
				.setPassword(password)
				.setCpf(this.cpf)
				.setCreated_on(this.created_on);
			return user;
	}
	
}
