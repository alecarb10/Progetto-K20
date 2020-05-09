package it.unipv.ingsw.k20.model.tournament;

import java.util.List;

import it.unipv.ingsw.k20.model.element.Day;
import it.unipv.ingsw.k20.model.match.Match;
import it.unipv.ingsw.k20.model.team.Team;

public interface ITournament {

	public TournamentType getTournamentType();

	public void initTournament(List<Team> teamsList);

	public boolean addTeamInTournament(Team t);

	public boolean removeTeamFromTournament(Team t);
	
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore);
	
	public List<Day> getSchedule();
}
