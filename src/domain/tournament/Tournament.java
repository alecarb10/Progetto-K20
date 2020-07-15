package domain.tournament;

import java.util.List;

import domain.team.Team;

/**
 * Tournament class.
 * 
 */
public abstract class Tournament implements ITournament {
	
	private int id;
	private String name;

	/**
	 * Tournament constructor.
	 * @param name
	 * 
	 */
	public Tournament(String name) {
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addTeams(List<Team> teamsList) {
		for (Team t: teamsList)
			addTeamInTournament(t);
	}
	
	public boolean checkTournamentSize(int size,List<Team> teamsList) {
		return size==teamsList.size()?true:false;
	}
	
	@Override
	public String toString() {
		return String.format("Tournament name: %s\n", this.name);
	}
}
