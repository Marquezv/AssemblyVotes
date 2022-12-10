package com.vmarquezv.dev.assemblyVotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AssemblyVotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssemblyVotesApplication.class, args);
	}

}
