package com.webshop.repository;

import org.springframework.data.repository.CrudRepository;

import com.webshop.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByRole(String role);
	void deleteUserById(long id);
}
