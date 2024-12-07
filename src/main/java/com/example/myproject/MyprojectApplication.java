package com.example.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.myproject")
public class MyprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyprojectApplication.class, args);
	}

}
