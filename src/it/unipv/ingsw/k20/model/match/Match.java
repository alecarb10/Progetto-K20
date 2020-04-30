package it.unipv.ingsw.k20.model.match;
import it.unipv.ingsw.k20.model.team.*;
import java.util.Date;

public class Match {
	/*
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
		this.stadium = homeTeam.getStadium();
		homeScore = 0;
		awayScore = 0;
	}

	public void setScore(int x, int y) {
		homeScore = x;
		homeTeam.setGoalsScored(homeTeam.getGoalsScored() + x);
		homeTeam.setGoalsConceded(homeTeam.getGoalsConceded() + y);
		awayScore = y;
		awayTeam.setGoalsScored(awayTeam.getGoalsScored() + y);
		awayTeam.setGoalsConceded(awayTeam.getGoalsConceded() + x);

	}

	public Team getWinner() {
		if (homeScore > awayScore) {
			homeTeam.setPoints(homeTeam.getPoints() + 3);
			return homeTeam;
		} else if (homeScore < awayScore) {
			awayTeam.setPoints(awayTeam.getPoints() + 3);
			return awayTeam;
		} else {
			homeTeam.setPoints(homeTeam.getPoints() + 1);
		awayTeam.setPoints(awayTeam.getPoints() + 1);

		return null;
		}
	}

	public String toString() {
		return "MATCH: " + homeTeam.getName() + "  " + homeScore + "  VS  " + awayScore + "  " + awayTeam.getName()
				+ "    DATE: " + date;
	}
*/
}
