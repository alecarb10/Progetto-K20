package it.unipv.ingsw.k20.model.tournament;

import it.unipv.ingsw.k20.model.match.Match;

public abstract class Tournament implements ITournament {
	private static int n = 1;
	private int tournamentID;
	private String name;

	public Tournament(String name) {
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

	public void insertScore(Match m, int homeScore, int awayScore) {
		m.setScore(homeScore, awayScore);
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
