package com.example.springsampleapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	@Value("${spring.datasource.url:none}")
	private static String url;
	@Value("${spring.datasource.username:none}")
	private static String username;
	@Value("${spring.datasource.password:none}")
	private static String password;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		System.out.print("URL: "+url);
		System.out.print("username: "+username);
		System.out.print("password: "+password);
	}

}
