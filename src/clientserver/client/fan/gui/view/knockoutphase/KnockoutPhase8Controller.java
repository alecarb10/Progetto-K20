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

public class KnockoutPhase8Controller implements Initializable {
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
	@FXML
	Label label8;
	@FXML
	Label label9;
	@FXML
	Label label10;
	@FXML
	Label label11;
	@FXML
	Label label12;
	@FXML
	Label label13;
	@FXML
	Label label14;
	@FXML
	Label label15;
	

	List<Label> labelDay1c = new ArrayList<>();
	List<Label> labelDay1f = new ArrayList<>();	
	List<Label> labelDay2c = new ArrayList<>();
	List<Label> labelDay2f = new ArrayList<>();
	List<Label> labelDay3c = new ArrayList<>();
	List<Label> labelDay3f = new ArrayList<>();	
	
	FacadeImpl facade = FacadeImpl.getInstance();
	List<Day> days;
	
	public void passingDataToKnock8(Tournament k8) throws SQLException {
		textBoard.setText(k8.getName());
		if(k8.getTournamentType() == TournamentType.KNOCKOUT_PHASE) {
		days = facade.getBoardSchedule(k8);
		}
		else if(k8.getTournamentType() == TournamentType.MIXED) {
			days = facade.getBoardSchedule(k8);
		}
		
		
		List<Label> labelsList= new ArrayList<>();
		labelsList.add(label1);
		labelsList.add(label2);
		labelsList.add(label3);
		labelsList.add(label4);		
		labelsList.add(label5);
		labelsList.add(label6);
		labelsList.add(label7);
		labelsList.add(label8);
		labelsList.add(label9);
		labelsList.add(label10);
		labelsList.add(label11);
		labelsList.add(label12);
		labelsList.add(label13);
		labelsList.add(label14);
		labelsList.add(label15);
		
		for(int i=0,j=0,k=0;i<labelsList.size();i++) { //i=label,j=day,k=match		
			final double EIGHT=Math.pow(2, 3); 
			if(i==EIGHT||i==EIGHT+Math.pow(2, 2)) {
				k=0;
				j++;
			}
			Match match=null;
			if(i==labelsList.size()-1) {
				k=0;
				match=days.get(j).getMatchesList().get(k);
				labelsList.get(i).setText(match.getWinner().getName());
			}
			else {
				match=days.get(j).getMatchesList().get(k);
				if(i%2==0)
					labelsList.get(i).setText(match.getHomeTeam().getName() +"            "+ match.getHomeScore());
				else {
					labelsList.get(i).setText(match.getAwayTeam().getName() +"            "+ match.getAwayScore());
					k++;
				}
			}
		}
	
		
//		label1.setText(days.get(0).getMatchesList().get(0).getHomeTeam().getName() +"            "+ days.get(0).getMatchesList().get(0).getHomeScore());
//		label2.setText(days.get(0).getMatchesList().get(0).getAwayTeam().getName() +"            " +days.get(0).getMatchesList().get(0).getAwayScore());
//		
//		label3.setText(days.get(0).getMatchesList().get(1).getHomeTeam().getName() +"            "+ days.get(0).getMatchesList().get(1).getHomeScore());
//		label4.setText(days.get(0).getMatchesList().get(1).getAwayTeam().getName() +"            " +days.get(0).getMatchesList().get(1).getAwayScore());
//		
//		label5.setText(days.get(0).getMatchesList().get(2).getHomeTeam().getName() +"            "+ days.get(0).getMatchesList().get(2).getHomeScore());
//		label6.setText(days.get(0).getMatchesList().get(2).getAwayTeam().getName() +"            " +days.get(0).getMatchesList().get(2).getAwayScore());
//		
//		label7.setText(days.get(0).getMatchesList().get(3).getHomeTeam().getName() +"            "+ days.get(0).getMatchesList().get(3).getHomeScore());
//		label8.setText(days.get(0).getMatchesList().get(3).getAwayTeam().getName() +"            " +days.get(0).getMatchesList().get(3).getAwayScore());
//		
//		
//		label9.setText(days.get(1).getMatchesList().get(0).getHomeTeam().getName() +"            "+ days.get(1).getMatchesList().get(0).getHomeScore());
//		label10.setText(days.get(1).getMatchesList().get(0).getAwayTeam().getName() +"            " +days.get(1).getMatchesList().get(0).getAwayScore());
//		
//
//		label11.setText(days.get(1).getMatchesList().get(1).getHomeTeam().getName() +"            "+ days.get(1).getMatchesList().get(1).getHomeScore());
//		label12.setText(days.get(1).getMatchesList().get(1).getAwayTeam().getName() +"            " +days.get(1).getMatchesList().get(1).getAwayScore());
//		
//
//		label13.setText(days.get(2).getMatchesList().get(0).getHomeTeam().getName() +"            "+ days.get(2).getMatchesList().get(0).getHomeScore());
//		label14.setText(days.get(2).getMatchesList().get(0).getAwayTeam().getName() +"            " +days.get(2).getMatchesList().get(0).getAwayScore());
//		
//		label15.setText(days.get(2).getMatchesList().get(0).getWinner().getName());
		
		
		
		
		/*
		labelDay1c.add(label1);
		labelDay1f.add(label2);
		labelDay1c.add(label3);
		labelDay1f.add(label4);
		labelDay1c.add(label5);
		labelDay1f.add(label6);
		labelDay1c.add(label7);
		labelDay1f.add(label8);
		
		labelDay2c.add(label9);
		labelDay2f.add(label10);
		labelDay2c.add(label11);
		labelDay2f.add(label12);
		
		labelDay3c.add(label13);
		labelDay3f.add(label14);
				
		*/
	}
	
	
	
	
	public void backButtonClicked(ActionEvent event) throws IOException {
		StageLoader SLB = new StageLoader();
		SLB.show("clientserver/client/fan/gui/view/FanMenu.fxml", "Fan menu", event);
		/*
		FXMLLoader loader = new FXMLLoader();
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
