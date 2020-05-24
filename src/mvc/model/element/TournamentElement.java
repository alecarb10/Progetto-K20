package mvc.model.element;

import java.util.ArrayList;
import java.util.List;

import mvc.model.match.Match;
import mvc.model.team.Team;

public abstract class TournamentElement implements IElement {

	private int id;
	protected List<Team> teamsList;
	protected List<Day> schedule;
	protected boolean completed;
	
	public TournamentElement() {
		this.teamsList = new ArrayList<>();
		this.completed = false;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
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
	
	public void setSchedule(List<Day> schedule) {
		this.schedule = schedule;
	}
	
	public List<Day> getSchedule() {
		return schedule;
	}
	
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore) {
		int index = 0;
		
		for (int i = 0; i < schedule.size(); i++)
			if (schedule.get(i).getNumber() == dayNumber) {
				index = i;
				break;
			}	
			else
				index = -1;
		
		if (index < 0)
			return false;
		
		for (Match m: schedule.get(index).getMatchesList())
			if (m.equals(match)) {
				m.setScore(homeScore, awayScore);
				return true;
			}
		
		return false;
	}

	public Day getDayByNumber(int number) {
		Day day = null;

		for (Day d: schedule)
			if (d.getNumber() == number) {
				day = d;
				break;
			}
		
		return day;
	}
}
