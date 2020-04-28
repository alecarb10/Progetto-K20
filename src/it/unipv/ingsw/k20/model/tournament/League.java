package it.unipv.ingsw.k20.model.tournament;

import it.unipv.ingsw.k20.model.element.Group;

public class League extends Tournament {
	private Group group; // girone unico
	
	public League(String name) {
		super(name);
		this.group=null;
	}
	
	public Group getGroup() {
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
		return null;
	}
	
	

}
