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

		labelDay2.add(label17);
		labelDay2.add(label18);
		labelDay2.add(label19);
		labelDay2.add(label20);
		labelDay2.add(label21);
		labelDay2.add(label22);
		labelDay2.add(label23);
		labelDay2.add(label24);
		PB.populate(days, 1, 1, labelDay2);

		labelDay3.add(label25);
		labelDay3.add(label26);
		labelDay3.add(label27);
		labelDay3.add(label28);
		PB.populate(days, 2, 2, labelDay3);

		labelDay4.add(label29);
		labelDay4.add(label30);
		PB.populate(days, 3, 3, labelDay4);
		
		if(days.size() > 3){
			if(days.get(3).getMatchesList().get(0).isPlayed()) {
			label31.setText((days.get(3).getMatchesList().get(0).getWinner().getName()));		
			}
		}
		
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
