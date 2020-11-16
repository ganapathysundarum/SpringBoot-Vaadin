package com.book.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repo;	
	
	@Autowired
	//private Converter<Users, UserDetails> userUserDetailsConverter;
	private UserToUserDetails userUserDetailsConverter;

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	public Users retrieveByUserName(String username) {
		return repo.findByUsername(username);
	}
	
	/* @Autowired
	    @Qualifier(value = "userToUserDetails")
	    public void setUserUserDetailsConverter(Converter<Users, UserDetails> userUserDetailsConverter) {
	        this.userUserDetailsConverter = userUserDetailsConverter;
	    }*/

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		/*
		 * List<GrantedAuthority> authorities = new
		 * ArrayList<GrantedAuthority>(); if(username==null ||
		 * username.trim().equals("")) { return(new User("ANONYMOUS", "", true,
		 * true, true, true, authorities)); } return User.withUsername(username)
		 * .build();
		 */
		/*Users u = retrieveByUserName(username);		
		return new User(u.getUsername(), u.getPassword(),true, true, true, true, getGrantedAuthorities(u));*/
		System.out.println("username <--> "+username);
		System.out.println("repo <--> "+repo);
		Objects.requireNonNull(repo,"Report cannot be null");
		Objects.requireNonNull(userUserDetailsConverter,"userUserDetailsConverter cannot be null");
		return userUserDetailsConverter.convert(repo.findByUsername(username));
		        
	}
	
	/*private Collection<GrantedAuthority> getGrantedAuthorities(Users user){
		Collection<GrantedAuthority> grantedAuthorities =  new ArrayList<>();
		if(user.getRole().equalsIgnoreCase("admin")){
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return grantedAuthorities;
	}*/

}