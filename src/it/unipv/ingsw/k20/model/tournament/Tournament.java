package it.unipv.ingsw.k20.model.tournament;

import it.unipv.ingsw.k20.model.match.Match;
import it.unipv.ingsw.k20.model.team.Team;
import it.unipv.ingsw.k20.model.manager.Manager;


public abstract class Tournament {
	private static int n=1;
	private int tournamentID;
	private String name;
	
	public Tournament(String name) {
		this.tournamentID=n;
		this.name=name;
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
	
	public abstract TournamentType getTournamentType();
	
	public abstract void initTournament(int maxDays);
	
	public abstract boolean addTeamInTournament(Team t);
	
	public abstract boolean removeTeamFromTournament(Team t);
	
	public void insertScore(Match m) {;}
	
	@Override
	public String toString() {
		return String.format("Tournament name: %s\n",this.name);	
	}
	
	/**
	 * Il metodo assegna il torneo al manager.
	 */
	public void setTournamentManager(Manager m) {
		m.addTournament(this);
	}
	
	 
	
}
