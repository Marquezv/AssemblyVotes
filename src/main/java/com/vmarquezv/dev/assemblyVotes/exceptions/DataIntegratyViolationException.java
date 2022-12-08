package com.vmarquezv.dev.assemblyVotes.exceptions;

public class DataIntegratyViolationException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public DataIntegratyViolationException(String msg) {
		super(msg);
	}
	
}
