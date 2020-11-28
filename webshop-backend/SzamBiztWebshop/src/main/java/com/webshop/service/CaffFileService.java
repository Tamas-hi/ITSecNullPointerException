package com.webshop.service;

import com.webshop.model.CaffFile;
import com.webshop.model.CaffPost;
import com.webshop.repository.CaffFileRepository;
import org.springframework.stereotype.Service;

@Service
public class CaffFileService {
    private final CaffFileRepository caffFileRepository;

    public CaffFileService(CaffFileRepository caffFileRepository) {
        this.caffFileRepository = caffFileRepository;
    }

    public void saveCaffFile(CaffPost caffPost, byte[] byteArray) {
        this.caffFileRepository.save(new CaffFile(byteArray, caffPost));
    }
}
