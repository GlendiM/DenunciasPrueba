package com.UniversidaVeracruzana.buzondenuncias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class} )
public class BuzondenunciasApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuzondenunciasApplication.class, args);
	}

}
