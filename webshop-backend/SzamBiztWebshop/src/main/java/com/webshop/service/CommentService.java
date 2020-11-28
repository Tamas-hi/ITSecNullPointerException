package com.webshop.service;

import com.webshop.model.CaffPost;
import com.webshop.model.Comment;
import com.webshop.model.User;
import com.webshop.repository.CaffPostRepository;
import com.webshop.repository.CommentRepository;
import com.webshop.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CaffPostRepository caffPostRepository;
    private final UserRepository userRepository;

    public CommentService(
            CommentRepository commentRepository,
            CaffPostRepository caffPostRepository,
            UserRepository userRepository
    ) {
        this.commentRepository = commentRepository;
        this.caffPostRepository = caffPostRepository;
        this.userRepository = userRepository;
    }

    public List<Comment> getCommentByCaffPostId(long caffPostId) {
        return this.commentRepository.findAllByCaffPostId(caffPostId);
    }

    public void addNewComment(long caffPostId, long userId, String commentText) throws NotFoundException {
        CaffPost caffPost;
        if (this.caffPostRepository.findById(caffPostId).isPresent()) {
            caffPost = this.caffPostRepository.findById(caffPostId).get();
        } else {
            throw new NotFoundException("CaffPost is not found.");
        }

        User user;

        if (this.userRepository.findById(userId).isPresent()) {
            user = this.userRepository.findById(userId).get();
        } else {
            throw new NotFoundException("CaffPost is not found.");
        }

        Comment comment = new Comment(commentText, new Date(), user, caffPost);

        this.commentRepository.save(comment);
    }
}
