package com.webshop.service;

import java.util.List;

import javax.transaction.Transactional;

import com.webshop.model.CaffFile;
import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.CaffPost;
import com.webshop.repository.CaffPostRepository;

@Service
public class CaffPostService {

    private final CaffPostRepository caffPostRepository;
    private final CaffFileService caffFileService;

    public CaffPostService(CaffPostRepository caffPostRepository, CaffFileService caffFileService) {
        this.caffPostRepository = caffPostRepository;
        this.caffFileService = caffFileService;
    }

    public List<CaffPost> getPosts() {
        return caffPostRepository.findAll();
    }

    @Transactional
    public CaffPost findCaffById(long id) {
        return caffPostRepository.findCaffPostById(id);
    }

    @Transactional
    public void deleteCaffById(long id) throws NotFoundException {
    	CaffPost caffPost;
        if (caffPostRepository.findById(id).isPresent()) {
            caffPost = caffPostRepository.findById(id).get();
        } else {
            throw new NotFoundException("CaffPost is not found.");
        }

        CaffFile connectedCaffFile = this.caffFileService.getCaffFileByCaffPostId(caffPost.getId());
        this.caffFileService.deleteCaffFile(connectedCaffFile);

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
