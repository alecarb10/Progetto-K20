package domain.team;

import java.util.ArrayList;
import java.util.List;

/**
 * Team class.
 * 
 */
public class Team {
	
	private String name;
	private int id;
	private int goalsScored;
	private int goalsConceded;
	private int points;
	private Stadium stadium;
	private ArrayList<Player> players;

	/**
	 * Team constructor.
	 * @param name
	 * 
	 */
	public Team(String name) {
		this.name = name;
		this.players = new ArrayList<>();
		this.points = 0;
		this.goalsScored = 0;
		this.goalsConceded = 0;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	
	public boolean insertPlayer(Player player) {
		return players.add(player);
	}
	
	public boolean removePlayer(Player player) {
		return players.remove(player);
	}
	
	public List<Player> getPlayers(){
		return this.players;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setGoalsScored(int goalsScored) {
		this.goalsScored = goalsScored;
	}
	
	public int getGoalsScored() {
		return goalsScored;
	}

	public int getGoalsConceded() {
		return goalsConceded;
	}

	public void setGoalsConceded(int goalsConceded) {
		this.goalsConceded = goalsConceded;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public Stadium getStadium() {
		return stadium;
	}
	
	public void setStadium(Stadium stadium) {
		this.stadium = stadium;
	}

	@Override
	public int hashCode() {
		return ((Integer)id).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		Team team = (Team) obj;
		return team.getId() == this.getId();
	}

	@Override
	public String toString() {
		return "Team: " + this.name + "\n" + this.stadium;
	}
}
