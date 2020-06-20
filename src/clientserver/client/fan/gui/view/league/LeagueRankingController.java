package clientserver.client.fan.gui.view.league;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import database.dao.impl.FacadeImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;
import mvc.model.element.Day;
import mvc.model.element.Group;
import mvc.model.match.Match;
import mvc.model.team.Team;
import mvc.model.tournament.League;
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
	
	FacadeImpl facade = new FacadeImpl();
	League league;
	
	List<Team> teams;
	List<Day> days; 
	Day day;
	
	
	public void menuButtonClicked(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("clientserver/client/fan/gui/view/FanMenu.fxml"));
		Scene scene = new Scene(loader.load());
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Fan menu");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	public void passingData(League leagueTmp) throws SQLException {  // popolazione List<View> e comboBox
		league = leagueTmp;
		teams = facade.getTeamsByTournament(leagueTmp);
		for(Team team : teams) {
			league.addTeamInTournament(team);
							}
		for(Team team : league.getTeamsList()) {                                     //con league.getTeamList() avviene già l'oridnazione in base ai punti
			ranking.getItems().add(team.getName()+"             "+ team.getPoints());
							}	
		
		days = facade.getSchedule(leagueTmp);
		for(Day day : days) {
			dayComboBox.getItems().add(day.getNumber());
				}
			
		}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub	
	}
	
	public void rankingButtonClicked(ActionEvent event) {
		ranking.getItems().clear();
		text.setText("RANKING");
		for(Team team : league.getTeamsList()) {                                     
			ranking.getItems().add(team.getName()+"             "+ team.getPoints());
							}	
	}
	
	public void daySelected(ActionEvent event) {
		int indx = (int) dayComboBox.getSelectionModel().getSelectedIndex();
		ranking.getItems().clear();
		text.setText("DAY  " + (indx+1));
		day = days.get(indx);
	    for(Match match : day.getMatchesList()) {
	    	ranking.getItems().add(match.toString());
	    }
		
	}
	
	public void teamSelected(ActionEvent event) {
		int index = ranking.getSelectionModel().getSelectedIndex();
		ranking.getItems().clear();
		text.setText(league.getTeamsList().get(index).getName());
		for(Day day: days) {
			for(Match match : day.getMatchesList()) {
				if(match.getHomeTeam().getId() == league.getTeamsList().get(index).getId() || match.getAwayTeam().getId() == league.getTeamsList().get(index).getId()) {
					ranking.getItems().add(match.toString());		
				}
					}
							}		
		}
		
		
	
	
	
	


}
