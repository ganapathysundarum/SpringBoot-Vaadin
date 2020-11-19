package com.book.main;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.data.Result;
import com.vaadin.data.converter.LocalDateToDateConverter;
import com.vaadin.data.converter.StringToBigDecimalConverter;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.PopupView;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

public class BookForm extends FormLayout {

	private TextField title;

	private TextField description;
	private TextField price;
	private DateField publicationDate;
	private TextField authorName;
	private Button save;
	private Button delete;

	private Book book;
	private MyUI myUI;
	private Binder<Book> binder = new Binder<>();
	
	private BookRepository rep;

	public BookForm(MyUI myUI, BookRepository rep) {
		this.myUI = myUI;
		this.rep = rep;		
		this.init();
	}

	public BookForm() {
		
	}

	private void init(){
		title = new TextField();

		description = new TextField();
		price  = new TextField();
		publicationDate  = new DateField();
		authorName  = new TextField();
		save = new Button("Save");
		save.addClickListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub				
				System.out.println("--> repo -- "+rep);
				rep.save(book);
				System.out.println("--> BookForm - Save Button Clicked");
				System.out.println("--> BookForm - Save - title : "+book.getTitle());
			}
		});
		
		delete = new Button("Delete");
		delete.addClickListener(new ClickListener() {			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub				
				rep.delete(book);
				System.out.println("--> BookForm - Delete Button Clicked");
			}
		});
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public TextField getTitle() {
		return title;
	}

	public TextField getDesc() {
		return description;
	}

	public TextField getPrice() {
		return price;
	}

	public DateField getPublicationDate() {
		return publicationDate;
	}

	public TextField getAuthorName() {
		return authorName;
	}

	public Button getSave() {
		return save;
	}

	public Button getDelete() {
		return delete;
	}

	public Book getBook() {
		return book;
	}

	public MyUI getMyUI() {
		return myUI;
	}

	public Binder<Book> getBinder() {
		return binder;
	}

}
