package it.unipv.ingsw.k20.model.team;

import java.util.ArrayList;

public class Stadium {
	private String name;
	private String TeamName;
	private int NumTeam;
	private String location;
	private int points;
	private ArrayList<Team> teams;
	private int capacity;
	public Stadium(String name,String location,int capacity,String TeamName, int NumTeam,String coach) {
		this.name=name;
		this.location=location;
		this.capacity=capacity;
		this.NumTeam=NumTeam;
		this.TeamName=TeamName;
		this.teams=new ArrayList<>();
		
	}
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	public void setNumTeam(int numTeam) {
		NumTeam = numTeam;
	}

	public void addTeam(Team s ) {
		teams.add(s);
	}
	
	public String getTeamName() {
		return TeamName;
	}
	public void setTeamName(String teamName) {
		TeamName = teamName;
	}
	public int getNumTeam() {
		return NumTeam;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public String toString() {
		return "-name-"+this.name+"-lacation-"+this.location+"-capacity-"+this.capacity+"-TeamName"+this.TeamName+"-Number of team-"+this.NumTeam;
	}
	
}