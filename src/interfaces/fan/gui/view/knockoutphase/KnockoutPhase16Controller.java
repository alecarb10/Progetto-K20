package interfaces.fan.gui.view.knockoutphase;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import domain.element.Day;
import domain.match.Match;
import domain.tournament.KnockoutPhase;
import domain.tournament.Tournament;
import domain.tournament.TournamentType;
import interfaces.fan.gui.view.util.PopulateBrackets;
import interfaces.fan.gui.view.util.StageLoader;
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
import services.persistence.dao.impl.FacadeImpl;

public class KnockoutPhase16Controller implements Initializable {
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
	@FXML
	Label label16;
	@FXML
	Label label17;
	@FXML
	Label label18;
	@FXML
	Label label19;
	@FXML
	Label label20;
	@FXML
	Label label21;
	@FXML
	Label label22;
	@FXML
	Label label23;
	@FXML
	Label label24;
	@FXML
	Label label25;
	@FXML
	Label label26;
	@FXML
	Label label27;
	@FXML
	Label label28;
	@FXML
	Label label29;
	@FXML
	Label label30;
	@FXML
	Label label31;
	FacadeImpl facade = FacadeImpl.getInstance();
	List<Day> days;
	List<Label> labelDay1 = new ArrayList<>();
	List<Label> labelDay2 = new ArrayList<>();	
	List<Label> labelDay3 = new ArrayList<>();
	List<Label> labelDay4 = new ArrayList<>();
	public void passingDataToKnock16(Tournament k16) throws SQLException {
		textBoard.setText(k16.getName());
		days = facade.getBoardSchedule(k16);
		PopulateBrackets PB = new PopulateBrackets();

		labelDay1.add(label1);
		labelDay1.add(label2);
		labelDay1.add(label3);
		labelDay1.add(label4);
		labelDay1.add(label5);
		labelDay1.add(label6);
		labelDay1.add(label7);
		labelDay1.add(label8);
		labelDay1.add(label9);
		labelDay1.add(label10);
		labelDay1.add(label11);
		labelDay1.add(label12);
		labelDay1.add(label13);
		labelDay1.add(label14);
		labelDay1.add(label15);
		labelDay1.add(label16);
		
		PB.populate(days, 0, 0, labelDay1);
		/*int idx1 = 0;
		Day day1 = days.get(0);
		if(day1.isCompleted()) {
			for(Match match: day1.getMatchesList()) {
				labelDay1.get(idx1).setText(match.getHomeTeam().getName() +"    " + match.getHomeScore());
				idx1++;
				labelDay1.get(idx1).setText(match.getAwayTeam().getName() +"    "+ match.getAwayScore());
				idx1++;
			}	
		}
		*/
		
		labelDay2.add(label17);
		labelDay2.add(label18);
		labelDay2.add(label19);
		labelDay2.add(label20);
		labelDay2.add(label21);
		labelDay2.add(label22);
		labelDay2.add(label23);
		labelDay2.add(label24);
		PB.populate(days, 1, 1, labelDay2);
		/*
		int idx2 = 0;
		if(days.size() > 1) {
		Day day2 = days.get(1);
		if(day2.isCompleted()) {
			for(Match match: day2.getMatchesList()) {
				labelDay2.get(idx2).setText(match.getHomeTeam().getName() +"    " + match.getHomeScore());
				idx2++;
				labelDay2.get(idx2).setText(match.getAwayTeam().getName() +"    "+ match.getAwayScore());
				idx2++;
				}
			
			}
		}
		*/

		labelDay3.add(label25);
		labelDay3.add(label26);
		labelDay3.add(label27);
		labelDay3.add(label28);
		PB.populate(days, 2, 2, labelDay3);
		/*
		int idx3 = 0;
		if(days.size() > 2) {
		Day day3 = days.get(2);
		if(day3.isCompleted()) {
			for(Match match: day3.getMatchesList()) {
				labelDay3.get(idx3).setText(match.getHomeTeam().getName() +"    " + match.getHomeScore());
				idx3++;
				labelDay3.get(idx3).setText(match.getAwayTeam().getName() +"    "+ match.getAwayScore());
				idx3++;
				}
			}
		}
		*/
		
		labelDay4.add(label29);
		labelDay4.add(label30);
		PB.populate(days, 3, 3, labelDay4);
		if(days.size() > 3){
			if(days.get(3).getMatchesList().get(0).isPlayed()) {
			label31.setText((days.get(3).getMatchesList().get(0).getWinner().getName()));		
			}
		}
		/*
		int idx4 = 0;
		if(days.size() > 3) {
		Day day4 = days.get(3);
		if(day4.isCompleted()) {
			for(Match match: day4.getMatchesList()) {
				labelDay4.get(idx4).setText(match.getHomeTeam().getName() +"    " + match.getHomeScore());
				idx4++;
				labelDay4.get(idx4).setText(match.getAwayTeam().getName() +"    "+ match.getAwayScore());
				idx4++;
				}
			label31.setText((days.get(3).getMatchesList().get(0).getWinner().getName()));
			}
		
		}
		*/
		
		
		
		
//		List<Label> labelsList= new ArrayList<>();
//		labelsList.add(label1);
//		labelsList.add(label2);
//		labelsList.add(label3);
//		labelsList.add(label4);		
//		labelsList.add(label5);
//		labelsList.add(label6);
//		labelsList.add(label7);
//		labelsList.add(label8);
//		labelsList.add(label9);
//		labelsList.add(label10);
//		labelsList.add(label11);
//		labelsList.add(label12);
//		labelsList.add(label13);
//		labelsList.add(label14);
//		labelsList.add(label15);
//		labelsList.add(label16);
//		labelsList.add(label17);
//		labelsList.add(label18);
//		labelsList.add(label19);
//		labelsList.add(label20);
//		labelsList.add(label21);
//		labelsList.add(label22);
//		labelsList.add(label23);
//		labelsList.add(label24);
//		labelsList.add(label25);
//		labelsList.add(label26);
//		labelsList.add(label27);
//		labelsList.add(label28);
//		labelsList.add(label29);
//		labelsList.add(label30);
//		labelsList.add(label31);
//		
//		for(int i=0,j=0,k=0;i<labelsList.size();i++) { //i=label,j=day,k=match		
//			final double SIXTEEN=Math.pow(2, 4); 
//			final double EIGHT=Math.pow(2, 3);
//			if(i==SIXTEEN||i==SIXTEEN+EIGHT||i==SIXTEEN+EIGHT+Math.pow(2, 2)) {
//				k=0;
//				j++;
//			}
//			Match match=null;
//			if(i==labelsList.size()-1) {
//				k=0;
//				match=days.get(j).getMatchesList().get(k);
//				labelsList.get(i).setText(match.getWinner().getName());
//			}
//			else {
//				match=days.get(j).getMatchesList().get(k);
//				if(i%2==0)
//					labelsList.get(i).setText(match.getHomeTeam().getName() +"            "+ match.getHomeScore());
//				else {
//					labelsList.get(i).setText(match.getAwayTeam().getName() +"            "+ match.getAwayScore());
//					k++;
//				}
//			}
//		}
/*
		label1.setText(days.get(0).getMatchesList().get(0).getHomeTeam().getName() +"            "+ days.get(0).getMatchesList().get(0).getHomeScore());
		label2.setText(days.get(0).getMatchesList().get(0).getAwayTeam().getName() +"            " +days.get(0).getMatchesList().get(0).getAwayScore());
		
		label3.setText(days.get(0).getMatchesList().get(1).getHomeTeam().getName() +"            "+ days.get(0).getMatchesList().get(1).getHomeScore());
		label4.setText(days.get(0).getMatchesList().get(1).getAwayTeam().getName() +"            " +days.get(0).getMatchesList().get(1).getAwayScore());
		
		label5.setText(days.get(0).getMatchesList().get(2).getHomeTeam().getName() +"            "+ days.get(0).getMatchesList().get(2).getHomeScore());
		label6.setText(days.get(0).getMatchesList().get(2).getAwayTeam().getName() +"            " +days.get(0).getMatchesList().get(2).getAwayScore());
		
		label7.setText(days.get(0).getMatchesList().get(3).getHomeTeam().getName() +"            "+ days.get(0).getMatchesList().get(3).getHomeScore());
		label8.setText(days.get(0).getMatchesList().get(3).getAwayTeam().getName() +"            " +days.get(0).getMatchesList().get(3).getAwayScore());
		
		label9.setText(days.get(0).getMatchesList().get(4).getHomeTeam().getName() +"            "+ days.get(0).getMatchesList().get(4).getHomeScore());
		label10.setText(days.get(0).getMatchesList().get(4).getAwayTeam().getName() +"            " +days.get(0).getMatchesList().get(4).getAwayScore());
		
		label11.setText(days.get(0).getMatchesList().get(5).getHomeTeam().getName() +"            "+ days.get(0).getMatchesList().get(5).getHomeScore());
		label12.setText(days.get(0).getMatchesList().get(5).getAwayTeam().getName() +"            " +days.get(0).getMatchesList().get(5).getAwayScore());
		
		label13.setText(days.get(0).getMatchesList().get(6).getHomeTeam().getName() +"            "+ days.get(0).getMatchesList().get(6).getHomeScore());
		label14.setText(days.get(0).getMatchesList().get(6).getAwayTeam().getName() +"            " +days.get(0).getMatchesList().get(6).getAwayScore());
		
		label15.setText(days.get(0).getMatchesList().get(7).getHomeTeam().getName() +"            "+ days.get(0).getMatchesList().get(7).getHomeScore());
		label16.setText(days.get(0).getMatchesList().get(7).getAwayTeam().getName() +"            " +days.get(0).getMatchesList().get(7).getAwayScore());
	*/	
		/*
			
			label17.setText(days.get(1).getMatchesList().get(0).getHomeTeam().getName() +"            "+ days.get(1).getMatchesList().get(0).getHomeScore());
			label18.setText(days.get(1).getMatchesList().get(0).getAwayTeam().getName() +"            " +days.get(1).getMatchesList().get(0).getAwayScore());
			
			label19.setText(days.get(1).getMatchesList().get(1).getHomeTeam().getName() +"            "+ days.get(1).getMatchesList().get(1).getHomeScore());
			label20.setText(days.get(1).getMatchesList().get(1).getAwayTeam().getName() +"            " +days.get(1).getMatchesList().get(1).getAwayScore());
			
			label21.setText(days.get(1).getMatchesList().get(2).getHomeTeam().getName() +"            "+ days.get(1).getMatchesList().get(2).getHomeScore());
			label22.setText(days.get(1).getMatchesList().get(2).getAwayTeam().getName() +"            " +days.get(1).getMatchesList().get(2).getAwayScore());
			
			label23.setText(days.get(1).getMatchesList().get(3).getHomeTeam().getName() +"            "+ days.get(1).getMatchesList().get(3).getHomeScore());
			label24.setText(days.get(1).getMatchesList().get(3).getAwayTeam().getName() +"            " +days.get(1).getMatchesList().get(3).getAwayScore());
			
			
			label25.setText(days.get(2).getMatchesList().get(0).getHomeTeam().getName() +"            "+ days.get(2).getMatchesList().get(0).getHomeScore());
			label26.setText(days.get(2).getMatchesList().get(0).getAwayTeam().getName() +"            " +days.get(2).getMatchesList().get(0).getAwayScore());
			
			label27.setText(days.get(2).getMatchesList().get(1).getHomeTeam().getName() +"            "+ days.get(2).getMatchesList().get(1).getHomeScore());
			label28.setText(days.get(2).getMatchesList().get(1).getAwayTeam().getName() +"            " +days.get(2).getMatchesList().get(1).getAwayScore());
			

			label29.setText(days.get(3).getMatchesList().get(0).getHomeTeam().getName() +"            "+ days.get(3).getMatchesList().get(0).getHomeScore());
			label30.setText(days.get(3).getMatchesList().get(0).getAwayTeam().getName() +"            " +days.get(3).getMatchesList().get(0).getAwayScore());
			
			label31.setText(days.get(3).getMatchesList().get(0).getWinner().getName());

		*/
		
		
	}

	
	
	
	
	public void backButtonClicked(ActionEvent event) throws IOException {
		StageLoader SLB = new StageLoader();
		SLB.show("interfaces/fan/gui/view/FanMenu.fxml", "Fan menu", event);
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	
	
}
