package it.unipv.ingsw.k20.view.gui.manager.controller;

import java.io.IOException;

import it.unipv.ingsw.k20.view.gui.manager.util.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController {

	@FXML
	private TextField txtFldUsername;
	
	@FXML
	private PasswordField pwdFldPassword,pwdFldRepeatPassword;
	
	public RegistrationController(){
		this.txtFldUsername=new TextField();
		this.pwdFldPassword=new PasswordField();
		this.pwdFldRepeatPassword=new PasswordField();
	}
	
	public void registration(ActionEvent event) throws IOException {
		FXMLLoader loader=new FXMLLoader(getClass().getResource(Constants.PATH_PREFIX+"/resources/Login.fxml"));
		loader.setController(new LoginController());
		Scene scene= new Scene(loader.load());
		scene.getStylesheets().add(getClass().getResource(Constants.STYLE_PATH).toExternalForm());
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Manager");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}
	
//	private boolean pwdMatching() {
//		return this.pwdFldPassword.getText().equals(this.pwdFldRepeatPassword.getText());
//	}
	
}
