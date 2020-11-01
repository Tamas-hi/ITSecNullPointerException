package com.webshop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.webshop.domain.CaffPost;

public interface CaffPostRepository extends CrudRepository<CaffPost, Long> {
	
	List<CaffPost> findAll();
	
}
