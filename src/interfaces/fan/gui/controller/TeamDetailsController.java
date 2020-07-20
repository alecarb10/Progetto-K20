package interfaces.fan.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import domain.element.Day;
import domain.match.Match;
import domain.team.Player;
import domain.team.PlayerPositionType;
import domain.team.Team;
import domain.tournament.Tournament;
import interfaces.fan.gui.util.StageLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import services.persistence.dao.impl.FacadeImpl;

public class TeamDetailsController implements Initializable {
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

		for (Day day : facade.getGroupSchedule(tournamentPass)) {
			for (Match match : day.getMatchesList()) {
				if ((match.getHomeTeam().getId() == teamPass.getId() || match.getAwayTeam().getId() == teamPass.getId())
						&& match.isPlayed()) {
					matchesPlayed.getItems().add(match.toString());
				} else if (match.getHomeTeam().getId() == teamPass.getId()
						|| match.getAwayTeam().getId() == teamPass.getId()) {
					matchesNotPlayed.getItems().add(match.toString());
				}
			}
		}

		for (Team team : facade.getTeamsByTournament(tournamentPass)) {
			if (team.getId() == teamPass.getId()) {
				for (Player player : team.getPlayers()) {
					players.add(player);
				}
			}
		}

	}

	public void backButtonClicked(ActionEvent event) throws IOException, SQLException {
		StageLoader SLB = new StageLoader();
		SLB.backToLeague(event, tournamentB);
		
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
