package mvc.model.tournament;

import java.util.List;

import mvc.model.team.Team;

public abstract class Tournament implements ITournament {
	
	private static int n = 1;
	private int tournamentID;
	private String name;

	public Tournament(String name, List<Team> teamsList) {
		this.tournamentID = n;
		this.name = name;
		n++;
	}

	public int getTournamentID() {
		return this.tournamentID;
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

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		Tournament tournament = (Tournament) obj;
		return tournament.tournamentID == this.tournamentID;
	}

	@Override
	public int hashCode() {
		return ((Integer) this.tournamentID).hashCode();
	}
}
