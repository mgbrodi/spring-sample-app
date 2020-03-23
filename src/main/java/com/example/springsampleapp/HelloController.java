package com.example.springsampleapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Value("${info.app.name:#{null}}")
	private String name;

	@Value("${info.app.version:#{null}}")
	private String version;

	@Value("${spring.profiles.active:none}")
	private String profile;


	@RequestMapping("/")
	public String hello() {
		return "Hello, world.  " + toString();
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("{");
		sb.append("app name='").append(name).append('\'');
		sb.append(", version='").append(version).append('\'');
		sb.append(", profile='").append(profile).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
