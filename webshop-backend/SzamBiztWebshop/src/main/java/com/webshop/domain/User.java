package com.webshop.domain;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@GeneratedValue
	@Id
	private long id;
	private String name;
	private String email;
	@OneToMany(mappedBy = "user")
	private ArrayList<CaffPost> caffposts;
	
	private User() {
	}
	
	public User(long id, String name, String email, ArrayList<CaffPost> caffposts) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.caffposts = caffposts;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<CaffPost> getCaffposts() {
		return caffposts;
	}

	public void setCaffposts(ArrayList<CaffPost> caffposts) {
		this.caffposts = caffposts;
	}
	
	
}
