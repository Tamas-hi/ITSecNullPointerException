package com.webshop.controller;

import java.util.List;

import javax.transaction.Transactional;

import com.webshop.model.User;
import com.webshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		return "Hello hello sziasztok!";
	}
	
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
		return new ResponseEntity<>(userService.findAll(),HttpStatus.OK);
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteUser(@PathVariable long id) {
		userService.deleteUserById(id);
		return "successful";
	}
}
