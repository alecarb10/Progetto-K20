package it.unipv.ingsw.k20.model.team;

import java.util.ArrayList;
import java.util.List;

public class Team {
	private String name;
	private int goalsScored;
	private int goalsConceded;
	private int points;
	private Stadium stadium;
	private ArrayList<Player> players;

	public Team(String name) {
		this.name = name;
		this.players = new ArrayList<>();
		this.points = 0;
		this.goalsScored = 0;
		this.goalsConceded = 0;
	}

	public boolean insertPlayer(Player player) {
		return players.add(player);
	}
	
	public boolean removePlayer(Player player) {
		for (Player p: players)
			if (player.getSurname().equals(p.getSurname()) && player.getNumber() == p.getNumber())
				return players.remove(p);
		return false;
	}

	public List<Player> getLineUp() {
		List<Player> lineup = new ArrayList<>();
		
		for (Player p : players)
			if (p.getHolder())
				lineup.add(p);
		
		return lineup;
	}

	public int getGoalsScored() {
		return goalsScored;
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

	public int getGoalsConceded() {
		return goalsConceded;
	}

	public void setGoalsConceded(int goalsConceded) {
		this.goalsConceded = goalsConceded;
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

	public int numberOfPlayers() {
		return this.players.size();
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		Team team = (Team) obj;
		return team.getName() == this.getName();
	}

	@Override
	public String toString() {
		return "Team: " + this.name + "\n\tStadium: " + this.stadium;
	}
}
