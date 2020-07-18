package interfaces.manager.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import domain.team.Team;
import domain.tournament.Tournament;
import interfaces.manager.gui.util.Constants;
import interfaces.manager.gui.util.GraphicControlsHandler;
import interfaces.manager.gui.util.GraphicHandler;
import services.persistence.dao.impl.FacadeImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Controller for handle the home menu of the tournament's manager
 * @see Initializable
 * @see Tournament
 * @see Team
 */

public class HomeController implements Initializable {
	@FXML
	private BorderPane borderPaneHome;
	@FXML
	private ComboBox<String> cmbBoxProfile;
	
	private String username;
	private List<Tournament> tournamentsList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.cmbBoxProfile.setItems(FXCollections.observableArrayList("Edit","Logout"));
	}
	
	public void editTeam(ActionEvent event) {
		try {
			GraphicControlsHandler.resetComboBox(cmbBoxProfile, username);
			FXMLLoader loader= GraphicHandler.getLoader(Constants.PATH_PREFIX+"/resources/EditTeam.fxml");
			Parent root=loader.load();
			EditTeamController controller= loader.getController();
			controller.setUsername(username);
			controller.populateCmbBoxTournament(this.getTournamentsList(username));	
			controller.setTournamentsList(tournamentsList);
			this.borderPaneHome.setCenter(root);		
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
		}
	}
	
	public void addStadium(ActionEvent event) {
		try {
			GraphicControlsHandler.resetComboBox(cmbBoxProfile, username);
			this.borderPaneHome.setCenter(GraphicHandler.getParent(Constants.PATH_PREFIX+"/resources/AddStadium.fxml"));
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
		}
	}
	
	public void insertResult(ActionEvent event) {
		try {
			GraphicControlsHandler.resetComboBox(cmbBoxProfile, username);
			FXMLLoader loader= GraphicHandler.getLoader(Constants.PATH_PREFIX+"/resources/InsertResult.fxml");
			Parent root=loader.load();
			InsertResultController controller= loader.getController();
			controller.setUsername(username);
			controller.populateCmbBoxTournament(this.getTournamentsList(username));	
			controller.setTournamentsList(tournamentsList);
			this.borderPaneHome.setCenter(root);
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
		}
	}
	
	public void createTournament(ActionEvent event) {
		try {
			GraphicControlsHandler.resetComboBox(cmbBoxProfile, username);
			FXMLLoader loader= GraphicHandler.getLoader(Constants.PATH_PREFIX+"/resources/CreateTournament.fxml");
			Parent root=loader.load();
			CreateTournamentController controller= loader.getController();
			controller.setUsername(username);
			this.borderPaneHome.setCenter(root);
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
		}
	}
	
	public void setUsername(String username) {	
		try {
			this.username=username;
			this.cmbBoxProfile.setPromptText(username);
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
		}
	}
	
	private ObservableList<String> getTournamentsList(String username){
		ObservableList<String> tournaments= FXCollections.observableArrayList();	
		try {
			this.tournamentsList=FacadeImpl.getInstance().getAllTournamentsByManager(username);
			for(Tournament t: tournamentsList) {
				tournaments.add(t.getName());
			}
		} catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
		}		
		return tournaments;
	}
	
	public void profile(ActionEvent event) {
		if(this.cmbBoxProfile.getSelectionModel().getSelectedIndex()==0) {
			try {
				FXMLLoader loader= GraphicHandler.getLoader(Constants.PATH_PREFIX+"/resources/Profile.fxml");
				Parent root=loader.load();
				ProfileController controller= loader.getController();
				controller.setUsername(username);
				this.borderPaneHome.setCenter(root);
			} catch (Exception e) {
				new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
			}
		}
		else if(this.cmbBoxProfile.getSelectionModel().getSelectedIndex()==1) {
			Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Login.fxml",Constants.STYLE_LOGREG_PATH);
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			GraphicHandler.loadStage(scene, primaryStage);
		}
	}
	
}
