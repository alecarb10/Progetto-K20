package mvc.model.element;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import mvc.model.match.Match;
import mvc.model.team.Team;

public abstract class TournamentElement implements IElement {

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
		for (Day d: schedule)
			for(Match m: d.getMatchesList())
				if(m.isPlayed())
					completed = true;
				else
					completed = false;
		
		return completed;
	}
	
	public List<Day> getSchedule() {
		return schedule;
	}
	
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore) {
		Day day = getDayByNumber(dayNumber);
		
		for (Match m: day.getMatchesList())
			if (m.equals(match)) {
				m.setScore(homeScore, awayScore);
				return true;
			}
		
		return false;
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
