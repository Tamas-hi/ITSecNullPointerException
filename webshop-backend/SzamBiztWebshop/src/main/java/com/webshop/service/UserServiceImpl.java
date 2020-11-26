package com.webshop.service;

import com.webshop.model.Role;
import com.webshop.model.User;
import com.webshop.repository.RoleRepository;
import com.webshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
	
	private RoleRepository roleRepository;

	private UserRepository userRepository;

	private final String USER_ROLE = "USER";
	
	@Autowired
	public void setRoleRepository(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserDetailsImpl(user);
	}

	public User findByEmail(String email) {
		return userRepository.findFirstByEmail(email);
	}

	@Override
	public void registerTheUser(User user) {
		Role userRole = roleRepository.findByRole(USER_ROLE);
		
		if (userRole != null) {
			user.getRoles().add(userRole);
		} else {
			user.addRoles(USER_ROLE);
		}

		userRepository.save(user);
	}
	
	public List<User> findUser() {
		return (List<User>) userRepository.findAll();
	}

	public void deleteUserById(long id) {
		roleRepository.deleteUserById(id);
		userRepository.deleteUserById(id);
		
	}

	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}
}
