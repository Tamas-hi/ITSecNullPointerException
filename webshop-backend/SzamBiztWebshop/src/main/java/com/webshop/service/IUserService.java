package com.webshop.service;

import com.webshop.entity.TheUser;

public interface IUserService {
	
	public void registerTheUser(TheUser user);
	
	public TheUser findByEmail(String email);
	
}
