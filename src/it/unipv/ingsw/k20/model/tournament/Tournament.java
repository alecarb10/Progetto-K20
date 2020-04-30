package it.unipv.ingsw.k20.model.tournament;

import it.unipv.ingsw.k20.model.match.Match;
import it.unipv.ingsw.k20.model.team.Team;
import it.unipv.ingsw.k20.model.manager.Manager;


public abstract class Tournament {
	private static int n=1;
	private int tournamentID;
	private String name;
	private Manager manager;
	
	public Tournament(String name, Manager manager) {
		this.tournamentID=n;
		this.name=name;
		this.manager=manager;
		this.manager.addTournament(this);
		n++;
	}
	
	public int getTournamentID() {
		return this.tournamentID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		if(!name.isBlank())
			this.name=name;
	}
	
	public Manager getManager() {
		return this.manager;
	}
	
	public abstract TournamentType getTournamentType();
	
	public abstract void initTournament(int maxDays);
	
	public abstract boolean addTeamInTournament(Team t);
	
	public abstract boolean removeTeamFromTournament(Team t);
	
	public void insertScore(Match m, int homeScore,int awayScore) {
		m.setScore(homeScore,awayScore);
	}
	
	@Override
	public String toString() {
		return String.format("Tournament name: %s\nTournament manager:%s\n",this.name,this.manager);	
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) return false;
	    if (obj == this) return true;
	    Tournament tournament = (Tournament) obj;
	    return tournament.tournamentID == this.tournamentID;
	}
	
	@Override
	public int hashCode() {
		return ((Integer)this.tournamentID).hashCode();
	}
	
	
	 
	
}
