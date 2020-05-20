package clientserver.client.fan.gui.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Node;
import mvc.model.element.Day;
//===============
import mvc.model.element.Group;
import mvc.model.element.TournamentElement;
import mvc.model.match.Match;
import mvc.model.team.Team;
import mvc.model.tournament.League;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeagueRankingController implements Initializable {
	@FXML
	private Button menuButton;
	@FXML
	private Button teamDetailsButton;
	@FXML
	private ListView<String> ranking;
	@FXML
	private ComboBox dayComboBox;

	// parte della simulazione
	List<Day> days = new ArrayList<>();

	// simulazione del campionato
	public void populateRanking() {
		Team t1 = new Team("Team1");
		Team t2 = new Team("Team2");
		Team t3 = new Team("Team3");
		Team t4 = new Team("Team4");
		List<Team> teams = new ArrayList<>();
		teams.add(t1);
		teams.add(t2);
		teams.add(t3);
		teams.add(t4);
		// simulazione della classifica
		t1.setPoints(3);
		t2.setPoints(1);
		t3.setPoints(2);
		t4.setPoints(4);
		League league = new League("Prova");
		league.initTournament(teams);
		List<Team> tmpRanking = league.getTournamentElement().getRanking();
		for (Team team : tmpRanking) {
			ranking.getItems().add(team.getName() + "		" + team.getPoints());
		}
		// Simulazione delle giornate
		Date d1 = new Date(2019, 10, 4);
		Date d2 = new Date(2019, 10, 11);
		Date d3 = new Date(2019, 10, 18);
		Date d4 = new Date(2019, 10, 25);
		Match m1 = new Match(d1, t1, t2);
		m1.setScore(1, 0);
		Match m2 = new Match(d2, t3, t4);
		m2.setScore(2, 0);
		Match m3 = new Match(d3, t1, t4);
		m3.setScore(0, 4);
		Match m4 = new Match(d4, t2, t3);
		m4.setScore(2, 0);
		List<Match> lm1 = new ArrayList<>();
		List<Match> lm2 = new ArrayList<>();
		List<Match> lm3 = new ArrayList<>();
		List<Match> lm4 = new ArrayList<>();
		lm1.add(m1);
		lm2.add(m2);
		lm3.add(m3);
		lm4.add(m4);
		Day day1 = new Day(1, lm1, d1);
		Day day2 = new Day(2, lm2, d2);
		Day day3 = new Day(3, lm3, d3);
		Day day4 = new Day(4, lm4, d4);
		days.add(day1);
		days.add(day2);
		days.add(day3);
		days.add(day4);
		for (Day dayTmp : days) {
			dayComboBox.getItems().add(dayTmp.getNumber());
		}
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		populateRanking();
	}

	
	public void selectedDay(ActionEvent event) {
		for (int i = 0; i < dayComboBox.getItems().size(); i++) {
			int daySelected = (int) dayComboBox.getSelectionModel().getSelectedItem();
			for (Day day : days) {
				if (day.getNumber() == daySelected)
					ranking.getItems().clear();
				List<Match> matchesDay = day.getMatchesList();
				for (Match md : matchesDay) {
					ranking.getItems().add(md.toString());
				}
			}
		}
	}

	public void backButtonClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("clientserver/client/fan/gui/view/FanMenu.fxml"));
		Scene scene = new Scene(loader.load());
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Fan menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void teamSelected(ActionEvent event) throws IOException {
		ObservableList<String> teamName;
		teamName = ranking.getSelectionModel().getSelectedItems();
		for (String name : teamName) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("clientserver/client/fan/gui/view/teamDetailsView.fxml"));
			Scene scene = new Scene(loader.load());
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setTitle("Team details");
			primaryStage.setScene(scene);
			primaryStage.show();
			break;
		}
	}

}
