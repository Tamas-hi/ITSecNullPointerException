package com.webshop.repository;

import com.webshop.model.CaffFile;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface CaffFileRepository extends CrudRepository<CaffFile, Long> {
    CaffFile getCaffFileByCaffPostId(long caffPostId);
}
