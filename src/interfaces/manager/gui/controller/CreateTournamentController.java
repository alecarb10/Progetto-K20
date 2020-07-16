package interfaces.manager.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import domain.exception.IllegalTeamsSizeException;
import domain.exception.SameTeamNameException;
import domain.team.Team;
import domain.tournament.KnockoutPhase;
import domain.tournament.League;
import domain.tournament.MixedTournament;
import domain.tournament.Tournament;
import interfaces.manager.gui.util.Constants;
import interfaces.manager.gui.util.GraphicControlsHandler;
import interfaces.manager.gui.util.GraphicHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import services.persistence.dao.impl.FacadeImpl;

/**
 * Controller for create the tournament
 * 
 * @see Initializable
 * @see Tournament
 * @see Team
 */

public class CreateTournamentController implements Initializable {

	@FXML
	private TextField txtFldName, txtFldTeam;
	@FXML
	private ComboBox<String> cmbBoxType,cmbBoxSize;
	@FXML
	private ListView<String> listViewTeams;
	@FXML
	private Button btnAddTeam, btnCreate;

	private ObservableList<String> teams;
	private String username;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.cmbBoxType.setItems(FXCollections.observableArrayList("League", "Knockout Phase", "Mixed"));
		this.cmbBoxType.setOnAction((ActionEvent) -> {
			this.cmbBoxSize.setItems(this.getSizesList());
		});
		teams = FXCollections.observableArrayList();
		this.listViewTeams.setItems(teams);
		this.listViewTeams.setCellFactory(TextFieldListCell.forListView());
		txtFldTeam.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyEvent) {
				if (keyEvent.getCode().equals(KeyCode.ENTER))
					addTeam();
			}
		});
		this.btnAddTeam.setOnAction((ActionEvent) -> {
			addTeam();
		});
	}

	public void removeTeamFromList(ActionEvent event) {
		this.teams.remove(this.listViewTeams.getSelectionModel().getSelectedIndex());
	}

	private ObservableList<String> getSizesList() {
		ObservableList<String> sizeList = FXCollections.observableArrayList();
		for (int i = 4, j = 2; i <= 16 && j <= 4;) {
			if (this.cmbBoxType.getSelectionModel().getSelectedIndex() == 0) {
				sizeList.add(Integer.toString(i));
				i += 2;
			} else {
				i = 2;
				sizeList.add(Integer.toString((int) Math.pow(i, j)));
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
			if(!txtFldName.getText().isBlank()) {
				if(facadeImpl.checkUnique(name, username)) {
					tournament=getTournament(name);
					if(tournament.checkTournamentSize(Integer.parseInt(cmbBoxSize.getSelectionModel().getSelectedItem()), teamsList)&&tournament.checkNamesInTeams(teamsList)) {
						if(facadeImpl.storeTournament(tournament, username)) {
								int tournamentId=facadeImpl.getLastTournamentID();
								tournament.setId(tournamentId);
								tournament.initGroup(teamsList);
								tournament.initBoard(teamsList);
								if(tournament.getGroup()!=null) {
									facadeImpl.storeGroup(tournament);
									tournament.getGroup().setId(facadeImpl.getLastElementID(tournament.getGroup()));
								}
								else {
									facadeImpl.storeBoard(tournament);
									tournament.getBoard().setId(facadeImpl.getLastElementID(tournament.getBoard()));
								}
								for(Team team:teamsList) {
									facadeImpl.storeTeam(team, tournament);
									team.setId(facadeImpl.getLastTeamID());
								}
								if(tournament.getGroup()!=null) 
								facadeImpl.storeSchedule(tournament.getGroupSchedule(), tournament);
								else 
								facadeImpl.storeSchedule(tournament.getBoardSchedule(), tournament);
							}
							Alert alert=new Alert(AlertType.INFORMATION,"Tournament successfully created.",ButtonType.OK);
							alert.showAndWait();
							if(alert.getResult()==ButtonType.OK) 
								restoreComponents();
					}
				}
				else new Alert(AlertType.ERROR,"Tournament name already exists.",ButtonType.OK).show();
			}
			else new Alert(AlertType.ERROR,"Tournament name cannot be empty.",ButtonType.OK).show();
			
		} catch (IllegalTeamsSizeException itse) {			
			new Alert(AlertType.ERROR,itse.getMessage(),ButtonType.OK).show();
		}		
		catch (SameTeamNameException stne) {
			new Alert(AlertType.ERROR,stne.getMessage(),ButtonType.OK).show();
		}
		catch (Exception e) {
			new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private Tournament getTournament(String name) {
		Tournament tournament = null;
		switch (this.cmbBoxType.getSelectionModel().getSelectedIndex()) {
		case 0:
			tournament = new League(name);
			break;
		case 1:
			tournament = new KnockoutPhase(name);
			break;
		case 2:
			tournament = new MixedTournament(name);
			break;
		default:
			tournament = null;
			break;

		}
		return tournament;
	}

	private List<Team> getTeamsList() {
		List<Team> teamsList = new ArrayList<Team>();
		for (int i = 0; i < teams.size(); i++)
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

	private void addTeam() {
		this.listViewTeams.getItems().add(this.txtFldTeam.getText());
		GraphicControlsHandler.resetTextField(this.txtFldTeam, "Team Name");
	}
}
