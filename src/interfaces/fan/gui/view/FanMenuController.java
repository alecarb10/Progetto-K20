package interfaces.fan.gui.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


import domain.team.*;
import domain.tournament.*;
import interfaces.fan.gui.view.knockoutphase.KnockoutPhase16Controller;
import interfaces.fan.gui.view.knockoutphase.KnockoutPhase4Controller;
import interfaces.fan.gui.view.knockoutphase.KnockoutPhase8Controller;
import interfaces.fan.gui.view.league.LeagueRankingController;
import interfaces.fan.gui.view.util.StageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

	public void populateTournamentNames() throws SQLException {
		tournamentList = facade.getAllTournaments();
		for (Tournament tournament : tournamentList) {
			tournamentNames.getItems().add(tournament.getName());
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			populateTournamentNames();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void tournamentSelected(ActionEvent event) throws IOException, SQLException {
		int idx = tournamentNames.getSelectionModel().getSelectedIndex();
		Tournament tSelect = tournamentList.get(idx);
		if (tSelect.getTournamentType() == TournamentType.LEAGUE) {
			StageLoader SLL = new StageLoader();
			SLL.show("interfaces/fan/gui/view/league/LeagueRanking.fxml", "Ranking", event);
			LeagueRankingController lrc = SLL.getLoader().getController();
			lrc.passingData(tournamentList.get(idx));

		}

		if (tSelect.getTournamentType() == TournamentType.KNOCKOUT_PHASE) {
			teams = facade.getTeamsByTournament(tSelect);
			int c = teams.size();
			switch (c) {

			case 4:
				StageLoader SL4 = new StageLoader();
				SL4.show("interfaces/fan/gui/view/knockoutphase/knockoutphase4.fxml", "Board", event);
				KnockoutPhase4Controller kp4c = SL4.getLoader().getController();
				kp4c.passingDataToKnock4(tournamentList.get(idx));
				break;

			case 8:
				StageLoader SL8 = new StageLoader();
				SL8.show("interfaces/fan/gui/view/knockoutphase/knockoutphase8.fxml", "Board", event);
				KnockoutPhase8Controller kp8c = SL8.getLoader().getController();
				kp8c.passingDataToKnock8(tournamentList.get(idx));
				break;

			case 16:
				StageLoader SL16 = new StageLoader();
				SL16.show("interfaces/fan/gui/view/knockoutphase/knockoutphase16.fxml", "Board", event);
				KnockoutPhase16Controller kp16c = SL16.getLoader().getController();
				kp16c.passingDataToKnock16(tournamentList.get(idx));
				break;

			}
		}

		if (tSelect.getTournamentType() == TournamentType.MIXED) {
			StageLoader SLM = new StageLoader();
			SLM.show("interfaces/fan/gui/view/league/LeagueRanking.fxml", "Mixed ranking", event);
			LeagueRankingController lrc = SLM.getLoader().getController();
			lrc.passingData(tournamentList.get(idx));

		}

	}

}
