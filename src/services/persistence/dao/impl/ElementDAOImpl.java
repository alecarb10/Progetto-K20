package services.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;

import domain.element.Day;
import domain.element.TournamentElement;
import domain.match.Match;
import domain.team.Stadium;
import domain.team.Team;
import domain.tournament.Tournament;

import services.persistence.dao.IElementDAO;
import services.persistence.util.DBConnection;

/**
 * Implementation of IElementDAO
 * @param conn Connection object to manage the access to the db
 * @see IElementDAO
 * @see TournamentElement
 * @see Day
 * @see Match
 * @see Tournament
 */

public class ElementDAOImpl implements IElementDAO {

	private Connection conn;
	
	/**
	 * Stores a group into the db
	 * @param t the tournament that contains the group 
	 * @return a boolean that indicates success/insuccess
	 */
	@Override
	public boolean storeGroup(Tournament t) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		if (t != null) {
			String query = "INSERT INTO tournament.group(IDTournament, Completed) VALUES(?,?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, t.getId());
			ps.setInt(2, 0);
			
			rs = ps.execute();
			if (!rs) {
				DBConnection.closeConnection(conn);
				return true;
			}
		}
		
		DBConnection.closeConnection(conn);
		return false;
	}
	
	/**
	 * Stores a board into the db
	 * @param t the tournament that contains the board
	 * @return a boolean that indicates success/insuccess
	 */
	@Override
	public boolean storeBoard(Tournament t) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		if (t != null) {
			String query = "INSERT INTO board(IDTournament, Completed) VALUES(?,?)";
			ps = conn.prepareStatement(query);
			ps.setInt(1, t.getId());
			ps.setInt(2, 0);
			
			rs = ps.execute();
			if (!rs) {
				DBConnection.closeConnection(conn);
				return true;
			}
		}
		
		DBConnection.closeConnection(conn);
		return false;
	}

	/**
	 * Gets the id of the board, given the tournament
	 * @param t the tournament that owns the board
	 * @return board's id
	 */
	@Override
	public int getBoardIDByTournament(Tournament t) throws SQLException {
		conn = DBConnection.startConnection(conn);
		ResultSet rs;
		PreparedStatement ps;
		
		String query = "SELECT IDBoard FROM board where IDTournament=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, t.getId());
		rs = ps.executeQuery();

		rs.next();
		int ID = rs.getInt(1);

		DBConnection.closeConnection(conn);
		return ID;
	}
	
	/**
	 * Gets the id of the last element stored
	 * @return element's id
	 */
	@Override
	public int getLastElementID(TournamentElement t) throws SQLException {
		conn = DBConnection.startConnection(conn);
		ResultSet rs;
		Statement st1;
		
		// group
		if (t.getTournamentElementType().ordinal() == 1) {
			String query = "SELECT MAX(IDGroup) FROM tournament.group";
			st1 = conn.createStatement();
			rs = st1.executeQuery(query);

			rs.next();
			int ID = rs.getInt(1);

			DBConnection.closeConnection(conn);
			return ID;
		}
		// board
		else {
			String query = "SELECT MAX(IDBoard) FROM board";
			st1 = conn.createStatement();
			rs = st1.executeQuery(query);

			rs.next();
			int ID = rs.getInt(1);

			DBConnection.closeConnection(conn);
			return ID;
		}
	}
	
	/**
	 * Gets the id of the last day stored
	 * @return day's id
	 */
	private int getLastDayID() throws SQLException {
		conn = DBConnection.startConnection(conn);
		
		ResultSet rs;

		String query = "SELECT MAX(IDDay) FROM day";

		Statement st1;
		
		st1 = conn.createStatement();
		rs = st1.executeQuery(query);

		rs.next();
		int ID = rs.getInt(1);
			
		DBConnection.closeConnection(conn);
		return ID;
	}

	/**
	 * Stores a tournament's schedule into the db
	 * @param schedule
	 * @param t the tournament that owns the schedule
	 * @return a boolean that indicates success/insuccess
	 */
	@Override
	public boolean storeSchedule(List<Day> schedule, Tournament t) throws SQLException {
		boolean stored = false;
		for (Day d: schedule)
			stored = storeDay(d, t);
		return stored;
	}
	
	/**
	 * Stores a day into the db
	 * @param d the day to store
	 * @param t the tournament
	 * @return a boolean that indicates success/insuccess
	 */
	@Override
	public boolean storeDay(Day d, Tournament t) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs, stored = false;

		String query = "INSERT INTO day(Number, Date, day.Group, Board) VALUES(?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, d.getNumber());
		ps.setTimestamp(2, new java.sql.Timestamp(d.getDate().getTime()));

		if (t.getGroup() == null)
			ps.setNull(3, Types.INTEGER);
		else {
			if(t.getGroup().isCompleted())
				ps.setNull(3, Types.INTEGER);
			else 
				ps.setInt(3, t.getGroup().getId());
		}
		
		if (t.getBoard() == null)
			ps.setNull(4, Types.INTEGER);
		else
			ps.setInt(4, t.getBoard().getId());

		rs = ps.execute();
		if (!rs) {
			DBConnection.closeConnection(conn);
			
			d.setId(getLastDayID());
			
			for (Match m: d.getMatchesList())
				stored = storeMatch(m, d);
			
			return stored;
		}

		DBConnection.closeConnection(conn);
		return false;
	}
	
	/**
	 * Stores a match into the db
	 * @param m the match to store
	 * @param d the day that contains the match
	 * @return a boolean that indicates success/insuccess
	 */
	private boolean storeMatch(Match m, Day d) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;

		String query = "INSERT INTO tournament.match(Day, Stadium, HomeTeam, AwayTeam, HomeScore, AwayScore, Played) VALUES(?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, d.getId());
		
		if (m.getStadium() == null)
			ps.setNull(2, Types.VARCHAR);
		else
			ps.setString(2, m.getStadium().getName());
		
		ps.setInt(3, m.getHomeTeam().getId());
		ps.setInt(4, m.getAwayTeam().getId());
		ps.setInt(5, m.getHomeScore());
		ps.setInt(6, m.getAwayScore());
		ps.setInt(7, 0);

		rs = ps.execute();
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}

		DBConnection.closeConnection(conn);
		return false;
	}
	
	/**
	 * Gets the schedule of a group given the tournament
	 * @param t the tournament
	 * @return a list containing days: schedule
	 */
	@Override
	public List<Day> getGroupSchedule(Tournament t) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps = null;
		ResultSet rs;
		String query;
		
		List<Day> schedule = new ArrayList<>();
		List<Match> matches;
		int idGroup;
		
		if (t.getGroup() != null) {
			query = "SELECT IDGroup from tournament.group where IDTournament=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, t.getId());
			
			rs = ps.executeQuery();
			if (rs.next())
				idGroup = rs.getInt(1);
			else
				return null;
			
			query = "SELECT IDDay, Number, Date from day where day.Group=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, idGroup);
		}
		else
			return null;
		
		rs = ps.executeQuery();

		while (rs.next()) {
			int id = rs.getInt(1);
			int number = rs.getInt(2);
			Timestamp date = rs.getTimestamp(3);
			
			matches = getMatchesByDay(id, date);
			
			Day d = new Day(number, matches, date);
			d.setId(id);
			schedule.add(d);
		}
		
		DBConnection.closeConnection(conn);
		return schedule;
	}
	
	/**
	 * Gets the schedule of a board given the tournament
	 * @param t the tournament
	 * @return a list containing days: schedule
	 */
	@Override
	public List<Day> getBoardSchedule(Tournament t) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps = null;
		ResultSet rs;
		String query;
		
		List<Day> schedule = new ArrayList<>();
		List<Match> matches;
		int idBoard;
		
		if (t.getBoard() != null) {
			query = "SELECT IDBoard from board where IDTournament=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, t.getId());
			
			rs = ps.executeQuery();
			if (rs.next())
				idBoard = rs.getInt(1);
			else
				return null;
			
			query = "SELECT IDDay, Number, Date from day where day.Board=?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, idBoard);
		}
		else
			return null;
		
		rs = ps.executeQuery();

		while (rs.next()) {
			int id = rs.getInt(1);
			int number = rs.getInt(2);
			Timestamp date = rs.getTimestamp(3);
			
			matches = getMatchesByDay(id, date);
			
			Day d = new Day(number, matches, date);
			d.setId(id);
			schedule.add(d);
		}
		
		DBConnection.closeConnection(conn);
		return schedule;
	}
	
	/**
	 * Gets all matches given the day's id and date
	 * @param id day's id
	 * @param date day's date
	 * @return a list containing matches
	 */
	private List<Match> getMatchesByDay(int id, Timestamp date) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;
		String query;
		
		Match match;
		List<Match> matches = new ArrayList<>();	
		
		query = "SELECT IDMatch, HomeTeam, AwayTeam, HomeScore, AwayScore, Played from tournament.match where Day=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		
		rs = ps.executeQuery();

		while (rs.next()) {
			int idMatch = rs.getInt(1);
			int idHomeTeam = rs.getInt(2);
			int idAwayTeam = rs.getInt(3);
			int homeScore = rs.getInt(4);
			int awayScore = rs.getInt(5);
			int played = rs.getInt(6);
			
			Team homeTeam = getTeamByID(idHomeTeam);
			Team awayTeam = getTeamByID(idAwayTeam);
			match = new Match(date, homeTeam, awayTeam);
			match.setId(idMatch);
			if (played == 1)
				match.setScore(homeScore, awayScore);
			
			matches.add(match);
		}
		
		return matches;
	}
	
	/**
	 * Gets a team given the id
	 * @param id team's id
	 * @return the team
	 */
	private Team getTeamByID(int id) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;
		String query;

		Team team = null;

		query = "SELECT Name, Stadium, Points, GoalsScored, GoalsConceded from team where IDTeam=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, id);

		rs = ps.executeQuery();

		if (rs.next()) {
			String name = rs.getString(1);
			String stadium = rs.getString(2);

			int points = rs.getInt(3);
			int goalsScored = rs.getInt(4);
			int goalsConceded = rs.getInt(5);
			team = new Team(name);
			
			team.setId(id);
			team.setGoalsConceded(goalsConceded);
			team.setGoalsScored(goalsScored);
			team.setPoints(points);
			
			if (stadium != null) {
				query = "SELECT City, Capacity from stadium where Name=?";
				ps = conn.prepareStatement(query);
				ps.setString(1, stadium);
				rs = ps.executeQuery();
				rs.next();
				
				String stadiumCity = rs.getString(1);
				int stadiumCapacity = rs.getInt(2);
				team.setStadium(new Stadium(stadium, stadiumCity, stadiumCapacity));
			}
		}
		else
			return null;
		
		return team;
	}

	/**
	 * Updates a match infos, given his id
	 * @param match
	 * @return a boolean that indicates success/insuccess
	 */
	@Override
	public boolean updateMatch(Match match) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		String query = "UPDATE tournament.match SET HomeScore=?, AwayScore=?, Played=?, Stadium=? WHERE IDMatch=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1,match.getHomeScore());
		ps.setInt(2, match.getAwayScore());
		ps.setInt(3, 1);
		
		if (match.getStadium() == null)
			ps.setNull(4, Types.VARCHAR);
		else
			ps.setString(4, match.getStadium().getName());
		
		ps.setInt(5, match.getId());
		rs = ps.execute();
		
		if (!rs) {
			if (!updateTeamInfo(match.getHomeTeam()) && !updateTeamInfo(match.getAwayTeam())) {
				DBConnection.closeConnection(conn);
				return true;
			}
		}	

		DBConnection.closeConnection(conn);
		return false;
	}
	
	/**
	 * Updates team informations after match update, given his id
	 * @param team
	 * @return a boolean that indicates success/insuccess
	 */
	private boolean updateTeamInfo(Team team) throws SQLException {
		PreparedStatement ps;
		
		String query = "UPDATE team SET Points=?, GoalsScored=?, GoalsConceded=? WHERE IDTeam=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, team.getPoints());
		ps.setInt(2, team.getGoalsScored());
		ps.setInt(3, team.getGoalsConceded());
		ps.setInt(4, team.getId());
		
		return ps.execute();
	}
}
