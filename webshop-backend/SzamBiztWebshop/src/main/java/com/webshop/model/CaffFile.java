package com.webshop.model;

import javax.persistence.*;

@Entity
public class CaffFile {

    @GeneratedValue
    @Id
    private long id;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] content;

    @OneToOne
    private CaffPost caffPost;

    public CaffFile() {
    }

    public CaffFile(byte[] content, CaffPost caffPost) {
        this.content = content;
        this.caffPost = caffPost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public CaffPost getCaffPost() {
        return caffPost;
    }

    public void setCaffPost(CaffPost caffPost) {
        this.caffPost = caffPost;
    }
}
