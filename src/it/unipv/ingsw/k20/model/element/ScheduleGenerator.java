package it.unipv.ingsw.k20.model.element;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;
import it.unipv.ingsw.k20.model.match.Match;
import it.unipv.ingsw.k20.model.team.Team;

public class ScheduleGenerator {

	private static ScheduleGenerator scheduleGenerator;
	
	private List<Day> schedule;

	private ScheduleGenerator() {
		schedule = new ArrayList<>();
	}

	public static synchronized ScheduleGenerator getInstance() {
		if (scheduleGenerator == null)
			scheduleGenerator = new ScheduleGenerator();
		return scheduleGenerator;
	}

	// maxDays = numero massimo di giornate
	public List<Day> generateSchedule(List<Team> teamsList, int maxDays) throws OddTeamsSizeException {
		
		List<Team> teams; 								// lista di appoggio per estrarre squadre a caso
		List<Match> matches;						 	// partite di un'intera giornata
		List<Team> matchTeams = new ArrayList<>(); 		// due squadre a caso per ogni match
		Random random = new Random();
		Calendar calendar = initDate();
		
		if (teamsList.size() % 2 != 0)
			throw new OddTeamsSizeException("Teams size must be even");
		
		for (int i = 1; i <= maxDays; i++) {

			matches = new ArrayList<>();
			teams = teamsList;

			// creo ( teamsList.size()/2 ) partite
			for (int j = 0; j < teamsList.size() / 2; j++) {

				// prendo due squadre a caso dalla lista
				for (int k = 0; k < 2; k++) {
					int randomIndex = random.nextInt(teams.size());
					matchTeams.add(teams.get(randomIndex));
					teams.remove(randomIndex);
				}

				// matches.add(new Match(matchTeams.get(0), matchTeams.get(1),
				// calendar.getTime());
			}

			Day day = new Day(i, matches, calendar.getTime());
			schedule.add(day);
			calendar.add(Calendar.DAY_OF_YEAR, 7); // una giornata ogni settimana
		}
		
		return schedule;
	}

	private Calendar initDate() {
		// imposto la data odierna modificando l'ora in 15
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.HOUR, 3);
		
		return calendar;
	}

}
