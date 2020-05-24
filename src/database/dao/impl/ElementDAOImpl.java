package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.dao.IElementDAO;
import database.util.DBConnection;
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
}
