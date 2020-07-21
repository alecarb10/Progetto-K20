package interfaces.fan.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import domain.element.Day;
import domain.tournament.Tournament;
import domain.tournament.TournamentType;
import interfaces.fan.gui.util.StageLoader;
import interfaces.fan.gui.util.TournamentUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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
	Tournament tournament;

	public void passingDataToKnock4(Tournament k4) {
		tournament = k4;

		textBoard.setText(k4.getName());
		if (k4.getTournamentType() == TournamentType.MIXED) {
			try {
				k4.setGroupSchedule(facade.getGroupSchedule(k4));
			} catch (SQLException e) {
				new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
			}
			if (k4.getGroup().isCompleted()) {
				try {
					k4.setBoardSchedule(facade.getBoardSchedule(k4));
				} catch (SQLException e) {
					new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
				}
			}
		}

		if (k4.getTournamentType() == TournamentType.KNOCKOUT_PHASE) {
			try {
				k4.setBoardSchedule(FacadeImpl.getInstance().getBoardSchedule(k4));
			} catch (SQLException e) {
				new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK).show();
			}
		}

		labelDay1.add(label1);
		labelDay1.add(label2);
		labelDay1.add(label3);
		labelDay1.add(label4);
		TournamentUtil.populateBrackets(k4.getBoardSchedule(), 0, 0, labelDay1);

		labelDay2.add(label5);
		labelDay2.add(label6);
		TournamentUtil.populateBrackets(k4.getBoardSchedule(), 1, 1, labelDay2);

		if (k4.getBoardSchedule().size() > 1) {
			if (k4.getBoardSchedule().get(1).getMatchesList().get(0).isPlayed()) {
				label7.setText((k4.getBoardSchedule().get(1).getMatchesList().get(0).getWinner().getName()));
			}
		}

	}

	public void backButtonClicked(ActionEvent event) {
		StageLoader SLB = new StageLoader();
		SLB.backToFanMenuOrLeague(event, tournament);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
