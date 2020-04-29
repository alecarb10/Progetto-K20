package it.unipv.ingsw.k20.model.tournament;

import it.unipv.ingsw.k20.model.element.Board;
import it.unipv.ingsw.k20.model.element.TournamentElement;
import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;

public class KnockoutPhase extends Tournament {	
	
	private TournamentElement board;
	
	public KnockoutPhase(String name) {
		super(name);
		this.board=null;
	}
	
	public TournamentElement getBoard() {
		return this.board;
	}
	
	public void setBoard(Board board) {
		if(board!=null)
			this.board=board;
	}
	
	@Override
	public TournamentType getTournamentType() {
		return TournamentType.KNOCKOUT_PHASE;
	}
	
	@Override
	public String toString() {
		return super.toString()+String.format("Tournament type: %s\n%s", this.getTournamentType(), this.board.toString());
	}
	
	@Override
	public void initTournament(int maxDays) {
		try {
			this.board.initTournamentElement(maxDays);
		}
		catch(OddTeamsSizeException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
