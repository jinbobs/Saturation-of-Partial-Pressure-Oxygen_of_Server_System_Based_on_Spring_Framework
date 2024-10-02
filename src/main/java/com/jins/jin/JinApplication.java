package com.jins.jin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class  JinApplication {

	public static void main(String[] args) {
		SpringApplication.run(JinApplication.class, args);
	}

}
