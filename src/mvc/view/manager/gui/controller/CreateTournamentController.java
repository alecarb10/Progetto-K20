package mvc.view.manager.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import database.dao.impl.FacadeImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldListCell;
import mvc.model.team.Team;
import mvc.model.tournament.KnockoutPhase;
import mvc.model.tournament.League;
import mvc.model.tournament.MixedTournament;
import mvc.model.tournament.Tournament;
import mvc.view.manager.gui.util.GraphicControlsHandler;

public class CreateTournamentController implements Initializable {
	
	@FXML
	private TextField txtFldName,txtFldTeam;
	@FXML
	private ComboBox<String> cmbBoxType,cmbBoxSize;
	@FXML
	private ListView<String> listViewTeams;
	@FXML
	private Button btnAddTeam,btnCreate;
	
	private ObservableList<String> teams = FXCollections.observableArrayList();
	private String username;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.cmbBoxType.setItems(FXCollections.observableArrayList("League","Knockout Phase","Mixed"));
		this.cmbBoxType.setOnAction((ActionEvent)->{
			this.cmbBoxSize.setItems(this.getSizesList());
		});
		this.listViewTeams.setItems(teams);
		this.listViewTeams.setCellFactory(TextFieldListCell.forListView());
		this.btnAddTeam.setOnAction((ActionEvent)->{
			this.listViewTeams.getItems().add(this.txtFldTeam.getText());
			GraphicControlsHandler.resetTextField(this.txtFldTeam, "Team Name");
		});
	}

	public void removeTeamFromList(ActionEvent event) {
		this.teams.remove(this.listViewTeams.getSelectionModel().getSelectedIndex());
	}
	
	private ObservableList<String> getSizesList(){
		ObservableList<String> sizeList= FXCollections.observableArrayList();
		for(int i=4,j=2;i<=16&&j<=4;) {
			if(this.cmbBoxType.getSelectionModel().getSelectedIndex()==0) {
				sizeList.add(Integer.toString(i));
				i+=2;
			}
			else {				
				i=2;
				sizeList.add(Integer.toString((int)Math.pow(i, j)));
				j++;
				
			}
				
		}		
		return sizeList;
	}
	
	public void createTournament(ActionEvent event) {		
		Tournament tournament=null;
		FacadeImpl facadeImpl= FacadeImpl.getInstance();
		List<Team> teamsList=getTeamsList();
		String name=this.txtFldName.getText();
		try {		
			
			if(facadeImpl.checkUnique(name, username)) {
				tournament=getTournament(name);
				if(facadeImpl.storeTournament(tournament, username)) {
						int tournamentId=facadeImpl.getLastTournamentID();
						tournament.setId(tournamentId);
						tournament.initTournament(teamsList);
						facadeImpl.storeElement(tournament);
						int elementId=facadeImpl.getLastElementID(tournament.getTournamentElement());
						tournament.getTournamentElement().setId(elementId);
						for(Team team:teamsList) {
							facadeImpl.storeTeam(team, tournament);
							int teamId=facadeImpl.getLastTeamID();
							team.setId(teamId);
						}
						facadeImpl.storeSchedule(tournament.getSchedule(), tournament);
					}
					restoreComponents();
			}
			else new Alert(AlertType.ERROR,"Tournament name already exists.",ButtonType.OK).show();
			
		} catch (Exception e) {			
			e.printStackTrace();
		}		
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	private Tournament getTournament(String name) {
		Tournament tournament=null;
		switch(this.cmbBoxType.getSelectionModel().getSelectedIndex()) {
			case 0: 
				tournament= new League(name);
				break;
			case 1: 
				tournament= new KnockoutPhase(name);
				break;
			case 2:
				tournament= new MixedTournament(name);
				break;
			default:
				tournament=null;
				break;
			
		}
		return tournament;
	}
	
	private List<Team> getTeamsList(){
		List<Team> teamsList=new ArrayList<Team>();
		for(int i=0;i<teams.size();i++) 
			teamsList.add(new Team(teams.get(i)));
		return teamsList;
	}
	
	private void restoreComponents() {
		GraphicControlsHandler.resetTextField(this.txtFldName, "Name");
		GraphicControlsHandler.resetComboBox(this.cmbBoxType, "Type");
		GraphicControlsHandler.resetComboBox(this.cmbBoxSize, "Size");
		GraphicControlsHandler.resetTextField(this.txtFldTeam, "Team Name");
		GraphicControlsHandler.resetObservableList(this.teams);
	}
	
}
