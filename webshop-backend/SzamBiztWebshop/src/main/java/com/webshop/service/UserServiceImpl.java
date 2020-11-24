package com.webshop.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webshop.model.Role;
import com.webshop.model.Customer;
import com.webshop.repository.CustomerRepository;
import com.webshop.repository.RoleRepository;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService{
	
	private RoleRepository roleRepository;
	
	@Autowired
	private CustomerRepository cmrepo;
	
	private final String USER_ROLE = "USER";
	
	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("User keresés");
		Customer user = findByEmail(username);
		if( user == null ){
			throw new UsernameNotFoundException(username);
		}
		
		return new UserDetailsImpl(user);
	}

	public Customer findByEmail(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerTheUser(Customer user) {
		Role userRole = roleRepository.findByRole(USER_ROLE);
		
		if(userRole != null) {
			user.getRoles().add(userRole);
		}else {
			user.addRoles(USER_ROLE);
		}
		
		cmrepo.save(user);
	}
	
	public List<Customer> findCustomer(){
		return (List<Customer>) cmrepo.findAll();
	}
	
	
}
