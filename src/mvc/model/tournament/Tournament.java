package mvc.model.tournament;

import java.util.List;

import mvc.model.team.Team;

public abstract class Tournament implements ITournament {
	
	private String name;

	public Tournament(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if (!name.isBlank())
			this.name = name;
	}
	
	protected void addTeams(List<Team> teamsList) {
		for (Team t: teamsList)
			addTeamInTournament(t);
	}

	@Override
	public String toString() {
		return String.format("Tournament name: %s\n", this.name);
	}
}
