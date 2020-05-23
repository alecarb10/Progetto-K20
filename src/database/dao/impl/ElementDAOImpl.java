package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			String query = "INSERT INTO group(IDTournament, Completed) VALUES(?,?)";
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
}
