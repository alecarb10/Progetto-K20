package clientserver.client.fan.gui.view;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

	FacadeImpl facade = new FacadeImpl();
	List<Tournament> tournamentList = new ArrayList<>();

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
			System.out.println(tSelect.getName());
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
