package interfaces.fan.gui.util;

import java.util.List;
import domain.element.Day;
import domain.match.Match;
import javafx.scene.control.Label;

public class PopulateBrackets {
	public static void populate(List<Day> days, int nDay, int nM, List<Label> labels) {
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

		}
	}
	
	

	
	
}
