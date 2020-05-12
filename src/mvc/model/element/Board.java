package mvc.model.element;

import java.util.List;

import mvc.model.team.Team;
import mvc.model.util.ScheduleGenerator;

public class Board extends TournamentElement {

	public Board(String name) {super(name);}

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
		// decreta vincitore...
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
}
