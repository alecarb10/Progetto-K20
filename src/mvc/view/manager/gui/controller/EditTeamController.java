package mvc.view.manager.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.ResourceBundle;

import database.dao.impl.FacadeImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mvc.model.team.Team;
import mvc.model.tournament.Tournament;

public class EditTeamController implements Initializable {

	
	@FXML
	private ComboBox<String> cmbBoxTournament,cmbBoxTeam;
	
	@FXML
	private TextField txtFldTeamName,txtFldStadiumName,txtFldStadiumCity,txtFldStadiumCapacity;
	
	private String username;
	private ObservableList<String> tournaments;	
	private FacadeImpl facadeImpl;
	private List<Tournament> tournamentsList;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tournaments= FXCollections.observableArrayList();
		facadeImpl= new FacadeImpl();
		this.cmbBoxTournament.setOnAction((ActionEvent)->{
			this.cmbBoxTeam.setItems(this.getTeamsList());
		});

	}
	
	public void setUsername(String username) {		
		this.username=username;
	}
	
	public void setTournamentsMap(List<Tournament> tournamentsList) {
		this.tournamentsList=tournamentsList;
	}
	
	public void populateCmbBoxTournament(ObservableList<String> tournaments) {
		this.tournaments=tournaments;
		this.cmbBoxTournament.setItems(tournaments);
	}
	
	private ObservableList<String> getTeamsList(){
		ObservableList<String> teams=FXCollections.observableArrayList();
		try {
			List<Team> teamsList=facadeImpl.getTeamsByTournament(getTournament(cmbBoxTournament.getSelectionModel().getSelectedItem()));
			for(Team t: teamsList) 
				teams.add(t.getName());
		} catch (Exception ex) {
			ex.printStackTrace();			
		}
		return teams;
	}
	
	private Tournament getTournament(String name) {
		for(Tournament tournament:tournamentsList) {
			if(tournament.getName().equals(name))
				return tournament;
		}
		return null;
	}
}
