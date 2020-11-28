package com.webshop.controller;

import com.webshop.model.Comment;
import com.webshop.service.CommentService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("/comments/{caffPostId}")
    public ResponseEntity<List<Comment>> getCommentsByCaffPostId(@PathVariable long caffPostId) {
        return new ResponseEntity<>(commentService.getCommentByCaffPostId(caffPostId), HttpStatus.OK);
    }

    @RequestMapping(value = "/comments/{caffPostId}/{userId}", method = RequestMethod.POST)
    public ResponseEntity<List<Comment>> getCommentsByCaffPostId(
            @PathVariable long caffPostId,
            @PathVariable long userId,
            @RequestBody String commentText
    ) throws NotFoundException {
        commentService.addNewComment(caffPostId, userId, commentText);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
