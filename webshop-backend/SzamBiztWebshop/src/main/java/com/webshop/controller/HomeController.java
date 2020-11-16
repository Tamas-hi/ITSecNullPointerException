package com.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.entity.TheUser;
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
	
	
	@RequestMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new TheUser());
		return "successful";
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
    public void greetingSubmit(@ModelAttribute TheUser user) {
		userService.registerTheUser(user);
	}
}
