package com.example.roomiesync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
public class RoomiesyncApplication {
	
	public static final Logger log = (Logger) LoggerFactory.getLogger(ApplicationListener.class);
	public static void main(String[] args) {
		SpringApplication.run(RoomiesyncApplication.class, args);

		log.info("Shubham");
	}

}
