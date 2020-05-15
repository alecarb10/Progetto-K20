package mvc.view.manager.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class EditTeamController implements Initializable {

	
	@FXML
	private ComboBox<String> cmbBoxTournament;
	
	private String managerUsername;
	private ObservableList<String> tournaments = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void setUsername(String username) {		
		this.managerUsername=username;
	}
	
	public void populateCmbBoxTournament(ObservableList<String> tournaments) {
		this.tournaments=tournaments;
		this.cmbBoxTournament.setItems(tournaments);
	}
	
}
