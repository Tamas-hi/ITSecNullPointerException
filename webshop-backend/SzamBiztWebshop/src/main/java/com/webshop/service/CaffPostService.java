package com.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.CaffPost;
import com.webshop.repository.CaffPostRepository;

@Service
public class CaffPostService {
	
	CaffPostRepository caffRepo;

	@Autowired
	public void setCaffRepo(CaffPostRepository caffRepo) {
		this.caffRepo = caffRepo;
	}
	
	public List<CaffPost> getPosts(){
		return caffRepo.findAll();
	}
	
	public CaffPost findCaffById(long id){
		return caffRepo.findCaffPostById(id);
	}
}
