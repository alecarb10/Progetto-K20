package interfaces.fan.localgui.view.element;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import interfaces.fan.localgui.view.league.LeagueRankingController;
import interfaces.fan.localgui.view.util.StageLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.element.Day;
import model.match.Match;
import model.team.Player;
import model.team.PlayerPositionType;
import model.team.Team;
import model.tournament.Tournament;
import services.persistence.dao.impl.FacadeImpl;

public class teamDetailsController implements Initializable {
	@FXML
	TableView<Player> table;
	@FXML
	TableColumn<Player, String> name;
	@FXML
	TableColumn<Player, String> surname;
	@FXML
	TableColumn<Player, Integer> number;
	@FXML
	TableColumn<Player, PlayerPositionType> position;
	@FXML
	ListView<String> matchesPlayed;
	@FXML
	ListView<String> matchesNotPlayed;
	@FXML
	Button backButton;
	@FXML
	Text text;
	
	FacadeImpl facade = FacadeImpl.getInstance();
	ObservableList<Player> players = FXCollections.observableArrayList();
	Tournament tournamentB;
	
	
	public void passingData(Tournament tournamentPass, Team teamPass) throws SQLException {
		text.setText(teamPass.getName());
		tournamentB = tournamentPass;
		
		for(Day day : facade.getGroupSchedule(tournamentPass)) {
			for(Match match : day.getMatchesList()) {
				if((match.getHomeTeam().getId() == teamPass.getId() || match.getAwayTeam().getId() == teamPass.getId()) && match.isPlayed()) {
					matchesPlayed.getItems().add(match.toString());
				}
				else if(match.getHomeTeam().getId() == teamPass.getId() || match.getAwayTeam().getId() == teamPass.getId())
				{
					matchesNotPlayed.getItems().add(match.toString());
				}
			}
		}
		
		for(Team team : facade.getTeamsByTournament(tournamentPass)) {
			if(team.getId() == teamPass.getId()) {
				for(Player player : team.getPlayers()) {
					players.add(player);
				}
			}
		}
		
		
	}

	
	public void backButtonClicked(ActionEvent event) throws IOException, SQLException {
		StageLoader SLB = new StageLoader();
		SLB.show("clientserver/client/fan/gui/view/league/LeagueRanking.fxml", "Ranking", event);
		LeagueRankingController lrc = SLB.getLoader().getController();
		lrc.passingData(tournamentB);
		/*
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("clientserver/client/fan/gui/view/league/LeagueRanking.fxml"));
		Parent root =loader.load();
		LeagueRankingController lrc = loader.getController();
		lrc.passingData(tournamentB);
		Scene scene = new Scene(root);
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Ranking");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
		primaryStage.show();
		*/
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		name.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		surname.setCellValueFactory(new PropertyValueFactory<Player, String>("surname"));
		number.setCellValueFactory(new PropertyValueFactory<Player, Integer>("number"));
		position.setCellValueFactory(new PropertyValueFactory<Player, PlayerPositionType>("position"));
		table.setItems(players);
		
		
	}
	

}