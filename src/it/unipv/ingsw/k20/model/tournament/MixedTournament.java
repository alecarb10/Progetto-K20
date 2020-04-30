package it.unipv.ingsw.k20.model.tournament;

import java.util.ArrayList;
import java.util.List;

import it.unipv.ingsw.k20.model.element.Board;
import it.unipv.ingsw.k20.model.element.Group;
import it.unipv.ingsw.k20.model.element.TournamentElement;
import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;
import it.unipv.ingsw.k20.model.manager.Manager;
import it.unipv.ingsw.k20.model.team.Team;

public class MixedTournament extends Tournament {
	
	private List<TournamentElement> groupsList;
	private TournamentElement board;
	
	public MixedTournament(String name,Manager manager, int maxDays) {
		super(name,manager);
		this.groupsList=new ArrayList<>();
		this.initTournament(maxDays);
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
		char groupLetter=65; // A
		StringBuilder sb= new StringBuilder().append("Gironi\n");
		for(TournamentElement te: this.groupsList) {
			sb.append("Group ").append(groupLetter).append("\n").append(te).append("\n");
			groupLetter++;
		}
		return super.toString()+String.format("Tournament type: %s\n%s\n%s\n", this.getTournamentType(),sb.toString(),this.board.toString());	
	}
	
	@Override
	public void initTournament(int maxDays) {
		try {
			
			//fase a gironi
			for(TournamentElement te:this.groupsList)
				te.initTournamentElement(maxDays);
			//fase ad eliminazione diretta 
			if(this.isEachGroupCompleted())
				this.board.initTournamentElement(maxDays);
			
		}
		catch(OddTeamsSizeException ex) {
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * Il metodo verifica se tutti i gironi hanno terminato.
	 */
	private boolean isEachGroupCompleted() {
		int n=0;
		for(TournamentElement te:this.groupsList)
			if(te.isCompleted())
				n++;
		return n==this.groupsList.size()?true:false;
			
	}
	
	@Override
	public boolean addTeamInTournament(Team team) {
		for(TournamentElement te:this.groupsList)
			if(te.getTeamsList().size()<4)
				return te.addTeam(team);
		return false; // torneo al completo
	}
	
	@Override
	public boolean removeTeamFromTournament(Team team) {
		for(TournamentElement te:this.groupsList)
				return te.removeTeam(team);
		return false;
	}
	

}
