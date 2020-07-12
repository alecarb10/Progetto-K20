package interfaces.fan.gui.view.league;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import domain.element.Day;
import domain.element.Group;
import domain.match.Match;
import domain.team.Player;
import domain.team.Team;
import domain.tournament.KnockoutPhase;
import domain.tournament.League;
import domain.tournament.MixedTournament;
import domain.tournament.Tournament;
import domain.tournament.TournamentType;
import interfaces.fan.gui.view.element.dayViewController;
import interfaces.fan.gui.view.element.teamDetailsController;
import interfaces.fan.gui.view.knockoutphase.KnockoutPhase16Controller;
import interfaces.fan.gui.view.knockoutphase.KnockoutPhase4Controller;
import interfaces.fan.gui.view.knockoutphase.KnockoutPhase8Controller;
import interfaces.fan.gui.view.util.StageLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import services.persistence.dao.impl.FacadeImpl;

public class LeagueRankingController implements Initializable {
	
	@FXML
	private Text text;
	@FXML
	private Button menuButton;
	@FXML
	private ComboBox dayComboBox;
	@FXML
	private ComboBox teamComboBox;
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
	
	//--------------------------------------------------	
	FacadeImpl facade = FacadeImpl.getInstance();
	ObservableList<Team> wTeams = FXCollections.observableArrayList();
	Tournament tournament;
	List<Day> dayMixed;
	
	
	
	public void menuButtonClicked(ActionEvent event) throws IOException {
		StageLoader SLM = new StageLoader();
		SLM.show("interfaces/fan/gui/view/FanMenu.fxml", "Fan menu", event);

	}

	public void passingData(Tournament tournamentPass) throws SQLException {
		
		text.setText(tournamentPass.getName());
		if(tournamentPass.getTournamentType() == TournamentType.LEAGUE) {
			boardButton.setVisible(false);
		}
		else if(tournamentPass.getTournamentType() == TournamentType.MIXED) {
			boardButton.setVisible(true);
			
			
		}
		
		tournament = tournamentPass;
		for(Team team : facade.getTeamsByTournament(tournamentPass)) {
			if(tournament.getTeamsList().contains(team) == false) {
			tournament.addTeamInTournament(team);
				}
			}
		for(Team team : tournament.getTeamsList()) {
			if(wTeams.contains(team) == false) {
			wTeams.add(team);
				}
			if(teamComboBox.getItems().contains(team.getName()) == false) {
			teamComboBox.getItems().add(team.getName());
			}
			
		}
		for(Day day: facade.getGroupSchedule(tournament)) {
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
		for(Team team : tournament.getTeamsList()) {
			if(team.getName() == teamNameTS) {
				teamSelectedTS = team;
			}
		}
		
		StageLoader SLTS = new StageLoader();
		SLTS.show("interfaces/fan/gui/view/element/teamDetails.fxml", "team details", event);
		teamDetailsController tdc = SLTS.getLoader().getController();
		tdc.passingData(tournament, teamSelectedTS);
				
	}
	
	
	public void daySelected(ActionEvent event) throws SQLException, IOException {
		int dayN = (int) dayComboBox.getSelectionModel().getSelectedItem();
		Day dayS = null;
		for(Day day : facade.getGroupSchedule(tournament)) {
			if(day.getNumber() == dayN) {
				dayS = day;
			}
		}
		
		
		StageLoader SLD = new StageLoader();
		SLD.showDaySelected("interfaces/fan/gui/view/element/dayView.fxml", ("DAY " + Integer.toString(dayS.getNumber())), event);
		dayViewController dvc = SLD.getLoader().getController();
		dvc.passingDataToDay(tournament, dayS);
				
	}
	
	
	
	
/*
	public void boardButtonCliccked(ActionEvent event) throws IOException, SQLException {
		int c = wTeams.size();
		switch (c) {
		case 4:
			FXMLLoader loader4 = new FXMLLoader();
			loader4.setLocation(
					getClass().getResource("/clientserver/client/fan/gui/view/knockoutphase/knockoutphase4.fxml"));
			Parent root4 = loader4.load();
			KnockoutPhase4Controller kp4c = loader4.getController();
			kp4c.passingDataToKnock4(tournament);
			Scene scene4 = new Scene(root4);
			Stage primaryStage4 = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage4.setTitle("Board");
			primaryStage4.setScene(scene4);
			primaryStage4.setResizable(false);
			Rectangle2D primScreenBounds4 = Screen.getPrimary().getVisualBounds();
			primaryStage4.setX((primScreenBounds4.getWidth() - primaryStage4.getWidth()) / 2);
			primaryStage4.setY((primScreenBounds4.getHeight() - primaryStage4.getHeight()) / 2);
			primaryStage4.show();

			break;
		case 8:
			FXMLLoader loader8 = new FXMLLoader();
			loader8.setLocation(
					getClass().getResource("/clientserver/client/fan/gui/view/knockoutphase/knockoutphase8.fxml"));
			Parent root8 = loader8.load();
			KnockoutPhase8Controller kp8c = loader8.getController();
			kp8c.passingDataToKnock8(tournament);
			Scene scene8 = new Scene(root8);
			Stage primaryStage8 = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage8.setTitle("Board");
			primaryStage8.setScene(scene8);
			primaryStage8.setResizable(false);
			Rectangle2D primScreenBounds8 = Screen.getPrimary().getVisualBounds();
			primaryStage8.setX((primScreenBounds8.getWidth() - primaryStage8.getWidth()) / 2);
			primaryStage8.setY((primScreenBounds8.getHeight() - primaryStage8.getHeight()) / 2);
			primaryStage8.show();

			break;
		case 16:
			FXMLLoader loader16 = new FXMLLoader();
			loader16.setLocation(
					getClass().getResource("/clientserver/client/fan/gui/view/knockoutphase/knockoutphase16.fxml"));
			Parent root16 = loader16.load();
			KnockoutPhase16Controller kp16c = loader16.getController();
			kp16c.passingDataToKnock16(tournament);
			Scene scene16 = new Scene(root16);
			Stage primaryStage16 = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage16.setTitle("Board");
			primaryStage16.setScene(scene16);
			primaryStage16.setResizable(false);
			Rectangle2D primScreenBounds16 = Screen.getPrimary().getVisualBounds();
			primaryStage16.setX((primScreenBounds16.getWidth() - primaryStage16.getWidth()) / 2);
			primaryStage16.setY((primScreenBounds16.getHeight() - primaryStage16.getHeight()) / 2);
			primaryStage16.show();
			break;

		}

	}
*/
}
