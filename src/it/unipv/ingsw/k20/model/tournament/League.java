package it.unipv.ingsw.k20.model.tournament;

import it.unipv.ingsw.k20.model.element.Group;
import it.unipv.ingsw.k20.model.element.TournamentElement;
import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;

public class League extends Tournament {
	private TournamentElement group; // girone unico
	
	public League(String name) {
		super(name);
		this.group=null;
	}
	
	public TournamentElement getGroup() {
		return this.group;
	}
	
	public void setGroup(Group group) {
		if(group!=null)
			this.group=group;
	}
	
	@Override
	public TournamentType getTournamentType() {
		return TournamentType.LEAGUE;
	}
	
	@Override
	public String toString() {
		return super.toString()+String.format("Tournament type: %s\n%s", this.getTournamentType(), this.group.toString());
	}
	
	@Override
	public void initTournament(int maxDays) {
		try {
			this.group.initTournamentElement(maxDays);
		}
		catch(OddTeamsSizeException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	

}
