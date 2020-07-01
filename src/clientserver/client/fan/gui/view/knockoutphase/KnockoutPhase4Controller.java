package clientserver.client.fan.gui.view.knockoutphase;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import clientserver.client.fan.gui.view.util.StageLoader;
import database.dao.impl.FacadeImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mvc.model.element.Day;
import mvc.model.match.Match;
import mvc.model.tournament.KnockoutPhase;
import mvc.model.tournament.Tournament;
import mvc.model.tournament.TournamentType;

public class KnockoutPhase4Controller implements Initializable{
	@FXML
	Button backButton;
	@FXML
	Text textBoard;
	@FXML
	Label label1;
	@FXML
	Label label2;
	@FXML
	Label label3;
	@FXML
	Label label4;
	@FXML
	Label label5;
	@FXML
	Label label6;
	@FXML
	Label label7;
	
	List<Label> labelDay1c = new ArrayList<>();
	List<Label> labelDay1f = new ArrayList<>();	
	List<Label> labelDay2c = new ArrayList<>();
	List<Label> labelDay2f = new ArrayList<>();
	
	FacadeImpl facade = FacadeImpl.getInstance();
	List<Day> days; 
	
	public void passingDataToKnock4(Tournament k4) throws SQLException {
		textBoard.setText(k4.getName());
		if(k4.getTournamentType() == TournamentType.KNOCKOUT_PHASE) {
			days = facade.getSchedule((KnockoutPhase)k4,false);
			}
			else if(k4.getTournamentType() == TournamentType.MIXED) {
				days = facade.getSchedule(k4, true);
			}
		labelDay1c.add(label1);
		labelDay1f.add(label2);
		labelDay1c.add(label3);
		labelDay1f.add(label4);
		labelDay2c.add(label5);
		labelDay2f.add(label6);
		for(Label labelc : labelDay1c) {
			for(Label labelf: labelDay1f) {
				for(Day day : days) {
					for(Match match: day.getMatchesList()) {
						if(day.getNumber() == 1) {
							
							labelc.setText(match.getHomeTeam().getName() + "          " + match.getHomeScore());
							labelf.setText(match.getAwayTeam().getName() + "          " + match.getAwayScore());
							labelDay1c.iterator().next().setText(day.getMatchesList().iterator().next().getHomeTeam().getName()+ "          " + day.getMatchesList().iterator().next().getHomeScore());
							labelDay1f.iterator().next().setText(day.getMatchesList().iterator().next().getAwayTeam().getName()+ "          " + day.getMatchesList().iterator().next().getAwayScore());	
						}
					}
				}
				
			}
		}
		
		for(Label labelc : labelDay2c) {
			for(Label labelf : labelDay2f ) {
				for(Day day : days) {
					if(day.getNumber() == 2) {
						for(Match match : day.getMatchesList()) {
							labelc.setText(match.getHomeTeam().getName() + "          " + match.getHomeScore());
							labelf.setText(match.getAwayTeam().getName() + "          " + match.getAwayScore());
							label7.setText(match.getWinner().getName());
							break;
						}
					}
				}
					
			}
		}
		
	
		
		
	}
	
	
	public void backButtonClicked(ActionEvent event) throws IOException {
		StageLoader SLB = new StageLoader();
		SLB.show("clientserver/client/fan/gui/view/FanMenu.fxml", "Fan menu", event);
		/*FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getClassLoader().getResource("clientserver/client/fan/gui/view/FanMenu.fxml"));
		Scene scene = new Scene(loader.load());
		Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		primaryStage.setTitle("Fan menu");
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
		// TODO Auto-generated method stub
		
	}
	

}
