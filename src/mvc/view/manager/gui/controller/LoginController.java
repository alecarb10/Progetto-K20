package mvc.view.manager.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import database.dao.impl.ManagerDAOImpl;
import mvc.view.manager.gui.util.Constants;
import mvc.view.manager.gui.util.GraphicHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable{	
	
	@FXML
	private TextField txtFldUsername;
	@FXML
	private PasswordField pwdFldPassword;
	@FXML
	private Label lblSignUp;
	
	public void login(ActionEvent event){
		try {
			 
			boolean checkLogin=new ManagerDAOImpl().checkManagerLogin(this.txtFldUsername.getText(),this.pwdFldPassword.getText());		
			String message=checkLogin?"logged":"incorrect credentials";
			AlertType alertType=checkLogin?AlertType.CONFIRMATION:AlertType.ERROR;
			new Alert(alertType,message,ButtonType.OK).showAndWait();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
	
		 
	}
	
	public void signUp(MouseEvent event) throws IOException
	{	        
		Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Registration.fxml", new RegistrationController(),Constants.STYLE_LOGREG_PATH);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Manager");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	
}