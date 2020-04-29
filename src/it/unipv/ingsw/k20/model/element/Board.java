package it.unipv.ingsw.k20.model.element;

import java.util.Date;
import java.util.List;

import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;
import it.unipv.ingsw.k20.model.team.Team;

public class Board {

	private List<Team> teamsList;
	private List<Day> schedule;

	public Board(List<Team> teamsList) {
		this.teamsList = teamsList;
	}

	public void initBoard(int maxDays) throws OddTeamsSizeException {
		schedule = ScheduleGenerator.getInstance().generateSchedule(teamsList, maxDays);
		// ...
	}

	public List<Team> getTeamsList() {
		return teamsList;
	}

	public List<Day> getSchedule() {
		return schedule;
	}

	public Day getDayByNumber(int number) {
		Day day = null;

		for (Day d : schedule)
			if (d.getNumber() == number)
				day = d;

		return day;
	}

	public Day getDayByDate(Date date) {
		Day day = null;

		for (Day d : schedule)
			if (d.getDate().equals(date))
				day = d;

		return day;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Team Name" + "\n");
		for (Team team : teamsList) {
			sb.append(team);
		}

		return sb.toString();
	}
}
