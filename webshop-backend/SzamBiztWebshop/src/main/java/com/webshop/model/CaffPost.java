package com.webshop.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CaffPost{

	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String content;
	private Date posted;
	
	@ManyToOne
	private User user;
	
	public CaffPost() {
	}
	
	public CaffPost(long id, String title, String content, Date posted, User user) {
		this.id = id;
		this.title = title;
		this.content = content;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPosted() {
		return posted;
	}

	public void setPosted(Date posted) {
		this.posted = posted;
	}

	public User getAuthor() {
		return user;
	}

	public void setAuthor(User user) {
		this.user = user;
	}

	public User getThe_user() {
		return user;
	}

	public void setThe_user(User user) {
		this.user = user;
	}

}
