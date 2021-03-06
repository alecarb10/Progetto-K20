package domain.util;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import domain.element.Day;
import domain.match.Match;
import domain.team.Team;

/**
 * Singleton class.
 * A Pure Fabrication class to generate a tournament's schedule using Berger's Algorithm.
 */
public class ScheduleGenerator {

	private static ScheduleGenerator scheduleGenerator;

	private ScheduleGenerator() {
	}

	public static synchronized ScheduleGenerator getInstance() {
		if (scheduleGenerator == null)
			scheduleGenerator = new ScheduleGenerator();
		return scheduleGenerator;
	}

	/**
	 * Generates the schedule given the teams and days number
	 * @param teamsList list of teams
	 * @param maxDays number of schedule's days
	 * @return a list containing days: schedule
	 */
	public List<Day> generateSchedule(List<Team> teamsList, int maxDays) {
		List<Day> schedule = new ArrayList<>();
		List<Match> matches = new ArrayList<>();
		Calendar calendar = initDate();
		int numTeams = teamsList.size();
		
		Collections.shuffle(teamsList); 	// random shuffle the list
		
		if (teamsList.size() < 2)			// schedule not generated
			return null;
		
		/* Only two teams -> only one match*/
		
		if (teamsList.size() == 2) {
			matches.add(new Match(calendar.getTime(), teamsList.get(0), teamsList.get(1)));
			schedule.add(new Day(1, matches, calendar.getTime()));
			return schedule;
		}

		/* Berger's Algorithm */
		
		List<Team> home = new ArrayList<>(numTeams / 2);
		List<Team> away = new ArrayList<>(numTeams / 2);

		for (int i = 0; i < numTeams / 2; i++) {
			home.add(i, teamsList.get(i));
			away.add(i, teamsList.get(numTeams - 1 - i));
		}

		for (int i = 0; i < maxDays; i++) {
			matches = new ArrayList<>();			
			
			if (i % 2 == 0)
				for (int j = 0; j < numTeams / 2; j++)
					matches.add(new Match(calendar.getTime(), away.get(j), home.get(j)));
			else 
				for (int j = 0; j < numTeams / 2; j++)
					matches.add(new Match(calendar.getTime(), home.get(j), away.get(j)));

			Team pivot = home.get(0);	
			
			Team exceed = away.get(away.size() - 1);
			away.remove(away.size() - 1);
			away = shiftRight(away, home.get(1));
			home.remove(0);
			home = shiftLeft(home, exceed);
			home.add(0, pivot);
			
			schedule.add(new Day(i + 1, matches, calendar.getTime()));
			calendar.add(Calendar.DAY_OF_YEAR, 7);
		}

		return schedule;
	}

	/**
	 * Adds a team into the list and shifts it to the left
	 * @param data list of teams
	 * @param add team to shift
	 * @return the new list of teams
	 */
	private List<Team> shiftLeft(List<Team> data, Team add) {
		List<Team> temp = new ArrayList<>(data.size());
		
		for (int i = 0; i < data.size() - 1; i++)
			temp.add(i, data.get(i + 1));
		
		temp.add(data.size() - 1, add);
		return temp;
	}

	/**
	 * Adds a team into the list and shifts it to the right
	 * @param data list of teams
	 * @param add team to shift
	 * @return the new list of teams
	 */
	private List<Team> shiftRight(List<Team> data, Team add) {
		List<Team> temp = new ArrayList<>(data.size());
		
		temp.add(0, add);
		
		for (int i = 1; i <= data.size(); i++)
			temp.add(i, data.get(i - 1));

		return temp;
	}

	/**
	 * Initialize a date to 3 pm
	 * @return Calendar object containing the date
	 */
	private Calendar initDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 3);

		return calendar;
	}
}
