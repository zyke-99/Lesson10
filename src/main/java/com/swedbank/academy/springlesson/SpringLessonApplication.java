package com.swedbank.academy.springlesson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.swedbank.academy.springlesson.person"})
public class SpringLessonApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringLessonApplication.class, args);
	}

}
