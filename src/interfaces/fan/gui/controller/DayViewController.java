package interfaces.fan.gui.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import domain.element.Day;
import domain.match.Match;
import domain.tournament.Tournament;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import services.persistence.dao.impl.FacadeImpl;

/**
 * Controller to see the matches in a day
 * @see Initializable
 *@see Day
 *@see Match
 */
public class DayViewController implements Initializable {
	@FXML
	private Text text;
	@FXML
	private ListView<String> matchPlayed;
	@FXML
	private ListView<String> matchNotPlayed;
	FacadeImpl facade = FacadeImpl.getInstance();

	public void passingDataToDay(Tournament tournamentpass, Day dayPass)  {
		text.setText("DAY " + dayPass.getNumber());
		try {
			for (Day day : facade.getGroupSchedule(tournamentpass)) {
				if (day.getId() == dayPass.getId()) {
					for (Match match : day.getMatchesList()) {
						if (match.isPlayed()) {
							matchPlayed.getItems().add(match.toString());
						} else if (match.isPlayed() == false) {
							matchNotPlayed.getItems().add(match.toString());
						}

					}
				}

			}
		} catch (SQLException e) {
			new Alert(AlertType.ERROR,e.getMessage(),ButtonType.OK).show();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
