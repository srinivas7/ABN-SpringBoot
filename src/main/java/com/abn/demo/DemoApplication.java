package com.abn.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.abn")
public class DemoApplication {
	public static void main(String[] args) {
		System.out.println("fasdfadsf");
		SpringApplication.run(DemoApplication.class, args);
	}
}
