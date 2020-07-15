package domain.tournament;

import java.util.List;

import domain.exception.IllegalTeamsSizeException;
import domain.exception.SameTeamNameException;
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
	
	@Override
	public boolean checkTournamentSize(int size, List<Team> teamsList) throws IllegalTeamsSizeException {
		if (size == teamsList.size())
			return true;
		else
			throw new IllegalTeamsSizeException("Tournament size doesn't match teams list size!");
	}
	
	@Override
	public boolean checkNamesInTeams(List<Team> teamsList) throws SameTeamNameException {
		boolean check = false;
		
		for (int i = 0; i < teamsList.size() - 1; i++)
			for (int j = i + 1; j < teamsList.size(); j++)
				if (teamsList.get(i).getName().equalsIgnoreCase(teamsList.get(j).getName()))
					check = true;
		
		if (check)
			throw new SameTeamNameException("There are two teams with the same name!");
		return true;
	}
	
	@Override
	public String toString() {
		return String.format("Tournament name: %s\n", this.name);
	}
	
}
