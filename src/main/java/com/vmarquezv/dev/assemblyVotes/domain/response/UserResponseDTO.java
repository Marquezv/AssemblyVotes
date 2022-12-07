package com.vmarquezv.dev.assemblyVotes.domain.response;

import java.sql.Timestamp;

import com.vmarquezv.dev.assemblyVotes.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
	
	private Long id;
	
	private String username;
	
	private Timestamp created_on;
	
	public User build() {
		User user = new User()
				.setUsername(this.username)
				.setCreated_on(this.created_on);
			return user;
	}
	
}
