package clientserver.client.fan.gui.view.league;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import clientserver.client.fan.gui.view.knockoutphase.KnockoutPhase16Controller;
import clientserver.client.fan.gui.view.knockoutphase.KnockoutPhase4Controller;
import clientserver.client.fan.gui.view.knockoutphase.KnockoutPhase8Controller;
import database.dao.impl.FacadeImpl;
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
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import mvc.model.element.Day;
import mvc.model.element.Group;
import mvc.model.match.Match;
import mvc.model.team.Team;
import mvc.model.tournament.KnockoutPhase;
import mvc.model.tournament.League;
import mvc.model.tournament.MixedTournament;
import mvc.model.tournament.Tournament;
//===============
import mvc.model.tournament.TournamentType;

public class LeagueRankingController implements Initializable {
	@FXML
	private Button menuButton;
	@FXML
	private Button teamDetailsButton;
	@FXML
	private ListView<String> ranking;
	@FXML
	private ComboBox dayComboBox;
	@FXML
	private Text text;
	@FXML
	private Button rankingButton;
	@FXML
	private Button boardButton;

	FacadeImpl facade = FacadeImpl.getInstance();
	MixedTournament mixed;

	List<Team> wTeams;
	List<Team> teams;
	List<Day> days;
	List<Day> dayMixed;
	Day day;
	Tournament tournament;

	public void menuButtonClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("clientserver/client/fan/gui/view/FanMenu.fxml"));
		Scene scene = new Scene(loader.load());
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Fan menu");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
		primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
		primaryStage.show();
	}

	public void passingData(Tournament tournamentPass) throws SQLException {

		if (tournamentPass.getTournamentType() == TournamentType.LEAGUE) {
			boardButton.setVisible(false);
			tournament = (League) tournamentPass;
			teams = facade.getTeamsByTournament(tournamentPass);
			for (Team team : teams) {
				tournament.addTeamInTournament(team);
			}
			wTeams = tournament.getTeamsList();
			for (Team team : wTeams) {
				ranking.getItems().add(team.getName() + "             " + team.getPoints());
			}

			days = facade.getSchedule(tournamentPass, false);
			for (Day day : days) {
				dayComboBox.getItems().add(day.getNumber());
			}

		}

		if (tournamentPass.getTournamentType() == TournamentType.MIXED) {
			boardButton.setVisible(true);
			tournament = (MixedTournament) tournamentPass;
			teams = facade.getTeamsByTournament(tournamentPass);
			for (Team team : teams) {
				tournament.addTeamInTournament(team);
			}
			wTeams = tournament.getTeamsList();
			for (Team team : wTeams) {
				ranking.getItems().add(team.getName() + "             " + team.getPoints());
			}
			days = facade.getSchedule(tournamentPass, false);
			for (Day day : days) {
				dayComboBox.getItems().add(day.getNumber());
			}
			dayMixed = facade.getSchedule(tournamentPass, true);
			if (dayMixed.isEmpty()) {
				boardButton.setDisable(false);
			} else {
				boardButton.setDisable(true);
			}

		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	public void rankingButtonClicked(ActionEvent event) {
		teamDetailsButton.setDisable(false);
		ranking.getItems().clear();
		text.setText("RANKING");
		for (Team team : wTeams) {
			ranking.getItems().add(team.getName() + "             " + team.getPoints());
		}
	}

	public void daySelected(ActionEvent event) {
		teamDetailsButton.setDisable(true);
		int indx = (int) dayComboBox.getSelectionModel().getSelectedIndex();
		ranking.getItems().clear();
		text.setText("DAY  " + (indx + 1));
		day = days.get(indx);
		for (Match match : day.getMatchesList()) {
			ranking.getItems().add(match.toString());
		}

	}

	public void teamSelected(ActionEvent event) {
		teamDetailsButton.setDisable(true);
		int index = ranking.getSelectionModel().getSelectedIndex();
		ranking.getItems().clear();
		text.setText(wTeams.get(index).getName());
		for (Day day : days) {
			for (Match match : day.getMatchesList()) {
				if (match.getHomeTeam().getId() == wTeams.get(index).getId()
						|| match.getAwayTeam().getId() == wTeams.get(index).getId()) {
					ranking.getItems().add(match.toString());
				}
			}
		}
	}

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

}
