package clientserver.client.fan.gui.view;

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
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Node;

//=======================================
import mvc.model.tournament.*;
import mvc.model.team.*;
import java.util.ArrayList;
import java.util.List;

public class FanMenuController implements Initializable {

	@FXML
	private Button resultButton;
	@FXML
	private ListView<String> tournamentName;
	List<Tournament> t = new ArrayList<>();

	// Metodo che simulazione di prendere i dati dal DB
	public void populateListView() {
		Tournament league = new League("league");
		Tournament knockoutPhase4teams = new KnockoutPhase("knockoutPhase4Teams");
		// torneo composto da quattro team
		Team t1 = new Team("team1");
		Team t2 = new Team("team2");
		Team t3 = new Team("team3");
		Team t4 = new Team("team4");
		List<Team> list1 = new ArrayList<>();
		list1.add(t1);
		list1.add(t2);
		list1.add(t3);
		list1.add(t4);
		knockoutPhase4teams.initTournament(list1);
		//torneo composto da otto team
		List<Team> list12 = new ArrayList<>();
		Team t5 = new Team("team1");
		Team t6 = new Team("team2");
		Team t7 = new Team("team3");
		Team t8 = new Team("team4");
		Team t9 = new Team("team1");
		Team t10 = new Team("team2");
		Team t11 = new Team("team3");
		Team t12 = new Team("team4");
		list12.add(t5);
		list12.add(t6);
		list12.add(t7);
		list12.add(t8);
		list12.add(t9);
		list12.add(t10);
		list12.add(t11);
		list12.add(t12);
		Tournament knockoutPhase8teams = new KnockoutPhase("knockoutPhase8Teams");
		knockoutPhase8teams.initTournament(list12);		
		Tournament mixedTournament = new MixedTournament("MixedTournament");

		t.add(league);
		t.add(knockoutPhase4teams);
		t.add(mixedTournament);
		t.add(knockoutPhase8teams);
		for (Tournament tt : t) {
			tournamentName.getItems().add(tt.getName());
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		populateListView();
	}

	public void textSelected(ActionEvent event) throws IOException {
		ObservableList<String> tournamentNameTmp;
		tournamentNameTmp = tournamentName.getSelectionModel().getSelectedItems();
		
		for(Tournament tt : t) {
			for(String ntmp : tournamentNameTmp) {
				if(ntmp == tt.getName() && tt.getTournamentType() == TournamentType.LEAGUE) {
					FXMLLoader loader = new FXMLLoader();
					loader.setLocation(getClass().getResource("/clientserver/client/fan/gui/view/LeagueRanking.fxml"));
					Scene scene = new Scene(loader.load());
					Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					primaryStage.setTitle("ranking");
					primaryStage.setScene(scene);
					primaryStage.show();
					break;
				}
		
				if(ntmp == tt.getName() && tt.getTournamentType() == TournamentType.KNOCKOUT_PHASE) {
					int n = tt.getTournamentElement().getTeamsList().size();
					switch(n) {
						
					case 4:
						FXMLLoader loader4 = new FXMLLoader();
						loader4.setLocation(getClass().getResource("/clientserver/client/fan/gui/view/knockoutphase/knockoutphase4.fxml"));
						Scene scene4 = new Scene(loader4.load());
						Stage primaryStage4 = (Stage) ((Node) event.getSource()).getScene().getWindow();
						primaryStage4.setTitle("board");
						primaryStage4.setScene(scene4);
						primaryStage4.show();
						break;
					
					case 8:
						FXMLLoader loader8 = new FXMLLoader();
						loader8.setLocation(getClass().getResource("/clientserver/client/fan/gui/view/knockoutphase/knockoutphase8.fxml"));
						Scene scene8 = new Scene(loader8.load());
						Stage primaryStage8 = (Stage) ((Node) event.getSource()).getScene().getWindow();
						primaryStage8.setTitle("board");
						primaryStage8.setScene(scene8);
						primaryStage8.show();
						break;		
					default :
						System.out.println("non trovato");
						break;
					}
				}		
			}
		}//chiusura del for
		
		
		
		
	}
	
	
	

}
