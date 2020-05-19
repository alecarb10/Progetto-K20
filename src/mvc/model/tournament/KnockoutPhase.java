package mvc.model.tournament;

import java.util.List;

import mvc.model.element.Board;
import mvc.model.element.Day;
import mvc.model.element.TournamentElement;
import mvc.model.match.Match;
import mvc.model.team.Team;

public class KnockoutPhase extends Tournament {

	private TournamentElement board;

	public KnockoutPhase(String name) {
		super(name);
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
	public boolean insertScore(int dayNumber, Match match, int homeScore, int awayScore) {
		return this.board.insertScore(dayNumber, match, homeScore, awayScore);
	}

	@Override
	public List<Day> getSchedule() {
		return this.board.getSchedule();
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
	public TournamentElement getTournamentElement() {
		return board;
	}
}
