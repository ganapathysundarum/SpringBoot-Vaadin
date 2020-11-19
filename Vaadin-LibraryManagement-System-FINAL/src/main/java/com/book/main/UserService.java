package com.book.main;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.book.main.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repo;	
	
	@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
		
	

	public UserService(UserRepository repo) {
		this.repo = repo;
	}

	public Users retrieveByUserName(String username) {
		return repo.findByUsername(username);
	}
		

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Users optionalUser = repo.findByUsername(username);
		System.out.println("username --> "+username);
	
        	List<String> roleList = new ArrayList<String>();
        	List<com.book.main.Role> roles = optionalUser.getRoles();
        	for(Role role:roles) {
        		roleList.add(role.getRole());
        	}
        	System.out.println("User <>--<> "+optionalUser.toString());
            return User.builder()
            	.username(optionalUser.getUsername())
            	//change here to store encoded password in db
            	//.password( bCryptPasswordEncoder.encode(optionalUser.getPassword()))
            	.password( "{noop}"+optionalUser.getPassword())
            	.disabled(!optionalUser.getEnabled())
            	.accountExpired(!optionalUser.getAccountNonExpired())
            	.accountLocked(!optionalUser.getAccountNonLocked())
            	.credentialsExpired(!optionalUser.getCredentialsNonExpired())
            	.roles(roleList.toArray(new String[0]))
            	.build();     
		        
	}	

}