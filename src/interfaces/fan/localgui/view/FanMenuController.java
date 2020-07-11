package interfaces.fan.localgui.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import interfaces.fan.localgui.view.knockoutphase.KnockoutPhase16Controller;
import interfaces.fan.localgui.view.knockoutphase.KnockoutPhase4Controller;
import interfaces.fan.localgui.view.knockoutphase.KnockoutPhase8Controller;
import interfaces.fan.localgui.view.league.LeagueRankingController;
import interfaces.fan.localgui.view.util.StageLoader;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.element.Group;
import model.team.*;
import model.tournament.*;
import javafx.scene.Node;
import javafx.scene.Parent;
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
			SLL.show("clientserver/client/fan/gui/view/league/LeagueRanking.fxml", "Ranking", event);
			LeagueRankingController lrc = SLL.getLoader().getController();
			lrc.passingData(tournamentList.get(idx));
			
			/*
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/clientserver/client/fan/gui/view/league/LeagueRanking.fxml"));
			Parent root = loader.load();
			LeagueRankingController lrc = loader.getController();
			lrc.passingData(tournamentList.get(idx));
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setTitle("ranking");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
			primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
			primaryStage.show();
			*/
		}

		if (tSelect.getTournamentType() == TournamentType.KNOCKOUT_PHASE) {
			teams = facade.getTeamsByTournament(tSelect);
			int c = teams.size();
			switch (c) {
			case 4:
				StageLoader SL4 = new StageLoader();
				SL4.show("clientserver/client/fan/gui/view/knockoutphase/knockoutphase4.fxml", "Board", event);
				KnockoutPhase4Controller kp4c = SL4.getLoader().getController();
				kp4c.passingDataToKnock4(tournamentList.get(idx));
			/*	FXMLLoader loader4 = new FXMLLoader();
				loader4.setLocation(
						getClass().getResource("/clientserver/client/fan/gui/view/knockoutphase/knockoutphase4.fxml"));
				Parent root4 = loader4.load();
				KnockoutPhase4Controller kp4c = loader4.getController();
				kp4c.passingDataToKnock4(tournamentList.get(idx));
				Scene scene4 = new Scene(root4);
				Stage primaryStage4 = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage4.setTitle("Board");
				primaryStage4.setScene(scene4);
				primaryStage4.setResizable(false);
				Rectangle2D primScreenBounds4 = Screen.getPrimary().getVisualBounds();
				primaryStage4.setX((primScreenBounds4.getWidth() - primaryStage4.getWidth()) / 2);
				primaryStage4.setY((primScreenBounds4.getHeight() - primaryStage4.getHeight()) / 2);
				primaryStage4.show();
			*/	
				break;
			case 8:
				StageLoader SL8 = new StageLoader();
				SL8.show("clientserver/client/fan/gui/view/knockoutphase/knockoutphase8.fxml", "Board", event);
				KnockoutPhase8Controller kp8c = SL8.getLoader().getController();
				kp8c.passingDataToKnock8(tournamentList.get(idx));
				/*
				FXMLLoader loader8 = new FXMLLoader();
				loader8.setLocation(
						getClass().getResource("/clientserver/client/fan/gui/view/knockoutphase/knockoutphase8.fxml"));
				Parent root8 = loader8.load();
				KnockoutPhase8Controller kp8c = loader8.getController();
				kp8c.passingDataToKnock8(tournamentList.get(idx));
				Scene scene8 = new Scene(root8);
				Stage primaryStage8 = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage8.setTitle("Board");
				primaryStage8.setScene(scene8);
				primaryStage8.setResizable(false);
				Rectangle2D primScreenBounds8 = Screen.getPrimary().getVisualBounds();
				primaryStage8.setX((primScreenBounds8.getWidth() - primaryStage8.getWidth()) / 2);
				primaryStage8.setY((primScreenBounds8.getHeight() - primaryStage8.getHeight()) / 2);
				primaryStage8.show();
	*/
				break;
			case 16:
				StageLoader SL16 = new StageLoader();
				SL16.show("clientserver/client/fan/gui/view/knockoutphase/knockoutphase16.fxml", "Board", event);
				KnockoutPhase16Controller kp16c = SL16.getLoader().getController();
				kp16c.passingDataToKnock16(tournamentList.get(idx));
				
			/*	
				FXMLLoader loader16 = new FXMLLoader();
				loader16.setLocation(
						getClass().getResource("/clientserver/client/fan/gui/view/knockoutphase/knockoutphase16.fxml"));
				Parent root16 = loader16.load();
				KnockoutPhase16Controller kp16c = loader16.getController();
				kp16c.passingDataToKnock16(tournamentList.get(idx));
				Scene scene16 = new Scene(root16);
				Stage primaryStage16 = (Stage) ((Node) event.getSource()).getScene().getWindow();
				primaryStage16.setTitle("Board");
				primaryStage16.setScene(scene16);
				primaryStage16.setResizable(false);
				Rectangle2D primScreenBounds16 = Screen.getPrimary().getVisualBounds();
				primaryStage16.setX((primScreenBounds16.getWidth() - primaryStage16.getWidth()) / 2);
				primaryStage16.setY((primScreenBounds16.getHeight() - primaryStage16.getHeight()) / 2);
				primaryStage16.show();
				
			*/	
				break;

			}
		}

		if (tSelect.getTournamentType() == TournamentType.MIXED) {
			StageLoader SLM = new StageLoader();
			SLM.show("clientserver/client/fan/gui/view/league/LeagueRanking.fxml", "Mixed ranking", event);
			LeagueRankingController lrc = SLM.getLoader().getController();
			lrc.passingData(tournamentList.get(idx));
			
			/*
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/clientserver/client/fan/gui/view/league/LeagueRanking.fxml"));
			Parent root = loader.load();
			LeagueRankingController lrc = loader.getController();
			lrc.passingData(tournamentList.get(idx));
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setTitle("mixed ranking");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
			primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
			primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
			primaryStage.show();
	*/
		}

	}

}
