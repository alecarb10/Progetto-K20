package it.unipv.ingsw.k20.view.gui.manager.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {	
	
	@FXML
	private TextField txtFldUsername;
	@FXML
	private PasswordField pwdFldPassword;
	
	public LoginController() {
		this.txtFldUsername=new TextField();
		this.pwdFldPassword=new PasswordField();
	}
	
	public void login(ActionEvent event) {
		/*
		 * if(txtFldUsername.getText().equals("")&&this.pwdFldPassword.getText().equals("")) {
			
		}
		*/
	}
	
	
	
}
