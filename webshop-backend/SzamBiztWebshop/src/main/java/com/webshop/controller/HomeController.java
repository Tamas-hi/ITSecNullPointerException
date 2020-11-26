package com.webshop.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import com.webshop.model.User;
import com.webshop.service.UserServiceImpl;
import com.webshop.SzamBiztWebshopApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	UserServiceImpl userService;
	
	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	@RequestMapping("/")
	public String index() {
		Path path = Paths.get("C:\\Users\\Tomi\\1.caff");
        try {
            byte[] data = Files.readAllBytes(path);
            CaffData result = readData(data);
            System.out.println("---Java Main--");
            System.out.println(result.caption);
            System.out.println(result.creator_name);
            System.out.println(result.image_width);
            System.out.println(result.image_height);
            System.out.println(result.pixels.length);
            System.out.println(result.tags.length);
            System.out.println(Arrays.toString(result.tags));

            BufferedImage image = new BufferedImage((int)result.image_width, (int)result.image_height, BufferedImage.TYPE_INT_RGB);

            int z = 0;
            for (int y = 0; y < (int)result.image_height; y++) {
                for (int x = 0; x < (int)result.image_width; x++) {
                    image.setRGB(x, y, result.pixels[z]);
                    z++;
                }
            }

            File outputFile = new File("output.bmp");
            ImageIO.write(image, "bmp", outputFile);

        } catch (IOException ioException) {
            System.out.println(ioException.getLocalizedMessage());
        }
		return "Hello hello sziasztok!";
	}

	private native CaffData readData(byte[] file);
	
	@RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        return user.getEmail().equals("user") && user.getPassword().equals("password");
    }
	
	@RequestMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "successful";
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
    public void greetingSubmit(@RequestBody User user) {
		userService.registerTheUser(user);
	}
	
	@GetMapping("users")
    public ResponseEntity<List<User>> allUser() {
		return new ResponseEntity<>(userService.findUser(),HttpStatus.OK);
	}
}
