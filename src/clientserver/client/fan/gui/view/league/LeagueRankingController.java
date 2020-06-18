package clientserver.client.fan.gui.view.league;

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
import mvc.model.tournament.Tournament;

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
	
	//implementazione con db
	List<Team> rankingList = new ArrayList<>();
	

	
	public void populateRanking(List<Team> rankingTmp) {
		rankingList = rankingTmp;
		for(Team team : rankingList) {
			ranking.getItems().add(team.getName() + "        " + team.getPoints());
		}
		
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		populateRanking(rankingList);
		
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
