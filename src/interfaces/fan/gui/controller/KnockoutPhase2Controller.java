package interfaces.fan.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import domain.tournament.Tournament;
import interfaces.fan.gui.util.StageLoader;
import interfaces.fan.gui.util.TournamentUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import services.persistence.dao.impl.FacadeImpl;

public class KnockoutPhase2Controller implements Initializable {
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
	
	List<Label> labelDay1 = new ArrayList<>();
	FacadeImpl facade = FacadeImpl.getInstance();
	Tournament tournament;
	
	public void passingDataToKnock2(Tournament k2) throws SQLException {
		tournament = k2;
		textBoard.setText(k2.getName());
		k2.setGroupSchedule(facade.getGroupSchedule(k2));
		if(k2.getGroup().isCompleted()) {
			k2.setBoardSchedule(facade.getBoardSchedule(k2));
		}
		
		labelDay1.add(label1);
		labelDay1.add(label2);
		TournamentUtil.populateBrackets(k2.getBoardSchedule(), 0, 0, labelDay1);
		
		if(k2.getBoardSchedule().size() >0 ){
			if(k2.getBoardSchedule().get(0).getMatchesList().get(0).isPlayed()) {
			label3.setText((k2.getBoardSchedule().get(0).getMatchesList().get(0).getWinner().getName()));		
			}
		}
		
		
	}
	
	public void backButtonClicked(ActionEvent event) throws IOException, SQLException {
		StageLoader SLB = new StageLoader();
		SLB.backToFanMenuOrLeague(event, tournament);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	

}
