package it.unipv.ingsw.k20.model.element;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.unipv.ingsw.k20.model.team.Team;

public abstract class TournamentElement implements iElement {

	protected List<Team> teamsList;
	protected List<Day> schedule;
	protected boolean completed;

	public TournamentElement() {
		this.teamsList = new ArrayList<>();
		this.completed = false;
	}

	public List<Team> getTeamsList() {
		return teamsList;
	}

	public boolean addTeam(Team team) {
		return teamsList.add(team);
	}

	public boolean removeTeam(Team team) {
		return teamsList.remove(team);
	}

	public boolean isCompleted() {
		return completed;
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
