package mvc.view.manager.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsw.k20.view.gui.manager.util.Constants;
import it.unipv.ingsw.k20.view.gui.manager.util.GraphicHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationController implements Initializable {

	@FXML
	private TextField txtFldUsername;	
	@FXML
	private PasswordField pwdFldPassword,pwdFldRepeatPassword;
	
	public void registration(ActionEvent event) throws IOException {
		Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Login.fxml", new LoginController(),Constants.STYLE_LOGREG_PATH);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Manager");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
//	private boolean pwdMatching() {
//		return this.pwdFldPassword.getText().equals(this.pwdFldRepeatPassword.getText());
//	}
	
}
