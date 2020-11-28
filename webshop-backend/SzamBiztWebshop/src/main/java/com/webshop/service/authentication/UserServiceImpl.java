package com.webshop.service.authentication;

import com.webshop.model.Role;
import com.webshop.model.User;
import com.webshop.repository.RoleRepository;
import com.webshop.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements IUserService {

    private final AuthenticationManager authenticationManager;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final HttpServletRequest httpServletRequest;

    private final HttpServletResponse httpServletResponse;

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    public UserServiceImpl(
            AuthenticationManager authenticationManager,
            BCryptPasswordEncoder bCryptPasswordEncoder,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            RoleRepository roleRepository,
            UserRepository userRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.httpServletRequest = httpServletRequest;
        this.httpServletResponse = httpServletResponse;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }


    public User findByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    @Override
    public ResponseEntity<UserDetails> login(User requestUser) {
        UsernamePasswordAuthenticationToken authenticationTokenRequest = new UsernamePasswordAuthenticationToken(requestUser.getEmail(), requestUser.getPassword());
        System.out.println(authenticationTokenRequest);
        try {
            Authentication authentication = this.authenticationManager.authenticate(authenticationTokenRequest);
            SecurityContext securityContext = SecurityContextHolder.getContext();
            securityContext.setAuthentication(authentication);

            UserDetails user = (UserDetails) authentication.getPrincipal();
            return new ResponseEntity<>(user, HttpStatus.OK);

        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Boolean> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(
                    httpServletRequest,
                    httpServletResponse,
                    authentication);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> register(User user) {
        String USER_ROLE = "USER";
        Role userRole = roleRepository.findByRole(USER_ROLE);

        if (userRole != null) {
            user.getRoles().add(userRole);
        } else {
            user.addRoles(USER_ROLE);
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        try {
            userRepository.save(user);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }
    
    public Set<Role> getUserRoleById(long id){
    	return userRepository.getUserById(id).getRoles();
    }
}
