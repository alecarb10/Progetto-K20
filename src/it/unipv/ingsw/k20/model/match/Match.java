package it.unipv.ingsw.k20.model.match;
import java.util.Date;
import java.util.HashMap;
import it.unipv.ingsw.k20.model.team.*;

public class Match {
	private Date date;
	private Team homeTeam;
	private Team awayTeam;
	private Stadium stadium;
	private int homeScore;
	private int awayScore;
	
	
	public Match(Date date, Team homeTeam, Team awayTeam) {
		this.date = date;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		// this.stadium = homeTeam.getStadium(); manca la classe Stadium, e la classe Team con il suo stadio di casa
		homeScore = 0;
		awayScore = 0;
	}
	
	public void setScore(int x, int y) {
		
	}
	
	/*
	public Team getWinner() {
	
		if(homeTeam.getGoalsScored() > awayTeam.getGoalsScored()) {
			homeTeam.setPoints(3);
			return homeTeam;
		} else if(homeTeam.getGoalsScored() < awayTeam.getGoalsScored()) {
			awayTeam.setPoints(3);
			return awayTeam;
		}
		else
			homeTeam.setPoits(1);
		    awayTeam.setPoits(1);
			return null;
	}
	
	public String toString() {
		return "MATCH: " + homeTeam.getName() +"  VS  "+awayTeam.getName() + "    SCORE: " + homeTeam.getGoals() + "-" + awayTeam.getGoals() + 
		"    DATE: " + date;    
	}
	*/
	
	
	
	
	
	
	

}
