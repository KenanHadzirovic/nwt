package com.nwt.preferences;

import com.nwt.preferences.entities.Preference;
import com.nwt.preferences.repository.PreferenceRepositoryImpl;
import com.nwt.preferences.repository.interfaces.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class MsPreferencesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPreferencesApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
