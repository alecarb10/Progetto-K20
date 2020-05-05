package it.unipv.ingsw.k20.model.tournament;

import it.unipv.ingsw.k20.model.element.Board;
import it.unipv.ingsw.k20.model.element.TournamentElement;
import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;
import it.unipv.ingsw.k20.model.team.Team;

public class KnockoutPhase extends Tournament {

	private TournamentElement board;

	public KnockoutPhase(String name) {
		super(name);
		this.initTournament();
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
		return super.toString() + String.format("Tournament type: %s\n%s", this.getTournamentType(), this.board.toString());
	}

	@Override
	public void initTournament() {
		try {
			this.board = new Board();
			this.board.initTournamentElement();
		} catch (OddTeamsSizeException ex) {
			System.out.println(ex.getMessage());
		}
	}

	@Override
	public boolean addTeamInTournament(Team team) {
		return this.board.addTeam(team);
	}

	@Override
	public boolean removeTeamFromTournament(Team team) {
		return this.board.addTeam(team);
	}
}
