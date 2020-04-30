package it.unipv.ingsw.k20.model.tournament;

import it.unipv.ingsw.k20.model.element.Group;
import it.unipv.ingsw.k20.model.element.TournamentElement;
import it.unipv.ingsw.k20.model.exception.OddTeamsSizeException;
import it.unipv.ingsw.k20.model.manager.Manager;
import it.unipv.ingsw.k20.model.team.Team;

public class League extends Tournament {
	private TournamentElement group; // girone unico
	
	public League(String name,Manager manager, int maxDays) {
		super(name,manager);
		this.initTournament(maxDays);
	}
	
	public TournamentElement getGroup() {
		return this.group;
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
			this.group=new Group();
			this.group.initTournamentElement(maxDays);
		}
		catch(OddTeamsSizeException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	@Override
	public boolean addTeamInTournament(Team team) {
		return this.group.addTeam(team);
	}
	
	@Override
	public boolean removeTeamFromTournament(Team team) {
		return this.group.addTeam(team);
	}
	

}