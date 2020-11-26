package com.webshop.model;

import java.awt.image.BufferedImage;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "caff_posts")
public class CaffPost{

	@Id
	@GeneratedValue
	private long id;
	@Column
	private String title;
	
	@Lob
	@Column
	private byte[] content;
	@Column
	private Date posted;
	
	@JsonBackReference
	@ManyToOne
	private User user;
	
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

}
