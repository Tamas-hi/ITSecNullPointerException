package com.webshop.service;

import com.webshop.model.User;

public interface IUserService {
	
	void registerTheUser(User user);
	
	User findByEmail(String email);
}
