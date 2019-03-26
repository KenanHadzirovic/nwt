package com.nwt.preferences;

import com.nwt.preferences.entities.Preference;
import com.nwt.preferences.repository.PreferenceRepositoryImpl;
import com.nwt.preferences.repository.interfaces.PreferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsPreferencesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsPreferencesApplication.class, args);
	}
}
