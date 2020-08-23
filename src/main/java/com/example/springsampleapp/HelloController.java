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

    @Value("${spring.datasource.url:none}")
    private String url;
    @Value("${spring.datasource.username:none}")
    private String username;
    @Value("${spring.datasource.password:none}")
    private String password;

	@RequestMapping("/")
	public String hello() {
		final StringBuffer sb = new StringBuffer();
		sb.append("<p>Hello, Prasanna!! Ciao Gabry!! <br> Have you ever been in:<br><ul>");
		for(City city: cityService.findAll()) {
			sb.append("<li>"+city.getName()+"</li>");
		}
		sb.append("</ul><br>");
		sb.append(toString());
		return  sb.toString();
	}

	@Override
	public String toString() {
		final StringBuffer sb = new StringBuffer("{");
		sb.append("app name='").append(name).append('\'');
		sb.append(", version='").append(version).append('\'');
        sb.append(", profile='").append(profile).append('\'');
        sb.append(", url='").append(url).append('\'');
        sb.append(", username='").append(username).append('\'');
        sb.append(", password='").append(password).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
