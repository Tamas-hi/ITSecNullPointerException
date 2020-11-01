package com.webshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.repository.CaffPostRepository;
import com.webshop.service.CaffPostService;

@RestController
public class HomeController {
	
	CaffPostService caffPostService;
	
	@RequestMapping("/")
	public String init() {
		return "Hello hello sziasztok!";
	}
	
	@Autowired
	public void setCaffPostService(CaffPostService caffPostService) {
		this.caffPostService = caffPostService;
	}
	
	
}
