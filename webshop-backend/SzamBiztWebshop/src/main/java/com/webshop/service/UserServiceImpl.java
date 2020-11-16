package com.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.webshop.entity.Role;
import com.webshop.entity.TheUser;
import com.webshop.repository.RoleRepository;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService{
	
	private RoleRepository roleRepository;
	 
	private final String USER_ROLE = "USER";
	
	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("User keresés");
		TheUser user = findByEmail(username);
		if( user == null ){
			throw new UsernameNotFoundException(username);
		}
		
		return new UserDetailsImpl(user);
	}

	public TheUser findByEmail(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registerTheUser(TheUser user) {
		Role userRole = roleRepository.findByRole(USER_ROLE);
		
		if(userRole != null) {
			user.getRoles().add(userRole);
		}else {
			user.addRoles(USER_ROLE);
		}
		
	}
	
	
}
