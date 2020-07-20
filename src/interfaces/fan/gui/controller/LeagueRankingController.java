package interfaces.fan.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import domain.element.Day;
import domain.team.Team;
import domain.tournament.Tournament;
import domain.tournament.TournamentType;
import interfaces.fan.gui.util.StageLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import services.persistence.dao.impl.FacadeImpl;

public class LeagueRankingController implements Initializable {

	@FXML
	private Text text;
	@FXML
	private Button menuButton;
	@FXML
	private ComboBox<Integer> dayComboBox;
	@FXML
	private ComboBox<String> teamComboBox;
	@FXML
	private Button boardButton;
	@FXML
	private TableView<Team> table;
	@FXML
	private TableColumn<Team, String> teamName;
	@FXML
	private TableColumn<Team, Integer> points;
	@FXML
	private TableColumn<Team, Integer> gs;
	@FXML
	private TableColumn<Team, Integer> gc;

	// --------------------------------------------------
	FacadeImpl facade = FacadeImpl.getInstance();
	ObservableList<Team> wTeams = FXCollections.observableArrayList();
	Tournament tournament;
	List<Day> dayMixed;

	public void menuButtonClicked(ActionEvent event) throws IOException {
		StageLoader SLM = new StageLoader();
		SLM.show("interfaces/fan/gui/resources/FanMenu.fxml", "Fan menu", event);

	}

	public void passingData(Tournament tournamentPass) throws SQLException {
//		System.out.println(tournamentPass.getName());
//		TournamentUtil.initTournament(tournament, tournamentPass);
//		System.out.println(tournament.getName());
//		for(Team team : tournament.getTeamsList()) {
//			System.out.println(team.getName());
//		}
		
		text.setText(tournamentPass.getName());
		if (tournamentPass.getTournamentType() == TournamentType.LEAGUE) {
			boardButton.setVisible(false);
		} else if (tournamentPass.getTournamentType() == TournamentType.MIXED) {
			boardButton.setVisible(true);
			int n = 0;
			List<Day> days = facade.getGroupSchedule(tournamentPass);
			int m = days.size();
			for(Day day: days) {
				if(day.isCompleted()) {
				n++;
			}
			if(n == m ) {
				boardButton.setDisable(false);
				}	
			else 
				boardButton.setDisable(true);
			}
				

		}

		tournament = tournamentPass;
		for (Team team : facade.getTeamsByTournament(tournamentPass)) {
			if (tournament.getTeamsList().contains(team) == false) {
				tournament.addTeamInTournament(team);
			}
		}
		for (Team team : tournament.getTeamsList()) {
			if (wTeams.contains(team) == false) {
				wTeams.add(team);
			}
			if (teamComboBox.getItems().contains(team.getName()) == false) {
				teamComboBox.getItems().add(team.getName());
			}

		}
		for (Day day : facade.getGroupSchedule(tournament)) {
			dayComboBox.getItems().add(day.getNumber());
		}
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		teamName.setCellValueFactory(new PropertyValueFactory<Team, String>("name"));
		points.setCellValueFactory(new PropertyValueFactory<Team, Integer>("points"));
		gs.setCellValueFactory(new PropertyValueFactory<Team, Integer>("goalsScored"));
		gc.setCellValueFactory(new PropertyValueFactory<Team, Integer>("goalsConceded"));
		table.setItems(wTeams);
	}

	public void teamSelected(ActionEvent event) throws IOException, SQLException {
		String teamNameTS = (String) (teamComboBox.getSelectionModel().getSelectedItem());
		Team teamSelectedTS = null;
		for (Team team : tournament.getTeamsList()) {
			if (team.getName() == teamNameTS) {
				teamSelectedTS = team;
			}
		}

		StageLoader SLTS = new StageLoader();
		SLTS.show("interfaces/fan/gui/resources/TeamDetails.fxml", "team details", event);
		TeamDetailsController tdc = SLTS.getLoader().getController();
		tdc.passingData(tournament, teamSelectedTS);

	}

	public void daySelected(ActionEvent event) throws SQLException, IOException {
		int dayN = (int) dayComboBox.getSelectionModel().getSelectedItem();
		Day dayS = null;
		for (Day day : facade.getGroupSchedule(tournament)) {
			if (day.getNumber() == dayN) {
				dayS = day;
			}
		}

		StageLoader SLD = new StageLoader();
		SLD.showDaySelected("interfaces/fan/gui/resources/DayView.fxml",
				("DAY " + Integer.toString(dayS.getNumber())), event);
		DayViewController dvc = SLD.getLoader().getController();
		dvc.passingDataToDay(tournament, dayS);

	}

	public void boardButtonCliccked(ActionEvent event) throws IOException, SQLException {
		int c = wTeams.size();
		switch (c) {
		
		case 4:
			StageLoader SLM2 = new StageLoader();
			SLM2.show("interfaces/fan/gui/resources/KnockoutPhase2.fxml", "Board", event);
			KnockoutPhase2Controller kp2c = SLM2.getLoader().getController();
			kp2c.passingDataToKnock2(tournament);
			break;
			
		case 8:
			StageLoader SLM4 = new StageLoader();
			SLM4.show("interfaces/fan/gui/resources/KnockoutPhase4.fxml", "Board", event);
			KnockoutPhase4Controller kp4c = SLM4.getLoader().getController();
			kp4c.passingDataToKnock4(tournament);
			break;
			
		case 16:
			StageLoader SLM8 = new StageLoader();
			SLM8.show("interfaces/fan/gui/resources/KnockoutPhase8.fxml", "Board", event);
			KnockoutPhase8Controller kp8c = SLM8.getLoader().getController();
			kp8c.passingDataToKnock8(tournament);
			break;

		}

	}

}
