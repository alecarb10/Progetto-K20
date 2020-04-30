package it.unipv.ingsw.k20.model.team;

public class Stadium {
	private String name;
	private String TeamName;
	private int NumTeam;
	private String location;
	private int capacity;
	public Stadium(String name,String location,int capacity,String TeamName, int NumTeam) {
		this.name=name;
		this.location=location;
		this.capacity=capacity;
		this.NumTeam=NumTeam;
		this.TeamName=TeamName;
		
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
