package com.vmarquezv.dev.userApi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApiApplication {

	static final Logger log = 
	        LoggerFactory.getLogger(UserApiApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(UserApiApplication.class, args);
	}

}
