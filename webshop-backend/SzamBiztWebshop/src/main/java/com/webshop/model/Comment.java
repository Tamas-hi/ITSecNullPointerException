package com.webshop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String text;

    @Column
    private Date createdAt;

    @ManyToOne
    private User user;

    @JsonBackReference
    @ManyToOne
    private CaffPost caffPost;

    public Comment(String text, Date createdAt, User user, CaffPost caffPost) {
        this.text = text;
        this.createdAt = createdAt;
        this.user = user;
        this.caffPost = caffPost;
    }

    public Comment() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CaffPost getCaffPost() {
        return caffPost;
    }

    public void setCaffPost(CaffPost caffPost) {
        this.caffPost = caffPost;
    }
}
