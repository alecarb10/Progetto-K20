package interfaces.fan.gui.util;

import java.sql.SQLException;
import java.util.List;
import domain.element.Day;
import domain.match.Match;
import domain.tournament.Tournament;
import domain.tournament.TournamentType;
import javafx.scene.control.Label;
import services.persistence.dao.impl.FacadeImpl;

public class TournamentUtil {
	public static void populateBrackets(List<Day> days, int nDay, int nM, List<Label> labels) {
		int idx = 0;
		if (days.size() > nM) {
			Day day = days.get(nDay);
			if (day.isCompleted()) {
				for (Match match : day.getMatchesList()) {
					labels.get(idx).setText(match.getHomeTeam().getName() + "    " + match.getHomeScore());
					idx++;
					labels.get(idx).setText(match.getAwayTeam().getName() + "    " + match.getAwayScore());
					idx++;
				}
			}
			if(day.isCompleted() == false && day != days.get(0) && days.get(nDay-1).isCompleted()) {
				for (Match match : day.getMatchesList()) {
					labels.get(idx).setText(match.getHomeTeam().getName());
					idx++;
					labels.get(idx).setText(match.getAwayTeam().getName());
					idx++;
				}	
			}
			if(day.isCompleted() == false && day == days.get(0)) {
				for (Match match : day.getMatchesList()) {
					labels.get(idx).setText(match.getHomeTeam().getName());
					idx++;
					labels.get(idx).setText(match.getAwayTeam().getName());
					idx++;
				}	
				
			}

		}
	}
	
	
}
