package com.webshop.repository;


import org.springframework.data.repository.CrudRepository;

import com.webshop.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
}
