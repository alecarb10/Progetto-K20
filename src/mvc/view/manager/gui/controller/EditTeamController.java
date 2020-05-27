package mvc.view.manager.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import database.dao.impl.FacadeImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mvc.model.team.Stadium;
import mvc.model.team.Team;
import mvc.model.tournament.Tournament;
import mvc.view.manager.gui.util.GraphicControlsHandler;

public class EditTeamController implements Initializable {

	
	@FXML
	private ComboBox<String> cmbBoxTournament,cmbBoxTeam;
	
	@FXML
	private TextField txtFldStadiumName,txtFldStadiumCity,txtFldStadiumCapacity;
	@FXML
	private Button btnAddStadium,btnEditStadium,btnAddPlayer;
	
	private String username;
	private ObservableList<String> tournaments,teams;	
	private FacadeImpl facadeImpl;
	private List<Tournament> tournamentsList;
	private List<Team> teamsList;
	private Tournament tournament;
	private Team team;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tournaments= FXCollections.observableArrayList();
		facadeImpl= new FacadeImpl();
		this.cmbBoxTournament.setOnAction((ActionEvent)->{
			teams=getTeamsList();
			if(teams !=null)
				this.cmbBoxTeam.setItems(teams);
		});
		this.cmbBoxTeam.setOnAction((ActionEvent)->{
			team= getTeam(cmbBoxTeam.getSelectionModel().getSelectedItem());
			Stadium stadium=null;
			if(team!=null) {
				stadium=team.getStadium();	
				if(stadium!=null) {			
					txtFldStadiumName.setText(stadium.getName());
					txtFldStadiumCity.setText(stadium.getCity());
					txtFldStadiumCapacity.setText(Integer.toString(stadium.getCapacity()));
				}
			}
		});

	}
	
	public void setUsername(String username) {		
		this.username=username;
	}
	
	public void setTournamentsList(List<Tournament> tournamentsList) {
		this.tournamentsList=tournamentsList;
	}
	
	public void populateCmbBoxTournament(ObservableList<String> tournaments) {
		this.tournaments=tournaments;
		this.cmbBoxTournament.setItems(tournaments);
	}
	
	private ObservableList<String> getTeamsList(){
		ObservableList<String> teams=FXCollections.observableArrayList();
		try {
			if(cmbBoxTournament.getValue()!=null) {
				tournament=getTournament(cmbBoxTournament.getSelectionModel().getSelectedItem());
				teamsList=facadeImpl.getTeamsByTournament(tournament);
				}
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
	
	private Team getTeam(String name) {
		for(Team team:teamsList)
			if(team.getName().equals(name))
				return team;
		return null;
		
	}
	
	public void addStadium(ActionEvent event) {
		try {
			Stadium stadium= new Stadium(txtFldStadiumName.getText(), txtFldStadiumCity.getText(),Integer.parseInt(txtFldStadiumCapacity.getText()));
			team.setStadium(stadium);
			facadeImpl.storeStadium(stadium);
			facadeImpl.updateTeam(team);
			restoreComponents();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void editStadium(ActionEvent event) {
		try {
			Stadium stadium= new Stadium(txtFldStadiumName.getText(), txtFldStadiumCity.getText(),Integer.parseInt(txtFldStadiumCapacity.getText()));
			facadeImpl.updateStadium(stadium);
			restoreComponents();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	private void restoreComponents() {
		GraphicControlsHandler.resetComboBox(this.cmbBoxTournament, "Select Tournament");
		GraphicControlsHandler.resetComboBox(this.cmbBoxTeam, "Select Team");
		GraphicControlsHandler.resetObservableList(this.teams);
		GraphicControlsHandler.resetTextField(this.txtFldStadiumName, "Name");
		GraphicControlsHandler.resetTextField(this.txtFldStadiumCity, "City");
		GraphicControlsHandler.resetTextField(this.txtFldStadiumCapacity, "Capacity");
	}
}
