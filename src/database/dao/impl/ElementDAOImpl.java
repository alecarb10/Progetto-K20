package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import database.dao.IElementDAO;
import database.util.DBConnection;
import mvc.model.element.Day;
import mvc.model.element.TournamentElement;
import mvc.model.match.Match;
import mvc.model.team.Stadium;
import mvc.model.team.Team;
import mvc.model.tournament.Tournament;
import mvc.model.tournament.TournamentType;

public class ElementDAOImpl implements IElementDAO {

	private Connection conn;
	
	@Override
	public boolean storeElement(Tournament t) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		// group
		if (t.getTournamentElement().getTournamentElementType().ordinal() == 1) {
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
		// board
		else {
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

	@Override
	public boolean storeSchedule(List<Day> schedule, Tournament t) throws SQLException {
		boolean stored = false;
		for (Day d: schedule)
			stored = storeDay(d, t);
		return stored;
	}
	
	@Override
	public boolean storeDay(Day d, Tournament t) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs, stored = false;

		String query = "INSERT INTO day(Number, Date, day.Group, Board) VALUES(?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, d.getNumber());
		ps.setTimestamp(2, new java.sql.Timestamp(d.getDate().getTime()));

		// group
		if (t.getTournamentElement().getTournamentElementType().ordinal() == 1) {
			ps.setInt(3, t.getTournamentElement().getId());
			ps.setNull(4, Types.INTEGER);
		}
		// board
		else {
			ps.setNull(3, Types.INTEGER);
			ps.setInt(4, t.getTournamentElement().getId());
		}

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
	
	@Override
	public List<Day> getSchedule(Tournament t, boolean wantsBoard) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps = null;
		ResultSet rs;
		String query;
		
		List<Day> schedule = new ArrayList<>();
		List<Match> matches;
		int idGroup;
		int idBoard;
		
		// group
		if (t.getTournamentElement().getTournamentElementType().ordinal() == 1 && !wantsBoard) {
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
		// board
		else if (t.getTournamentElement().getTournamentElementType().ordinal() == 0 || (t.getTournamentType() == TournamentType.MIXED && wantsBoard)) {
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
