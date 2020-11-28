package com.webshop.controller;

import java.util.List;
import java.util.Set;

import com.webshop.model.Role;
import com.webshop.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.service.authentication.UserServiceImpl;

@RestController
@RequestMapping("/api")
public class UserController {

	private final UserServiceImpl userService;

	public UserController(UserServiceImpl userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserDetails> login(@RequestBody User user) {
		return userService.login(user);
    }

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<Boolean> logout() {
		return userService.logout();
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Boolean> register(@RequestBody User user) {
		return userService.register(user);
	}
	
	@RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<User>> allUser() {
		return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Set<Role>> getUser(@PathVariable long id) {
		return new ResponseEntity<>(userService.getUserRoleById(id),HttpStatus.OK);
	}
}
