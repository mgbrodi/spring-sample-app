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
		sb.append("<center><p>Hello, Prasanna!! Ciao Gabry!!</center>  <br> I wonder if you ever visited any of the following cities: <br><ul>");
		for(City city: cityService.findAll()) {
			sb.append("<li>"+city.getName()+"</li>");
		}
		sb.append("</ul><br></center> ...you should!<br><hr>");
		sb.append(toString());
		return  sb.toString();
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
