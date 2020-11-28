package com.webshop.repository;

import com.webshop.model.CaffPost;
import com.webshop.model.Comment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
    List<Comment> findAllByCaffPostId(long caffPostId);
}
