package com.webshop.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @GeneratedValue
    @Id
    private long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String name;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable(
            name = "user_role",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    protected Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private List<CaffPost> caffPosts;

    public void addRoles(String roleName) {
        if(this.roles == null || this.roles.isEmpty())
            this.roles = new HashSet<>();

        this.roles.add(new Role(roleName));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<CaffPost> getCaffPosts() {
        return caffPosts;
    }

    public void setCaffPosts(List<CaffPost> caffPosts) {
        this.caffPosts = caffPosts;
    }
}
