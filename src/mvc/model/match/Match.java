package mvc.model.match;

import mvc.model.team.*;
import java.util.Date;

public class Match {

	private int id;
	private Date date;
	private Team homeTeam;
	private Team awayTeam;
	private Stadium stadium;
	private int homeScore;
	private int awayScore;
	private boolean played;

	public Match(Date date, Team homeTeam, Team awayTeam) {
		this.date = date;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.stadium = homeTeam.getStadium();
		homeScore = 0;
		awayScore = 0;
		played = false;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}

	public Date getDate() {
		return date;
	}
	
	public void setPlayed(boolean played) {
		this.played = played;
	}
	
	public boolean isPlayed() {
		return played;
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

		played = true;
		setPoints();
	}

	private void setPoints() {
		if(played) {
			if (homeScore > awayScore) 
				homeTeam.setPoints(homeTeam.getPoints() + 3);
			 else if (homeScore < awayScore) 
				awayTeam.setPoints(awayTeam.getPoints() + 3);
			 else {
				homeTeam.setPoints(homeTeam.getPoints() + 1);
				awayTeam.setPoints(awayTeam.getPoints() + 1);
			}
		}
	}
	
	public Team getWinner() {
		if(played) {
			if (homeScore > awayScore) {
				return homeTeam;
			} else if (homeScore < awayScore) {
				return awayTeam;
			} else {
				return null;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return "MATCH: " + homeTeam.getName() + "  " + homeScore + "  VS  " + awayScore + "  " + awayTeam.getName()
				+ "    DATE: " + date;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		Match other = (Match) obj;

		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;

		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		return true;
	}
}
