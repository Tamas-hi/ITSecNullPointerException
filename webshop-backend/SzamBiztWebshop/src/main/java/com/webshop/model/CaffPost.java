package com.webshop.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "caff_posts")
public class CaffPost {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String title;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] content;

    @Column
    private Date posted;

    @JsonBackReference
    @ManyToOne
    private User user;

    @Column
    public String creatorName;

    @Column
    public String caption;

    @Column
    public String[] tags;

    @OneToMany(mappedBy = "caffPost")
    private List<Comment> comments;

    public CaffPost() {
    }

    public CaffPost(long id, byte[] content, String title, Date posted, User user) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.posted = posted;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creator_name) {
        this.creatorName = creator_name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
