package com.webshop.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table( name = "users" )
public class Customer {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String email;
	
	private String password;
	
	private String name;
	
	@JsonBackReference
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinTable( 
		name = "users_roles", 
		joinColumns = {@JoinColumn(name="user_id")}, 
		inverseJoinColumns = {@JoinColumn(name="role_id")}  
	)
	private Set<Role> roles = new HashSet<Role>();
	
	@JsonBackReference
	@OneToMany(mappedBy = "customer")
	private List<CaffPost> caffposts;
	
	public Customer() {}
	
	public Customer(long id, String name, String email, List<CaffPost> caffposts) {
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

	public List<CaffPost> getCaffposts() {
		return caffposts;
	}

	public void setCaffposts(List<CaffPost> caffposts) {
		this.caffposts = caffposts;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public void addRoles(String roleName) {
		if(this.roles == null || this.roles.isEmpty())
			this.roles = new HashSet<>();
		
		this.roles.add(new Role(roleName));
	}

	@Override
	public String toString() {
		return "TheUser [id=" + id + ", email=" + email + ", password=" + password + ", name=" + name + ", roles="
				+ roles + ", caffposts=" + caffposts + "]";
	}

}
