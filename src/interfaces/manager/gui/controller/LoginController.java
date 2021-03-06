package interfaces.manager.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import interfaces.manager.gui.util.Constants;
import interfaces.manager.gui.util.GraphicHandler;
import services.persistence.dao.impl.FacadeImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Controller for manager's authentication to the home menu
 * @see Initializable
 */

public class LoginController implements Initializable {

	@FXML
	private TextField txtFldUsername;
	@FXML
	private PasswordField pwdFldPassword;
	@FXML
	private Label lblSignUp;

	public void login(ActionEvent event) {
		try {

			boolean checkLogin = FacadeImpl.getInstance().checkManagerLogin(this.txtFldUsername.getText(),this.pwdFldPassword.getText());
			if (checkLogin) {
				FXMLLoader loader=GraphicHandler.getLoader(Constants.PATH_PREFIX + "/resources/Home.fxml");
				Parent root=loader.load();
				HomeController controller=loader.getController();
				controller.setUsername(this.txtFldUsername.getText());
				Scene scene = GraphicHandler.getScene(root, Constants.STYLE_PATH);
				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				GraphicHandler.loadStage(scene, primaryStage);							
			} 
			else 
				new Alert(AlertType.ERROR,"Incorrect credentials.",ButtonType.OK).show(); 

		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
		}

	}

	public void signUp(MouseEvent event) throws IOException {
		Scene scene = GraphicHandler.getScene(Constants.PATH_PREFIX + "/resources/Registration.fxml", Constants.STYLE_LOGREG_PATH);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		GraphicHandler.loadStage(scene, primaryStage);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
