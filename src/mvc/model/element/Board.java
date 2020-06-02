package mvc.model.element;

import java.util.ArrayList;
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
		int prevDayNumber=schedule.size()-1;
		Day prevDay=schedule.get(prevDayNumber);
		if(isDayCompleted(prevDay)) {
			List<Team> prevDayWinnersList=getDayWinnersList(prevDay);			
			List<Match> matchesList=new ArrayList<>();
			for(int i=0;i<prevDayWinnersList.size();i+=2){
				//matchesList.add(new Match(date, winnersList.get(i), winnersList.get(i+1)))
			}
			Day nextDay=null;//= new Day(prevDayNumber+1, matchesList, date);
			return schedule.add(nextDay);
				
		}
		return false;
	}
	
	private boolean isDayCompleted(Day day) {
		int n=0;
		for(Match m:day.getMatchesList())
			if(m.isPlayed()) n++;
		return n==day.getMatchesList().size()?true:false;
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
