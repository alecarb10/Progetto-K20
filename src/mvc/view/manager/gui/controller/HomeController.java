package mvc.view.manager.gui.controller;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

import database.dao.impl.FacadeImpl;
import mvc.model.tournament.Tournament;
import mvc.view.manager.gui.util.Constants;
import mvc.view.manager.gui.util.GraphicHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
				this.borderPaneHome.setCenter(GraphicHandler.getParent(Constants.PATH_PREFIX+"/resources/Profile.fxml"));
				this.cmbBoxProfile.getSelectionModel().clearSelection();
			}
		});
	}
	
	public void editTeam(ActionEvent event) {
		try {
			FXMLLoader loader= GraphicHandler.getLoader(Constants.PATH_PREFIX+"/resources/EditTeam.fxml");
			Parent root=loader.load();
			EditTeamController controller= loader.getController();
			controller.populateCmbBoxTournament(this.getTournamentsList(this.cmbBoxProfile.getPromptText()));
			controller.setUsername(this.cmbBoxProfile.getPromptText());
			this.borderPaneHome.setCenter(root);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void editPlayer(ActionEvent event) {
		try {
			FXMLLoader loader= GraphicHandler.getLoader(Constants.PATH_PREFIX+"/resources/EditPlayer.fxml");
			Parent root=loader.load();
			EditPlayerController controller= loader.getController();
			controller.populateCmbBoxTournament(this.getTournamentsList(this.cmbBoxProfile.getPromptText()));
			controller.setUsername(this.cmbBoxProfile.getPromptText());
			this.borderPaneHome.setCenter(root);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void insertResult(ActionEvent event) {
		try {
			FXMLLoader loader= GraphicHandler.getLoader(Constants.PATH_PREFIX+"/resources/InsertResult.fxml");
			Parent root=loader.load();
			InsertResultController controller= loader.getController();
			controller.populateCmbBoxTournament(this.getTournamentsList(this.cmbBoxProfile.getPromptText()));
			controller.setUsername(this.cmbBoxProfile.getPromptText());
			this.borderPaneHome.setCenter(root);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void createTournament(ActionEvent event) {
		try {
			FXMLLoader loader= GraphicHandler.getLoader(Constants.PATH_PREFIX+"/resources/CreateTournament.fxml");
			Parent root=loader.load();
			CreateTournamentController controller= loader.getController();
			controller.setUsername(this.cmbBoxProfile.getPromptText());
			this.borderPaneHome.setCenter(root);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setUsername(String username) {
		this.cmbBoxProfile.setPromptText(username);
	}
	
	private ObservableList<String> getTournamentsList(String username){
		ObservableList<String> tournaments= FXCollections.observableArrayList();
		Map<Integer,Tournament> tournamentsMap=null;
		try {
			tournamentsMap=new FacadeImpl().getAllTournamentsByManager(username);
			for(Tournament t: tournamentsMap.values()) 
				tournaments.add(t.getName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
		return tournaments;
	}
}
