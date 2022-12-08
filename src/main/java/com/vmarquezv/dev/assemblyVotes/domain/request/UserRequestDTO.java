package com.vmarquezv.dev.assemblyVotes.domain.request;

import java.sql.Timestamp;

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
	
	private String cpf;
	
	private Timestamp created_on;
	
	private User user;
	
	public User build() {
		User user = new User()
				.setUsername(this.username)
				.setCpf(this.cpf)
				.setCreated_on(this.created_on);
			return user;
	}
	
}
