package com.webshop.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.CaffPost;
import com.webshop.repository.CaffPostRepository;

@Service
public class CaffPostServiceImpl {

    CaffPostRepository caffPostRepository;

    @Autowired
    public void setCaffPostRepository(CaffPostRepository caffPostRepository) {
        this.caffPostRepository = caffPostRepository;
    }

    public List<CaffPost> getPosts() {
        return caffPostRepository.findAll();
    }

    @Transactional
    public CaffPost findCaffById(long id) {
        return caffPostRepository.findCaffPostById(id);
    }

    @Transactional
    public void deleteCaffById(long id) {
        caffPostRepository.deleteCaffPostById(id);
    }

    public CaffPost uploadCaff(CaffPost caffPost) {
        return caffPostRepository.save(caffPost);
    }

    public void updateTitle(CaffPost caffPost) {
        caffPostRepository.save(caffPost);
    }

    public List<CaffPost> findCaffByTitle(String title) {
        return caffPostRepository.findByTitleContaining(title);
    }
}
