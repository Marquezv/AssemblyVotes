package com.vmarquezv.dev.userApi.exceptions;

public class ObjectNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
}
