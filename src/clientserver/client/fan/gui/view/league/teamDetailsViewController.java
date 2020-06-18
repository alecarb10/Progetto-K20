package clientserver.client.fan.gui.view.league;

import java.io.IOException;
import java.net.URL;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

//==================================================================
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import mvc.model.team.Team;
import mvc.model.match.Match;
import java.util.ArrayList;
import mvc.model.element.Day;

public class teamDetailsViewController implements Initializable {
	@FXML
	private Button backHomeButton;
	@FXML
	private ListView<String> Match;

	List<Day> days = new ArrayList<>();

	// simulazione match team t1 team selezionato
	public void populateMatch() {
		Team t1 = new Team("Team1");
		Team t2 = new Team("Team2");
		Team t3 = new Team("Team3");
		Team t4 = new Team("Team4");
		Team t5 = new Team("Team5");
		Date d1 = new Date(2019, 10, 4);
		Date d2 = new Date(2019, 10, 11);
		Date d3 = new Date(2019, 10, 18);
		Date d4 = new Date(2019, 10, 25);
		Match m1 = new Match(d1, t1, t2);
		m1.setScore(1, 0);
		Match m2 = new Match(d2, t3, t1);
		m2.setScore(2, 0);
		Match m3 = new Match(d3, t1, t4);
		m3.setScore(0, 4);
		Match m4 = new Match(d4, t5, t4);
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
		for (Day daytmp : days) {
			for (Match m : daytmp.getMatchesList()) {
				if (m.getHomeTeam().getName() == t1.getName() || m.getAwayTeam().getName() == t1.getName()) {
					Match.getItems().add(m.toString());
				}
				break;
			}
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		populateMatch();
	}

	public void backHome(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("clientserver/client/fan/gui/view/FanMenu.fxml"));
		Scene scene = new Scene(loader.load());
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Fan view");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

}
