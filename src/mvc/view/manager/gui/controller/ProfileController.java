package mvc.view.manager.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import database.dao.impl.FacadeImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import mvc.view.manager.gui.util.Constants;
import mvc.view.manager.gui.util.GraphicHandler;

public class ProfileController implements Initializable {

	@FXML
	private Text txtUsername;
	
	private String username;
	private FacadeImpl facadeImpl;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		facadeImpl= FacadeImpl.getInstance();
	}
	
	public void setUsername(String username) {
		this.username=username;
		txtUsername.setText(this.username);
	}
	
	public void deleteManager(ActionEvent event) {
		try {
			facadeImpl.removeManager(username);
			Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Login.fxml",Constants.STYLE_LOGREG_PATH);
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			GraphicHandler.loadStage(scene, primaryStage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
}
