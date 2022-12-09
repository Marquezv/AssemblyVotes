package com.vmarquezv.dev.assemblyVotes.domain.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private Date created_on;
	
}
