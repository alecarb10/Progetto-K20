package it.unipv.ingsw.k20.model.element;

import java.util.List;

import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;
import it.unipv.ingsw.k20.model.team.Team;
import it.unipv.ingsw.k20.model.util.ScheduleGenerator;

public class Board extends TournamentElement {

	public Board(List<Team> teamsList) {
		super(teamsList);
	}

	@Override
	public void initTournamentElement(int maxDays) throws OddTeamsSizeException {
		schedule = ScheduleGenerator.getInstance().generateSchedule(teamsList, maxDays);
		// ...
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
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("Team Name" + "\n");
		for (Team team : teamsList) {
			sb.append(team);
		}

		return sb.toString();
	}
}
