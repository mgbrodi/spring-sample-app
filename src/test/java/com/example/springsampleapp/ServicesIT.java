package com.example.springsampleapp;

import com.example.springsampleapp.model.City;
import com.example.springsampleapp.repository.CityRepository;
import com.example.springsampleapp.service.CityService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;


@SpringBootTest
@ContextConfiguration(initializers = {ServicesIT.Initializer.class})

class ServicesIT {

	private static PostgreSQLContainer sqlContainer;

	static {
		sqlContainer = new PostgreSQLContainer("postgres:10.7")
				.withDatabaseName("integration-tests-db")
				.withUsername("sa")
				.withPassword("sa");
		sqlContainer.start();
	}

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues.of(
					"spring.datasource.url=" + sqlContainer.getJdbcUrl(),
					"spring.datasource.username=" + sqlContainer.getUsername(),
					"spring.datasource.password=" + sqlContainer.getPassword()
			).applyTo(configurableApplicationContext.getEnvironment());
		}
	}

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CityService cityService;

	@Test
	void shouldGetAllCities() {
		// test userService.getAllUsers()
		cityRepository.save(new City(1L,"Roma",100000));
		cityRepository.save(new City(2L,"Firenze",10000));
		Assert.assertEquals(2,cityService.findAll().size());
	}

}
