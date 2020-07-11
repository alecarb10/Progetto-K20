package interfaces.manager.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfaces.manager.gui.util.Constants;
import interfaces.manager.gui.util.GraphicHandler;
import services.persistence.dao.impl.FacadeImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RegistrationController implements Initializable {

	@FXML
	private TextField txtFldUsername,txtFldName,txtFldSurname;	
	@FXML
	private PasswordField pwdFldPassword,pwdFldRepeatPassword;
	
	public void registration(ActionEvent event) throws IOException {
		FacadeImpl facadeImpl= FacadeImpl.getInstance();
		try {
			if(this.pwdMatching()&&this.isNotBlankControl()) {
				if(facadeImpl.checkUnique(this.txtFldUsername.getText())) {
					facadeImpl.storeManager(this.txtFldUsername.getText(), this.txtFldName.getText(), this.txtFldSurname.getText(),this.pwdFldPassword.getText());
					Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Login.fxml",Constants.STYLE_LOGREG_PATH);
					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					GraphicHandler.loadStage(scene, primaryStage);
				}
				else createAlert("Username already exists.");
			}
			else createAlert("Fields cannot be empty.\nPassword and confirm password must match.");
		} catch (Exception e) {
			Logger.getGlobal().log(Level.SEVERE,e.getMessage());
		}
	
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

	private boolean pwdMatching() {
		return this.pwdFldPassword.getText().equals(this.pwdFldRepeatPassword.getText());
	}
	
	private boolean isNotBlankControl() {
		return !this.txtFldName.getText().isBlank() && !this.txtFldSurname.getText().isBlank() && !this.txtFldUsername.getText().isBlank()&& !this.pwdFldPassword.getText().isBlank()&& !this.pwdFldRepeatPassword.getText().isBlank();
	}
	
	private void createAlert(String message) {
		new Alert(AlertType.ERROR,message,ButtonType.OK).show();
	}
	
	public void signIn(MouseEvent event)
	{	        
		Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Login.fxml",Constants.STYLE_LOGREG_PATH);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		GraphicHandler.loadStage(scene, primaryStage);
	}
	
	
}
