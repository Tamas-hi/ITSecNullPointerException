package com.webshop;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.imageio.ImageIO;

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
