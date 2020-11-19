package com.book.main;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
	
	@Autowired
	private BookRepository repo;
	
	@RequestMapping(value="/test/books", method=RequestMethod.GET)
	public ResponseEntity<Object> retrieveBook(){			
		List<Book> books = repo.findAll();		
		if(null!=books && books.size() == 0){
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok().body(books);				
	}
	
	@RequestMapping(value="/test/books", method=RequestMethod.POST)
	public ResponseEntity<Object> addBook(@RequestBody Book book){
		repo.save(book);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@RequestMapping(value="/test/books", method=RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBook(){
		repo.deleteAll();				
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@RequestMapping(value="/login", method=RequestMethod.DELETE)
	public String login(){
		return "";
	}

}