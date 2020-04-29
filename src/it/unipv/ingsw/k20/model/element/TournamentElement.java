package it.unipv.ingsw.k20.model.element;

import java.util.Date;
import java.util.List;

import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;
import it.unipv.ingsw.k20.model.team.Team;

public abstract class TournamentElement {

	protected List<Team> teamsList;
	protected List<Day> schedule;

	public TournamentElement(List<Team> teamsList) {
		this.teamsList = teamsList;
	}
	
	public abstract List<Team> getRanking();
	
	public abstract void initTournamentElement(int maxDays) throws OddTeamsSizeException;

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
}
