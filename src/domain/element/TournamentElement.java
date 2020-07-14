package domain.element;

import java.util.ArrayList;
import java.util.List;

import domain.match.Match;
import domain.team.Team;

/**
 * Tournament Element class.
 * 
 */
public abstract class TournamentElement implements IElement {

	private int id;
	protected List<Team> teamsList;
	protected List<Day> schedule;
	protected boolean completed;
	
	/**
	 * Tournament Element constructor, that create a list of teams subscribed to the tournament.
	 * 
	 */
	public TournamentElement() {
		this.teamsList = new ArrayList<>();
		this.completed = false;
	}

	public boolean addTeam(Team team) {
		return teamsList.add(team);
	}

	public boolean removeTeam(Team team) {
		return teamsList.remove(team);
	}

	/**
	 * This method returns true if each day of the element's schedule has been played.
	 * 
	 */
	public boolean isCompleted() {
		int counter = 0;
		
		for (Day d: schedule)
			if (d.isCompleted())
				counter++;
		
		completed = counter == schedule.size() ? true : false;
		return completed;
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
		
		if (dayNumber >= 2 && !schedule.get(dayNumber-2).isCompleted())
			return false;
		
		for (Match m: schedule.get(index).getMatchesList())
			if (m.equals(match) && !m.isPlayed()) {
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
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public void setSchedule(List<Day> schedule) {
		this.schedule = schedule;
	}
	
	public List<Day> getSchedule() {
		return schedule;
	}
	
	public List<Team> getTeamsList() {
		return teamsList;
	}
}
