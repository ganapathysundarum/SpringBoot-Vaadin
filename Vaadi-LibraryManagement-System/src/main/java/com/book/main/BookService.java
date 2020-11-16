package com.book.main;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	private BookRepository repo;
	
	public List<Book> retrieveBooksByTitle(String title){
		return repo.findByTitle(title);
	}

}
