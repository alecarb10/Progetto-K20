package it.unipv.ingsw.k20.model.tournament;

import it.unipv.ingsw.k20.model.team.Team;

public interface ITournament {

	public TournamentType getTournamentType();

	public void initTournament(int maxDays);

	public boolean addTeamInTournament(Team t);

	public boolean removeTeamFromTournament(Team t);
}
