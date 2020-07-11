package interfaces.fan.gui.view.element;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import domain.element.Day;
import domain.match.Match;
import domain.tournament.Tournament;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import services.persistence.dao.impl.FacadeImpl;

public class dayViewController implements Initializable {
	@FXML
	private Text text;
	@FXML
	private ListView<String> matchPlayed;
	@FXML
	private ListView<String> matchNotPlayed;
	FacadeImpl facade = FacadeImpl.getInstance();

	
	
	public void passingDataToDay(Tournament tournamentpass, Day dayPass) throws SQLException {
		text.setText("DAY " + dayPass.getNumber());
		for(Day day : facade.getGroupSchedule(tournamentpass)) {
			if(day.getId() == dayPass.getId()) {
				for(Match match : day.getMatchesList()) {
					if(match.isPlayed()) {
						matchPlayed.getItems().add(match.toString());
					}
					else if (match.isPlayed() == false){
						matchNotPlayed.getItems().add(match.toString());
					}
						
				}
			}
			
			
		}
		
	}
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
