package com.iesports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class IesportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IesportsApplication.class, args);
	}

}
