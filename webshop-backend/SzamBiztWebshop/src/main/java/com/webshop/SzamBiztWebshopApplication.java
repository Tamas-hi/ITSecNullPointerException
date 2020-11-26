package com.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SzamBiztWebshopApplication {
	static {
		System.loadLibrary("parser");
	}

	public static void main(String[] args) {
		SpringApplication.run(SzamBiztWebshopApplication.class, args);
	}

}
