package com.webshop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.crypto.Data;

@Entity
public class CaffPost {

	@GeneratedValue
	@Id
	private long id;
	private String title;
	private String content;
	private Data posted;
	@ManyToOne
	private User user;
	
	private CaffPost() {
	}
	
	public CaffPost(long id, String title, String content, Data posted, User user) {
		super();
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

	public Data getPosted() {
		return posted;
	}

	public void setPosted(Data posted) {
		this.posted = posted;
	}

	public User getAuthor() {
		return user;
	}

	public void setAuthor(User user) {
		this.user = user;
	}
	
	
}
