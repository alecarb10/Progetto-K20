package clientserver.client.fan.gui.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import clientserver.client.fan.gui.view.knockoutphase.KnockoutPhase4Controller;
import clientserver.client.fan.gui.view.knockoutphase.KnockoutPhase8Controller;
import clientserver.client.fan.gui.view.league.LeagueRankingController;
import database.dao.impl.FacadeImpl;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
//=======================================
import mvc.model.tournament.*;
import mvc.model.element.Group;
import mvc.model.team.*;
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
		if(tSelect.getTournamentType() == TournamentType.LEAGUE) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/clientserver/client/fan/gui/view/league/LeagueRanking.fxml"));
			Parent root=loader.load();
			LeagueRankingController lrc = loader.getController();
			League league = (League) tournamentList.get(idx);
			lrc.passingData(league);
			Scene scene = new Scene(root);
			Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			primaryStage.setTitle("ranking");
			primaryStage.setScene(scene);
			primaryStage.show();			
		}
		
		
		
		if(tSelect.getTournamentType() == TournamentType.KNOCKOUT_PHASE) {
			teams = facade.getTeamsByTournament(tSelect);
			int c = teams.size();
			switch(c) {
				case 4:
					FXMLLoader loader4 = new FXMLLoader();
					loader4.setLocation(getClass().getResource("/clientserver/client/fan/gui/view/knockoutphase/knockoutphase4.fxml"));
					Parent root4=loader4.load();
					KnockoutPhase4Controller kp4c = loader4.getController();
					KnockoutPhase knockoutPhase4 = (KnockoutPhase) tournamentList.get(idx);
					kp4c.passingDataToKnock4(knockoutPhase4);
					Scene scene4 = new Scene(root4);
					Stage primaryStage4 = (Stage) ((Node) event.getSource()).getScene().getWindow();
					primaryStage4.setTitle("Board");
					primaryStage4.setScene(scene4);
					primaryStage4.show();		
						
					break;
				case 8:
					FXMLLoader loader8 = new FXMLLoader();
					loader8.setLocation(getClass().getResource("/clientserver/client/fan/gui/view/knockoutphase/knockoutphase8.fxml"));
					Parent root8=loader8.load();
					KnockoutPhase8Controller kp8c = loader8.getController();
					KnockoutPhase knockoutPhase8 = (KnockoutPhase) tournamentList.get(idx);
					kp8c.passingDataToKnock8(knockoutPhase8);
					Scene scene8 = new Scene(root8);
					Stage primaryStage8 = (Stage) ((Node) event.getSource()).getScene().getWindow();
					primaryStage8.setTitle("Board");
					primaryStage8.setScene(scene8);
					primaryStage8.show();	
					
					break;
				case 16:
					System.out.println("16");
					break;
			
			}
			
			
			
			
			
			
			
			
			
			
		}
		if(tSelect.getTournamentType() == TournamentType.MIXED) {
			System.out.println(tSelect.getName());
		}
		
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
/*
	public void tournamentSelected(ActionEvent event) throws IOException {
		ObservableList<String> tournamentNameSelected;
		tournamentNameSelected = tournamentNames.getSelectionModel().getSelectedItems();
		
			for(Tournament tournament : tournamentList) {
				for(String tournamentNameSelectedTmp : tournamentNameSelected) {
					
					
					if(tournamentNameSelectedTmp == tournament.getName() && tournament.getTournamentType() == TournamentType.LEAGUE) {
						FXMLLoader loader = new FXMLLoader();
						loader.setLocation(getClass().getResource("/clientserver/client/fan/gui/view/LeagueRanking.fxml"));
		
						LeagueRankingController LRC = loader.getController();
						League leagueTmp = (League) tournament;
						Group groupTmp = (Group)leagueTmp.getTournamentElement();
						List<Team> rankingTmp = groupTmp.getRanking();		
						LRC.populateRanking(rankingTmp);
						
						Scene scene = new Scene(loader.load());
						Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
						primaryStage.setTitle("ranking");
						primaryStage.setScene(scene);
						primaryStage.show();
						
			
					}
					else System.out.println("altro");
						break;
					
					
					
					
				} //chiusura for interno
			} // chiusura for esterno
		
		
	}
	*/
	

}
