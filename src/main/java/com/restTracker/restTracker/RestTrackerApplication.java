package com.restTracker.restTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RestTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestTrackerApplication.class, args);
	}

}
