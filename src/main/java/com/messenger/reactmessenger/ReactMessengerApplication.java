package com.messenger.reactmessenger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ReactMessengerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactMessengerApplication.class, args);
	}

}
