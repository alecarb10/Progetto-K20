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

public class KnockoutPhase4Controller implements Initializable {
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
	
	List<Label> labelDay1 = new ArrayList<>();
	List<Label> labelDay2 = new ArrayList<>();	
	FacadeImpl facade = FacadeImpl.getInstance();
	List<Day> days;

	public void passingDataToKnock4(Tournament k4) throws SQLException {
		textBoard.setText(k4.getName());
		days = facade.getBoardSchedule(k4);
		labelDay1.add(label1);
		labelDay1.add(label2);
		labelDay1.add(label3);
		labelDay1.add(label4);
		PopulateBrackets.populate(days, 0, 0, labelDay1);
		
		labelDay2.add(label5);
		labelDay2.add(label6);
		PopulateBrackets.populate(days, 1, 1, labelDay2);
		
		if(days.size() >1 ){
			if(days.get(1).getMatchesList().get(0).isPlayed()) {
			label7.setText((days.get(1).getMatchesList().get(0).getWinner().getName()));		
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
