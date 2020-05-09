package mvc.view.manager.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import it.unipv.ingsw.k20.view.gui.manager.util.Constants;
import it.unipv.ingsw.k20.view.gui.manager.util.GraphicHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

public class HomeController implements Initializable {
	@FXML
	private BorderPane borderPaneHome;
	@FXML
	private ComboBox<String> cmbBoxProfile;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.cmbBoxProfile.setItems(FXCollections.observableArrayList("Edit","Logout"));
		this.cmbBoxProfile.setOnAction((ActionEvent)->{
			if(this.cmbBoxProfile.getSelectionModel().getSelectedItem().equals("Edit")) {
				this.borderPaneHome.setCenter(GraphicHandler.getParent(Constants.PATH_PREFIX+"/resources/Profile.fxml", new ProfileController()));
				
			}
		});
	}
	
	public void editTeam(ActionEvent event) {
		this.borderPaneHome.setCenter(GraphicHandler.getParent(Constants.PATH_PREFIX+"/resources/EditTeam.fxml", new EditTeamController()));
	}
	
	public void editPlayer(ActionEvent event) {
		this.borderPaneHome.setCenter(GraphicHandler.getParent(Constants.PATH_PREFIX+"/resources/EditPlayer.fxml", new EditPlayerController()));
	}
	
	public void insertResult(ActionEvent event) {
		this.borderPaneHome.setCenter(GraphicHandler.getParent(Constants.PATH_PREFIX+"/resources/InsertResult.fxml", new InsertResultController()));
	}
	
	public void createTournament(ActionEvent event) {
		this.borderPaneHome.setCenter(GraphicHandler.getParent(Constants.PATH_PREFIX+"/resources/CreateTournament.fxml", new CreateTournamentController()));
	}

}
