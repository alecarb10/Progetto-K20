package it.unipv.ingsw.k20.model.tournament;

import java.util.List;

import it.unipv.ingsw.k20.model.element.Board;
import it.unipv.ingsw.k20.model.element.Day;
import it.unipv.ingsw.k20.model.element.TournamentElement;
import it.unipv.ingsw.k20.model.match.Match;
import it.unipv.ingsw.k20.model.team.Team;

public class KnockoutPhase extends Tournament {

	private TournamentElement board;

	public KnockoutPhase(String name, List<Team> teamsList) {
		super(name, teamsList);
		initTournament(teamsList);
	}

	public TournamentElement getBoard() {
		return this.board;
	}

	@Override
	public TournamentType getTournamentType() {
		return TournamentType.KNOCKOUT_PHASE;
	}

	@Override
	public String toString() {
		return super.toString()
				+ String.format("Tournament type: %s\n%s", this.getTournamentType(), this.board.toString());
	}

	@Override
	public void initTournament(List<Team> teamsList) {
		this.board = new Board();
		addTeams(teamsList);
		this.board.initTournamentElement();
	}

	@Override
	public boolean addTeamInTournament(Team team) {
		return this.board.addTeam(team);
	}

	@Override
	public boolean removeTeamFromTournament(Team team) {
		return this.board.addTeam(team);
	}

	@Override
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore) {
		return this.board.insertScore(dayNumber, match, homeScore, awayScore);
	}

	@Override
	public List<Day> getSchedule() {
		return this.board.getSchedule();
	}
}
