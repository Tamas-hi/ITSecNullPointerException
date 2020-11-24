package com.webshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.model.Customer;
import com.webshop.service.CaffPostService;
import com.webshop.service.UserServiceImpl;

@RestController
public class HomeController {
	
	CaffPostService caffPostService;
	UserServiceImpl userService;
	
	@Autowired
	public void setCaffPostService(CaffPostService caffPostService) {
		this.caffPostService = caffPostService;
	}
	
	@Autowired
	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	@RequestMapping("/")
	public String index() {
		return "Hello hello sziasztok!";
	}
	
	@RequestMapping("/login")
    public boolean login(@RequestBody Customer user) {
        return
          user.getEmail().equals("user") && user.getPassword().equals("password");
    }
	
	@RequestMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new Customer());
		return "successful";
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
    public void greetingSubmit(@RequestBody Customer user) {
		userService.registerTheUser(user);
	}
	
	@GetMapping("users")
    public ResponseEntity<List<Customer>> allUser() {
		return new ResponseEntity<>(userService.findCustomer(),HttpStatus.OK);
				
	}
}
