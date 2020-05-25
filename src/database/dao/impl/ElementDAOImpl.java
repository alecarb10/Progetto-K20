package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;

import database.dao.IElementDAO;
import database.util.DBConnection;
import mvc.model.element.Day;
import mvc.model.match.Match;
import mvc.model.tournament.Tournament;

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
	public int getLastGroupID() throws SQLException {
		conn = DBConnection.startConnection(conn);

		ResultSet rs;

		String query = "SELECT MAX(IDGroup) FROM tournament.group";

		Statement st1;

		st1 = conn.createStatement();
		rs = st1.executeQuery(query);

		rs.next();
		int ID = rs.getInt(1);

		DBConnection.closeConnection(conn);
		return ID;
	}

	@Override
	public int getLastBoardID() throws SQLException {
		conn = DBConnection.startConnection(conn);

		ResultSet rs;

		String query = "SELECT MAX(IDBoard) FROM board";

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
	
	private boolean storeDay(Day d, Tournament t) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs, stored = false;

		String query = "INSERT INTO day(Number, Date, Group, Board) VALUES(?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, d.getNumber());
		ps.setString(2, d.getDate().toString());

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

		String query = "INSERT INTO match(Day, Date, Stadium, HomeTeam, AwayTeam, HomeScore, AwayScore, Played) VALUES(?,?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, d.getNumber());
		ps.setString(2, d.getDate().toString());
		
		if (m.getStadium() == null)
			ps.setNull(3, Types.VARCHAR);
		else
			ps.setString(3, m.getStadium().getName());
		
		ps.setInt(4, m.getHomeTeam().getId());
		ps.setInt(5, m.getAwayTeam().getId());
		ps.setInt(6, m.getHomeScore());
		ps.setInt(7, m.getAwayScore());
		ps.setInt(8, 0);

		rs = ps.execute();
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}

		DBConnection.closeConnection(conn);
		return false;
	}
	
	@Override
	public List<Day> getSchedule(Tournament t) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
