package com.example.springsampleapp;

import com.example.springsampleapp.model.City;
import com.example.springsampleapp.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	private ICityService cityService;

	@Value("${info.app.name:#{null}}")
	private String name;

	@Value("${info.app.version:#{null}}")
	private String version;

	@Value("${spring.profiles.active:none}")
	private String profile;


	@RequestMapping("/")
	public String hello() {
		final StringBuffer sb = new StringBuffer();
		sb.append("<center><h1><p style=\"color:blue\">Hello, SpringOne 2020 Attendees!!</p></h1>");
		sb.append("<p style=\"color:green\"> How's it going?</p></center>  </h1>");
		sb.append("<p style=\"color:purple\"><br><center><h2> I wonder if you ever visited any of the following cities: <h2><br><ul>");
		for(City city: cityService.findAll()) {
			sb.append("<li>"+city.getName()+"</li>");
		}
		try {
			//For failure scenario put 5000
			Thread.sleep(10);
		}
		catch(InterruptedException e) {
			System.out.println(e);
		}
		sb.append("</ul><br> ...you should!<br></center><p>");
		sb.append(toString());
		return  sb.toString();
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("{");
		sb.append("app name='").append(name).append('\'');
        sb.append(", profile='").append(profile).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
