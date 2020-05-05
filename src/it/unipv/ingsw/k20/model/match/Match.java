package it.unipv.ingsw.k20.model.match;

import it.unipv.ingsw.k20.model.team.*;
import java.util.Date;

public class Match {

	private Date date;
	private Team homeTeam;
	private Team awayTeam;
	private Team winner;
	private Stadium stadium;
	private int homeScore;
	private int awayScore;

	public Match(Date date, Team homeTeam, Team awayTeam) {
		this.date = date;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.stadium = homeTeam.getStadium();
		homeScore = 0;
		awayScore = 0;
		winner = null;
	}

	public Date getDate() {
		return date;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public int getAwayScore() {
		return awayScore;
	}

	public Stadium getStadium() {
		return stadium;
	}

	public void setScore(int homeScore, int awayScore) {
		this.homeScore = homeScore;
		homeTeam.setGoalsScored(homeTeam.getGoalsScored() + homeScore);
		homeTeam.setGoalsConceded(homeTeam.getGoalsConceded() + awayScore);

		this.awayScore = awayScore;
		awayTeam.setGoalsScored(awayTeam.getGoalsScored() + awayScore);
		awayTeam.setGoalsConceded(awayTeam.getGoalsConceded() + homeScore);

		setWinner();
	}

	private void setWinner() {
		if (homeScore > awayScore) {
			homeTeam.setPoints(homeTeam.getPoints() + 3);
			winner = homeTeam;
		} else if (homeScore < awayScore) {
			awayTeam.setPoints(awayTeam.getPoints() + 3);
			winner = awayTeam;
		} else {
			homeTeam.setPoints(homeTeam.getPoints() + 1);
			awayTeam.setPoints(awayTeam.getPoints() + 1);
			winner = null;
		}
	}

	public Team getWinner() {
		return this.winner;
	}
	
	@Override
	public String toString() {
		return "MATCH: " + homeTeam.getName() + "  " + homeScore + "  VS  " + awayScore + "  " + awayTeam.getName()
				+ "    DATE: " + date;
	}
}
