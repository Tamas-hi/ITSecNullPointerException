package com.webshop.service;

import java.util.List;

import com.webshop.model.Customer;

public interface IUserService {
	
	public void registerTheUser(Customer user);
	
	public Customer findByEmail(String email);
	
}
