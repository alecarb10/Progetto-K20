package it.unipv.ingsw.k20.model.element;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;
import it.unipv.ingsw.k20.model.team.Team;

public class Group {

	private List<Team> teamsList;
	private List<Day> schedule;

	public Group(List<Team> teamsList) {
		this.teamsList = teamsList;
	}

	public void initGroup(int maxDays) throws OddTeamsSizeException {
		schedule = ScheduleGenerator.getInstance().generateSchedule(teamsList, maxDays);
		// ...
	}

	public List<Team> getRanking() {
		List<Team> ranking = teamsList;
		
		Collections.sort(ranking, new Comparator<Team>() {
			@Override
			public int compare(Team t1, Team t2) {

				// if(t1.getPoints() > t2.getPoints())
				// return -1;
				//
				// else if (t.getPoints() < t1.getPoints())
				// return 1;

				return 0;
			}
		});
		
		return ranking;
	}

	public List<Team> getTeamsList() {
		return teamsList;
	}

	public Day getDayByNumber(int number) {
		Day day = null;
		
		for (Day d: schedule)
			if (d.getNumber() == number)
				day = d;
		
		return day;
	}
	
	public Day getDayByDate(Date date) {
		Day day = null;
		
		for (Day d: schedule)
			if (d.getDate().equals(date))
				day = d;
		
		return day;
	}
	
	public List<Day> getSchedule() {
		return schedule;
	}

	@Override
	public String toString() {
		List<Team> ranking = getRanking();
		StringBuilder sb = new StringBuilder();
		
		sb.append("Team Name" + "\t" + " Points" + "\n");
		for (Team team : ranking) {
			sb.append(team);
			// sb.append(Team.getName() + "\t" + team.getPoints());
		}

		return sb.toString();
	}
}
