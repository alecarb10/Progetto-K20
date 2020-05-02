package it.unipv.ingsw.k20.model.element;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;
import it.unipv.ingsw.k20.model.team.Team;
import it.unipv.ingsw.k20.model.util.ScheduleGenerator;

public class Group extends TournamentElement {

	public Group() {
	}

	@Override
	public void initTournamentElement(int maxDays) throws OddTeamsSizeException {
		schedule = ScheduleGenerator.getInstance().generateSchedule(teamsList, maxDays);
		// ...
	}

	@Override
	public List<Team> getRanking() {
		List<Team> ranking = teamsList;

		Collections.sort(ranking, new Comparator<Team>() {
			@Override
			public int compare(Team t1, Team t2) {

				if (t1.getPoints() > t2.getPoints())
					return -1;

				else if (t1.getPoints() < t2.getPoints())
					return 1;

				else {
					int goalDifference1 = t1.getGoalsScored() - t1.getGoalsConceded();
					int goalDifference2 = t2.getGoalsScored() - t2.getGoalsConceded();

					if (goalDifference1 > goalDifference2)
						return -1;

					else if (goalDifference1 < goalDifference2)
						return 1;

					else
						return 0;
				}
			}
		});

		return ranking;
	}

	@Override
	public void endTournamentElement() {
		completed = true;
		// decreta vincitore...
	}

	@Override
	public String toString() {
		List<Team> ranking = getRanking();
		StringBuilder sb = new StringBuilder();

		sb.append("Team Name" + "\t" + " Points" + "\n");
		for (Team team : ranking) {
			sb.append(team + "\n");
			// sb.append(Team.getName() + "\t" + team.getPoints() + "\n");
		}

		return sb.toString();
	}
}
