package interfaces.fan.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import domain.team.*;
import domain.tournament.*;
import interfaces.fan.gui.util.StageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import services.persistence.dao.impl.FacadeImpl;

import java.util.ArrayList;
import java.util.List;

public class FanMenuController implements Initializable {

	@FXML
	private Button resultButton;
	@FXML
	private ListView<String> tournamentNames;

	FacadeImpl facade = FacadeImpl.getInstance();
	List<Tournament> tournamentList = new ArrayList<>();
	List<Team> teams;
	StageLoader error = new StageLoader();

	public void populateTournamentNames() {
		try {
			tournamentList = facade.getAllTournaments();
		} catch (SQLException e) {
			new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
		}
		for (Tournament tournament : tournamentList) {
			tournamentNames.getItems().add(tournament.getName());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		populateTournamentNames();
	}

	public void tournamentSelected(ActionEvent event) {
		int idx = tournamentNames.getSelectionModel().getSelectedIndex();
		try {
			Tournament tSelect = tournamentList.get(idx);
			if (tSelect.getTournamentType() == TournamentType.LEAGUE) {
				StageLoader SLL = new StageLoader();
				SLL.show("interfaces/fan/gui/resources/LeagueRanking.fxml", "Ranking", event);
				LeagueRankingController lrc = SLL.getLoader().getController();
				lrc.passingData(tournamentList.get(idx));

			}

			if (tSelect.getTournamentType() == TournamentType.KNOCKOUT_PHASE) {
				try {
					teams = facade.getTeamsByTournament(tSelect);
				} catch (SQLException e) {
					new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
				}
				int c = teams.size();
				switch (c) {

				case 4:
					StageLoader SL4 = new StageLoader();
					SL4.show("interfaces/fan/gui/resources/KnockoutPhase4.fxml", "Board", event);
					KnockoutPhase4Controller kp4c = SL4.getLoader().getController();
					kp4c.passingDataToKnock4(tournamentList.get(idx));
					break;

				case 8:
					StageLoader SL8 = new StageLoader();
					SL8.show("interfaces/fan/gui/resources/KnockoutPhase8.fxml", "Board", event);
					KnockoutPhase8Controller kp8c = SL8.getLoader().getController();
					kp8c.passingDataToKnock8(tournamentList.get(idx));
					break;

				case 16:
					StageLoader SL16 = new StageLoader();
					SL16.show("interfaces/fan/gui/resources/KnockoutPhase16.fxml", "Board", event);
					KnockoutPhase16Controller kp16c = SL16.getLoader().getController();
					kp16c.passingDataToKnock16(tournamentList.get(idx));
					break;

				}
			}

			if (tSelect.getTournamentType() == TournamentType.MIXED) {
				StageLoader SLM = new StageLoader();
				SLM.show("interfaces/fan/gui/resources/LeagueRanking.fxml", "Mixed ranking", event);
				LeagueRankingController lrc = SLM.getLoader().getController();
				lrc.passingData(tournamentList.get(idx));

			}
		} catch (IndexOutOfBoundsException e) {
			new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
		}

	}

}
