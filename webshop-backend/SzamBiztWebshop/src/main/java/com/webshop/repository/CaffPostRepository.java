package com.webshop.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.webshop.model.CaffPost;

public interface CaffPostRepository extends CrudRepository<CaffPost, Long> {
	List<CaffPost> findAll();
	CaffPost findCaffPostById(long id);
	void deleteCaffPostById(long id);
	List<CaffPost> findByTitleContaining(String title);
}
