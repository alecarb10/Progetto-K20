package it.unipv.ingsw.k20.model.tournament;

import java.util.*;

import it.unipv.ingsw.k20.model.element.Ranking;
import it.unipv.ingsw.k20.model.element.Schedule;
import it.unipv.ingsw.k20.model.team.Team;


public abstract class Tournament {
	private static int n=1;
	private int tournamentID;
	private String name;
	private List<Team> teamsList;
	
	public Tournament(String name) {
		this.tournamentID=n;
		this.name=name;
		this.teamsList= new ArrayList<>();
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
	
	public List<Team> getTeamsList(){
		return this.teamsList;
	}
	
	public boolean addTeam(Team team)throws NullPointerException{
		return this.teamsList.add(team);
	} 
	
	public boolean removeTeam(Team team)throws NullPointerException{
		return this.teamsList.remove(team);
	}
	
	public abstract TournamentType getTournamentType();
	
	@Override
	public String toString() {
		return String.format("Tournament name: %s\nTournament type: %s\n%s",this.name,this.getTournamentType().toString(),this.getTeamsListToString());	}
	
	private String getTeamsListToString() {
		StringBuilder sb= new StringBuilder("List of teams:\n");
		for(Team t:this.teamsList) 
			sb.append("\t").append(t.toString()).append("\n");
		return sb.append("\n").toString();
	}
	
	public void init() {;}
	
	private void generateSchedule(Schedule schedule) {;}
	
	private void generateRanking(Ranking ranking) {;}
	
	public void insertScore() {;} 
	
}
