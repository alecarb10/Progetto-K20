package it.unipv.ingsw.k20.model.tournament;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.k20.model.element.Board;
import it.unipv.ingsw.k20.model.element.Group;

public class MixedTournament extends Tournament {
	
	private List<Group> groupsList;
	private Board board;
	
	public MixedTournament(String name) {
		super(name);
		this.groupsList=new ArrayList<>();
		this.board=null;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
	public void setBoard(Board board) {
		if(board!=null)
			this.board=board;
	}
	
	public List<Group> getGroupsList(){
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
		return null;
	}
	
	

}
