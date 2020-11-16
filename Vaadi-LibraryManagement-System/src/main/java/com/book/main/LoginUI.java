package com.book.main;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;


public class LoginUI extends UI {
	private TextField userName;

    private PasswordField passwordField;
    
    private Button login;

	
	private VerticalLayout layout;
	
	private UserRepository urep;
	
	public LoginUI(UserRepository urep){
		this.urep = urep;
	}
	
	@Override
	protected void init(VaadinRequest request) {
		userName = new TextField();
	    passwordField = new PasswordField();	    
	    login = new Button("Login");	
	    login.addClickListener(new ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				Users u = new Users();
				u.setUsername(userName.getValue());
				u.setPassword(passwordField.getValue());
				urep.save(u);
			}
		});
		layout = new VerticalLayout();
		layout.addComponents(userName,passwordField,login);
	}

	public TextField getUserName() {
		return userName;
	}

	public void setUserName(TextField userName) {
		this.userName = userName;
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(PasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public Button getLogin() {
		return login;
	}
}
