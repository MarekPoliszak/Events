package com.marekpoliszak.Spring.Events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringEventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEventsApplication.class, args);

		System.out.println();
		System.out.println("Started!");
		System.out.println();
	}


}
