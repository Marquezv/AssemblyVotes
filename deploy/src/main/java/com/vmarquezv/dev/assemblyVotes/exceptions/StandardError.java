package com.vmarquezv.dev.assemblyVotes.exceptions;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandardError {
	
	private Timestamp timestamp;
	
	private Integer status;
	
	private String error;
	
	private String path;
	
}
