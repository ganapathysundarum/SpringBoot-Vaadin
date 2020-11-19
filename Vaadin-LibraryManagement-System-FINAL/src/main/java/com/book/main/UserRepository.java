package com.book.main;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long>{

	public Users findByUsername(String username);
	
}
																																																																																																																																																															