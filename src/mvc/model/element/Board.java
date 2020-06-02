package mvc.model.element;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mvc.model.team.Team;
import mvc.model.match.Match;
import mvc.model.util.ScheduleGenerator;

public class Board extends TournamentElement {

	public Board() {}

	@Override
	public void initTournamentElement() {
		schedule = ScheduleGenerator.getInstance().generateSchedule(teamsList, 1);
	}
	
	@Override
	public List<Team> getRanking() {
		List<Team> ranking = teamsList;
		
		// prendo le squadre che si trovano nell'indice pari (chi passa il turno)
		for (int i = 0; i < teamsList.size(); i++)
			if (i % 2 == 0)
				ranking.add(teamsList.get(i));
		
		return ranking;
	}
	
	@Override
	public void endTournamentElement() {
		completed = true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Team Name" + "\n");
		for (Team team : teamsList) {
			sb.append(team + "\n");
		}

		return sb.toString();
	}

	@Override
	public ElementType getTournamentElementType() {
		return ElementType.BOARD;
	}
	
	public boolean addNextDay() {
		int prevDayNumber = schedule.size() - 1;
		Day prevDay = schedule.get(prevDayNumber);
		Date date = incrementDate(prevDay.getDate()).getTime();
		
		if (prevDay.isCompleted()) {
			List<Team> prevDayWinnersList = getDayWinnersList(prevDay);			
			List<Match> matchesList = new ArrayList<>();
			
			for (int i = 0; i < prevDayWinnersList.size(); i += 2)
				matchesList.add(new Match(date, prevDayWinnersList.get(i), prevDayWinnersList.get(i+1)));
			
			Day nextDay = new Day(prevDayNumber + 1, matchesList, date);
			return schedule.add(nextDay);		
		}
		return false;
	}
	
	private Calendar incrementDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 7);
		return calendar;
	}
	
	private List<Team> getDayWinnersList(Day day){
		List<Match> matchesList=day.getMatchesList();
		List<Team> dayWinnersList=new ArrayList<>();
		for(Match m: matchesList)
			if(m.isPlayed())
				dayWinnersList.add(m.getWinner());
		return dayWinnersList;
	}
}
