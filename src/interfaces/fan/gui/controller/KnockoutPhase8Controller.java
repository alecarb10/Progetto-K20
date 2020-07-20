package interfaces.fan.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import domain.element.Day;
import domain.tournament.Tournament;
import interfaces.fan.gui.util.PopulateBrackets;
import interfaces.fan.gui.util.StageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import services.persistence.dao.impl.FacadeImpl;

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
	

	List<Label> labelDay1 = new ArrayList<>();
	List<Label> labelDay2 = new ArrayList<>();	
	List<Label> labelDay3 = new ArrayList<>();
	
	
	FacadeImpl facade = FacadeImpl.getInstance();
	List<Day> days;
	
	public void passingDataToKnock8(Tournament k8) throws SQLException {
		textBoard.setText(k8.getName());
		days = facade.getBoardSchedule(k8);
		
		labelDay1.add(label1);
		labelDay1.add(label2);
		labelDay1.add(label3);
		labelDay1.add(label4);
		labelDay1.add(label5);
		labelDay1.add(label6);
		labelDay1.add(label7);
		labelDay1.add(label8);
		PopulateBrackets.populate(days, 0, 0, labelDay1);
		
		labelDay2.add(label9);
		labelDay2.add(label10);
		labelDay2.add(label11);
		labelDay2.add(label12);
		PopulateBrackets.populate(days, 1, 1, labelDay2);

		labelDay3.add(label13);
		labelDay3.add(label14);
		PopulateBrackets.populate(days, 2, 2, labelDay3);
		
		if(days.size() >2 ){
			if(days.get(2).getMatchesList().get(0).isPlayed()) {
			label15.setText((days.get(2).getMatchesList().get(0).getWinner().getName()));		
			}
		}

	}
	
	public void backButtonClicked(ActionEvent event) throws IOException {
		StageLoader SLB = new StageLoader();
		SLB.show("interfaces/fan/gui/resources/FanMenu.fxml", "Fan menu", event);
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
