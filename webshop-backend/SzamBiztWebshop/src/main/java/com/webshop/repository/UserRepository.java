package com.webshop.repository;

import com.webshop.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	// We only need the first element because email is unique, so can not be more result.
	User findFirstByEmail(String email);
	void deleteUserById(long id);
	User getUserById(long id); 
}
