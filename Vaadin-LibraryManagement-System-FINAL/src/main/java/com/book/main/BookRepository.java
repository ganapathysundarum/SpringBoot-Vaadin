package com.book.main;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
	public List<Book> findByTitle(String title);
}
																																																																																																																																																															