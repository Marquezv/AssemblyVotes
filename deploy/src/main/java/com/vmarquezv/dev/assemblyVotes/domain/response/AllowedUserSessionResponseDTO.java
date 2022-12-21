package com.vmarquezv.dev.assemblyVotes.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllowedUserSessionResponseDTO {
	
	private Long session_id;
	
	private Long user_id;
	
}
