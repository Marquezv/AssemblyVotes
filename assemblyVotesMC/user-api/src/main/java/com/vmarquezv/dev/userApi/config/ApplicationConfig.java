package com.vmarquezv.dev.userApi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class ApplicationConfig {
	
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
}
