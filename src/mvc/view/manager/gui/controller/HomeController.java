package mvc.view.manager.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.jdi.event.Event;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


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
			FXMLLoader loader= GraphicHandler.getLoader(Constants.PATH_PREFIX+"/resources/EditTeam.fxml");
			Parent root=loader.load();
			EditTeamController controller= loader.getController();
			this.borderPaneHome.setCenter(root);
			controller.setUsername(username);
			controller.populateCmbBoxTournament(this.getTournamentsList(username));	
			controller.setTournamentsList(tournamentsList);
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void addStadium(ActionEvent event) {
		try {
			this.borderPaneHome.setCenter(GraphicHandler.getParent(Constants.PATH_PREFIX+"/resources/AddStadium.fxml"));
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
			controller.populateCmbBoxTournament(this.getTournamentsList(username));
			controller.setUsername(username);
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
			controller.setUsername(username);
			this.borderPaneHome.setCenter(root);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void setUsername(String username) {
		
		try {
			this.username=username;
			this.cmbBoxProfile.setPromptText(username);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private ObservableList<String> getTournamentsList(String username){
		ObservableList<String> tournaments= FXCollections.observableArrayList();	
		try {
			this.tournamentsList=new FacadeImpl().getAllTournamentsByManager(username);
			for(Tournament t: tournamentsList) {
				tournaments.add(t.getName());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		else if(this.cmbBoxProfile.getSelectionModel().getSelectedIndex()==1) {
			Scene scene=GraphicHandler.getScene(Constants.PATH_PREFIX+"/resources/Login.fxml",Constants.STYLE_LOGREG_PATH);
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			GraphicHandler.loadStage(scene, primaryStage);
		}
	}
	
}
