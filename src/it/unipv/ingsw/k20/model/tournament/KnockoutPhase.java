package it.unipv.ingsw.k20.model.tournament;

import it.unipv.ingsw.k20.model.element.Board;

public class KnockoutPhase extends Tournament {	
	
	private Board board;
	
	public KnockoutPhase(String name) {
		super(name);
		this.board=null;
	}
	
	public Board getBoard() {
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
		return null;
	}
}
