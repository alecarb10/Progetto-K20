package it.unipv.ingsw.k20.model.tournament;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.k20.model.element.Board;
import it.unipv.ingsw.k20.model.element.Group;
import it.unipv.ingsw.k20.model.element.TournamentElement;
import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;

public class MixedTournament extends Tournament {
	
	private List<TournamentElement> groupsList;
	private TournamentElement board;
	
	public MixedTournament(String name) {
		super(name);
		this.groupsList=new ArrayList<>();
		this.board=null;
	}
	
	public TournamentElement getBoard() {
		return this.board;
	}
	
	public void setBoard(Board board) {
		if(board!=null)
			this.board=board;
	}
	
	public List<TournamentElement> getGroupsList(){
		return this.groupsList;
	}
	
	public boolean addGroup(Group group)throws NullPointerException{
		return this.groupsList.add(group);
	} 
	
	public boolean removeGroup(Group group)throws NullPointerException{
		return this.groupsList.remove(group);
	}
	
	@Override
	public TournamentType getTournamentType() {
		return TournamentType.MIXED;
	}
	
	@Override
	public String toString() {
		StringBuilder sb= new StringBuilder().append("Gironi\n");
		for(TournamentElement te: this.groupsList)
			sb.append(te).append("\n");
		return super.toString()+String.format("Tournament type: %s\n%s\n%s\n", this.getTournamentType(),sb.toString(),this.board.toString());	
	}
	
	@Override
	public void initTournament(int maxDays) {
		try {
			
			//fase a gironi
			for(TournamentElement te:this.groupsList)
				te.initTournamentElement(maxDays);
			//fase a eliminazione diretta 
			//this.board.initTournamentElement(maxDays);
			
		}
		catch(OddTeamsSizeException ex) {
			System.out.println(ex.getMessage());
		}
	}
	

}
