package mvc.model.tournament;

import java.util.List;

import mvc.model.team.Team;

public abstract class Tournament implements ITournament {
	
	private int id;
	private String name;

	public Tournament(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if (!name.isBlank())
			this.name = name;
	}
	
	public void addTeams(List<Team> teamsList) {
		for (Team t: teamsList)
			addTeamInTournament(t);
	}
	
	@Override
	public String toString() {
		return String.format("Tournament name: %s\n", this.name);
	}
}
