package com.webshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.CaffPost;
import com.webshop.repository.CaffPostRepository;

@Service
public class CaffPostService {
	
	CaffPostRepository caffPostRepository;

	@Autowired
	public void setCaffPostRepository(CaffPostRepository caffPostRepository) {
		this.caffPostRepository = caffPostRepository;
	}
	
	public List<CaffPost> getPosts(){
		return caffPostRepository.findAll();
	}
	
	public CaffPost findCaffById(long id){
		return caffPostRepository.findCaffPostById(id);
	}
}
