package com.book.main;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.Binder;
import com.vaadin.data.Binder.Binding;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;


public class UserForm extends FormLayout {



	private TextField email;
	
	private TextField username;
	private PasswordField password ;
	private ComboBox<String> role ;
	
	private Button save ;
	private Button delete;

	private Users users;
	private MyUI myUI;
	private Binder<Users> binder = new Binder<>();

	public UserForm(MyUI myUI,
			UserRepository userRep) {
		
	}

	public void setUsers(Users users) {
		
	}



	public TextField getEmail() {
		return email;
	}

	public void setEmail(TextField email) {
		this.email = email;
	}

	public TextField getUsername() {
		return username;
	}

	public void setUsername(TextField username) {
		this.username = username;
	}

	public PasswordField getPassword() {
		return password;
	}

	public void setPassword(PasswordField password) {
		this.password = password;
	}


	public Button getSave() {
		return save;
	}

	public void setSave(Button save) {
		this.save = save;
	}

	public Button getDelete() {
		return delete;
	}

	public void setDelete(Button delete) {
		this.delete = delete;
	}

	public MyUI getMyUI() {
		return myUI;
	}

	public void setMyUI(MyUI myUI) {
		this.myUI = myUI;
	}

	public Binder<Users> getBinder() {
		return binder;
	}

	public void setBinder(Binder<Users> binder) {
		this.binder = binder;
	}

	public Users getUsers() {
		return users;
	}

	public ComboBox<String> getRole() {
		return role;
	}

	public void setRole(ComboBox<String> role) {
		this.role = role;
	}
	

	


}
