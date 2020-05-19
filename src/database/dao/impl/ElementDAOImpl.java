package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import database.dao.IElementDAO;
import database.util.DBConnection;

public class ElementDAOImpl implements IElementDAO {

	private Connection conn;
	
	@Override
	public boolean storeGroup(int IDTournament) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		String query = "INSERT INTO group(IDTournament, Completed) VALUES(?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, IDTournament);
		ps.setInt(2, 0);
		
		rs = ps.execute();
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}	

		DBConnection.closeConnection(conn);
		return false;
	}

	@Override
	public boolean storeBoard(int IDTournament) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		String query = "INSERT INTO board(IDTournament, Completed) VALUES(?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, IDTournament);
		ps.setInt(2, 0);
		
		rs = ps.execute();
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}	

		DBConnection.closeConnection(conn);
		return false;
	}

}
