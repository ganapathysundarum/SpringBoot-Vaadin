package com.book.main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import com.vaadin.annotations.Theme;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.BorderStyle;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * This UI is the application entry point. A UI may either represent a browser
 * window (or tab) or some part of a html page where a Vaadin application is
 * embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is
 * intended to be overridden to add component to the user interface and
 * initialize non-component functionality.
 */

public class MyUI extends UI {

	static RestTemplate restTemplate = new RestTemplate();

	@Autowired
	BookRepository bookRep;

	@Autowired
	UserRepository userRep;

	public void setBookRep(BookRepository bookRep) {
		this.bookRep = bookRep;
	}

	public void setUserRep(UserRepository userRep) {
		this.userRep = userRep;
	}

	public MyUI(BookRepository bookRep, UserRepository userRep) {
		this.bookRep = bookRep;
		this.userRep = userRep;
	}

	// Book Library Tab grid
	private Grid<Book> grid;
	// Users Tab grid
	private Grid<Users> grid1;
	// Google Books Tab grid
	private Grid<GBook> grid2;

	private VerticalLayout layout;
	// Book Library tab components
	private BookForm form;
	private TextField filterText = new TextField();
	private Button clearFilterTextBtn;
	private Button addBookBtn;
	private CssLayout filtering;
	private HorizontalLayout toolbar;
	private HorizontalLayout main;

	// Users tab components
	private UserForm uform;
	private TextField filterText1 = new TextField();
	private Button clearFilterTextBtn1;
	private Button addUserBtn1;
	private CssLayout filtering1;
	private HorizontalLayout toolbar1;
	private HorizontalLayout main1;

	// Google Books Tab components
	private TextField filterText2 = new TextField();
	private Button filterText2Btn2;
	private Button clearFilterTextBtn2;
	private Button addGBookBtn2;
	private CssLayout filtering2;
	private HorizontalLayout toolbar2;
	private HorizontalLayout main2;

	// thumbnail
	private Resource thumbnailRes;

	// Tab
	private TabSheet tabsheet;

	@Override
	public void init(VaadinRequest vaadinRequest) {

		grid = new Grid<Book>();
		grid1 = new Grid<Users>();
		grid2 = new Grid<GBook>();

		layout = new VerticalLayout();
		form = new BookForm();
		clearFilterTextBtn = new Button("Clear Filter");

		addBookBtn = new Button("Add Book");
		
		filtering = new CssLayout();
		toolbar = new HorizontalLayout();
		main =  new HorizontalLayout();

		// Users tab components
		uform = new UserForm(this,userRep);		
		clearFilterTextBtn1 = new Button("Clear Filter");
		addUserBtn1 = new Button("Add User");
		filtering1 = new CssLayout();
		toolbar1 = new HorizontalLayout();
		main1 = new HorizontalLayout();

		// Google Books Tab components		
		filterText2Btn2 = new Button("Filter Text");
		clearFilterTextBtn2 = new Button("Clear Filter Text");
		addGBookBtn2 = new Button("Add Book");
		filtering2 = new CssLayout();
		toolbar2 = new HorizontalLayout();
		main2 = new HorizontalLayout();

		// thumbnail
		//thumbnailRes = Resource.class

		// Tab
		tabsheet = new TabSheet();
	}

	public BookRepository getBookRep() {
		return bookRep;
	}

	public Grid<Book> getGrid() {
		return grid;
	}

	public TextField getFilterText() {
		return filterText;
	}

	public BookForm getForm() {
		return form;
	}

	public VerticalLayout getLayout() {
		return layout;
	}

	public Button getClearFilterTextBtn() {
		return clearFilterTextBtn;
	}

	public Button getAddBookBtn() {
		return addBookBtn;
	}

	public CssLayout getFiltering() {
		return filtering;
	}

	public HorizontalLayout getToolbar() {
		return toolbar;
	}

	public HorizontalLayout getMain() {
		return main;
	}

}