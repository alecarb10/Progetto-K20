package interfaces.manager.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import interfaces.manager.gui.util.Constants;
import interfaces.manager.gui.util.GraphicHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.persistence.dao.impl.FacadeImpl;

/**
 * Controller for edit the informations of manager
 * @see Initializable
 */

public class ProfileController implements Initializable {

	@FXML
	private Text txtUsername;
	@FXML
	private TextField txtFldName,txtFldSurname;
	
	private String username;
	private FacadeImpl facadeImpl;
	private List<String> managerInfoList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		facadeImpl= FacadeImpl.getInstance();
	}
	
	public void setUsername(String username) {
		this.username=username;
		txtUsername.setText("Username: " +this.username);
		setManagerInfo();
	}
	
	public void deleteManager(ActionEvent event) {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to permanently remove this manager account?", ButtonType.YES, ButtonType.CANCEL);
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				facadeImpl.removeManager(username);
				Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Login.fxml",Constants.STYLE_LOGREG_PATH);
				Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
				GraphicHandler.loadStage(scene, primaryStage);
			}			
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
		}
		
	}
	
	public void saveManagerInfo(ActionEvent event) {
		try {
			if(isNotBlankControl()) {
				if(facadeImpl.updateManager(username, txtFldName.getText(), txtFldSurname.getText())) {
					Alert alert=new Alert(AlertType.INFORMATION,"Manager informations successfully updated.",ButtonType.OK);
					alert.showAndWait();
					if(alert.getResult()==ButtonType.OK) 
						setManagerInfo();
				}
			}
			else 
				new Alert(AlertType.ERROR, "Fields cannot be empty.", ButtonType.OK).show();
		}catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
		}
	}
	
	private void setManagerInfo() {
		try {
			managerInfoList=facadeImpl.getManagerByUsername(username);
			txtFldName.setText(managerInfoList.get(0));
			txtFldSurname.setText(managerInfoList.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private boolean isNotBlankControl() {
		return !this.txtFldName.getText().isBlank() && !this.txtFldSurname.getText().isBlank();
	}
}
