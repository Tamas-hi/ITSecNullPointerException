package com.webshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webshop.model.CaffPost;
import com.webshop.service.CaffPostService;

@Controller
@RequestMapping("/caffposts")
public class CaffPostController {

	CaffPostService caffpostservice;
	
	@Autowired
	public void setCaffpostservice(CaffPostService caffpostservice) {
		this.caffpostservice = caffpostservice;
	}

	@RequestMapping("/all")
	public List<CaffPost> getAllCaff(){
		return caffpostservice.getPosts();
	}
	
	@RequestMapping("/get/{id}")
	public CaffPost findCaffById(@PathVariable long id) {
		return caffpostservice.findCaffById(id);
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
