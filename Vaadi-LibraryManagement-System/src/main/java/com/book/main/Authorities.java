package com.book.main;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class Authorities implements AuthenticationProvider {
	
	@Autowired
	private UserRepository repo;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		String name = authentication.getName();
		Users us = repo.findByUsername(name);
        //String password = authentication.getCredentials().toString();
		String password = us.getPassword();
        
        return new UsernamePasswordAuthenticationToken(
                name, password, new ArrayList<>());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}



}
