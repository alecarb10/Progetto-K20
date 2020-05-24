package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import database.dao.IMatchDAO;
import database.util.DBConnection;
import mvc.model.element.Day;
import mvc.model.match.Match;

public class MatchDAOImpl implements IMatchDAO {

	private Connection conn;
	
	@Override
	public boolean storeMatch(Match m, Day d) throws SQLException {
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
	public int getLastMatchID() throws SQLException {
		conn = DBConnection.startConnection(conn);

		ResultSet rs;

		String query = "SELECT MAX(IDMatch) FROM match";

		Statement st1;

		st1 = conn.createStatement();
		rs = st1.executeQuery(query);

		rs.next();
		int ID = rs.getInt(1);

		DBConnection.closeConnection(conn);
		return ID;
	}

}
