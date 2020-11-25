package com.webshop.controller;

import com.webshop.model.CaffPost;
import com.webshop.service.CaffPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/caffposts")
public class CaffPostController {

	CaffPostService caffPostService;
	
	@Autowired
	public void setCaffPostService(CaffPostService caffPostService) {
		this.caffPostService = caffPostService;
	}

	@RequestMapping("/all")
	public List<CaffPost> getAllCaff(){
		return caffPostService.getPosts();
	}
	
	@RequestMapping("/get/{id}")
	public CaffPost findCaffById(@PathVariable long id) {
		return caffPostService.findCaffById(id);
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteCaffById(@PathVariable long id) {
		return "deleteee";
	}
	
	@RequestMapping("/upload/{id}")
	public String uploadCaffById(@PathVariable long id) {
		return "Deletet successfull";
	}
}
