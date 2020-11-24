package com.webshop.entity;

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

@Entity
@Table( name = "users" )
public class TheUser {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(unique=true, nullable=false)
	private String email;
	
	@Column( nullable=false )
	private String password;
	
	private String name;
	
	@ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	@JoinTable( 
		name = "users_roles", 
		joinColumns = {@JoinColumn(name="user_id")}, 
		inverseJoinColumns = {@JoinColumn(name="role_id")}  
	)
	private Set<Role> roles = new HashSet<Role>();
	
	@OneToMany(mappedBy = "the_user")
	private List<CaffPost> caffposts;
	
	public TheUser() {}
	
	public TheUser(long id, String name, String email, List<CaffPost> caffposts) {
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
