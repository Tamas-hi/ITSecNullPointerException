package com.webshop.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter{

	@Bean
	public UserDetailsService userDetailsService() {
	    return super.userDetailsService();
	}
	
	@Autowired
	private UserDetailsService userService;
	
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService);
		auth
			.inMemoryAuthentication()
				.withUser("root")
				.password("{noop}admin")
				.roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity httpSec) throws Exception {
		httpSec
			.authorizeRequests()
				.antMatchers(HttpMethod.GET,"/").permitAll()
				.antMatchers(HttpMethod.GET,"/login").permitAll()
				.antMatchers(HttpMethod.GET,"/registration").permitAll()
				.antMatchers(HttpMethod.POST,"/reg").permitAll()
				.antMatchers("/caffposts").hasRole("USER")
				.antMatchers("/delete").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
				.csrf().disable()
				.formLogin().permitAll();
				
						
	}
	
}
