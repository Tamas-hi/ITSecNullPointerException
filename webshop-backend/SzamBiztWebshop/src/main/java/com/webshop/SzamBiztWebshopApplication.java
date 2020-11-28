package com.webshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SzamBiztWebshopApplication {
	static {
		System.loadLibrary("parser");
	}

	public static void main(String[] args) {
        SpringApplication.run(SzamBiztWebshopApplication.class, args);
	}
}
