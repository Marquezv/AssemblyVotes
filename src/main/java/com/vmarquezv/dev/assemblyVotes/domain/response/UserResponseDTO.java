package com.vmarquezv.dev.assemblyVotes.domain.response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
	
	private Long user_id;
	
	private String username;
	
	private Timestamp created_on;
	
}
