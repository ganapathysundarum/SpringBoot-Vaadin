package com.book.main;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;

@Entity
public class Book  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	public String title;
	public Date publicationDate;
	public String description;
	public String authorName;
	public double price;
	
	public Book(){
		
	}
	
	public Book(String bookname,String description,Date publicationDate,double price,String authorName){
		this.title = bookname;
		this.publicationDate = publicationDate;
		this.description = description;
		this.authorName = authorName;		
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + id + ", bookname=" + title + ", publicationDate=" + publicationDate + ", description="
				+ description + ", authorName=" + authorName + ", price=" + price + "]";
	}
	
	
	
	
}
