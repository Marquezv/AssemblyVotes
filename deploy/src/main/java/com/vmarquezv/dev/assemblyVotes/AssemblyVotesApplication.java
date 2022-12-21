package com.vmarquezv.dev.assemblyVotes;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



@EnableScheduling
@SpringBootApplication
public class AssemblyVotesApplication {
	
	static final Logger log = 
	        LoggerFactory.getLogger(AssemblyVotesApplication.class);
	
	public static void main(String[] args) {
		log.info("[ SERVER ] - [ STATUS : INITIALIZING ]");
		SpringApplication.run(AssemblyVotesApplication.class, args);
		log.debug("[ SERVER ] - [ STATUS : DEBUG ]" + "[ " + args.length + " ]");

		log.info("[ SERVER ] - [ STATUS : STARTED ]");

	}

}
