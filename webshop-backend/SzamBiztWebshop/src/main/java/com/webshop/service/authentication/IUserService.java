package com.webshop.service.authentication;

import com.webshop.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService {
	User findByEmail(String email);
	ResponseEntity<UserDetails> login(User requestUser);
	ResponseEntity<Boolean> logout();
	ResponseEntity<Boolean> register(User form);
}
