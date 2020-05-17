package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.dao.IManagerDAO;
import database.util.DBConnection;
import database.util.MD5;

public class ManagerDAOImpl implements IManagerDAO {

	private Connection conn;

	public ManagerDAOImpl() {
		super();
	}

	@Override
	public boolean storeManager(String username, String name, String surname, String password) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		password = MD5.getMd5(password);
		String query = "INSERT INTO manager(Username, Name, Surname, Password) VALUES(?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, name);
		ps.setString(3, surname);
		ps.setString(4, password);
		
		rs = ps.execute();
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}	

		DBConnection.closeConnection(conn);
		return false;
	}
	
	@Override
	public boolean updateManager(String username, String name, String surname, String password) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		password = MD5.getMd5(password);
		String query = "UPDATE manager SET Name=?, Surname=?, Password=? WHERE Username=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, surname);
		ps.setString(3, password);
		ps.setString(4, username);
		rs = ps.execute();
		
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}	

		DBConnection.closeConnection(conn);
		return false;
	}

	@Override
	public boolean removeManager(String username) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;

		String query = "DELETE from manager where Username=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, username);
		rs = ps.execute();
		
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}
			
		DBConnection.closeConnection(conn);
		return false;
	}

	@Override
	public boolean checkManagerLogin(String username, String password) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		ResultSet rs;

		password = MD5.getMd5(password);
		String query = "SELECT * from manager where Username=? and Password=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, username);
		ps.setString(2, password);

		rs = ps.executeQuery();

		if (rs.next()) {
			DBConnection.closeConnection(conn);
			return true;
		}
			
		DBConnection.closeConnection(conn);
		return false;
	}

	@Override
	public boolean checkUnique(String username) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		ResultSet rs;

		String query = "SELECT count(*) from manager where Username=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, username);

		rs = ps.executeQuery();

		rs.next();
		int count = rs.getInt(1);
		
		if (count == 0) {
			DBConnection.closeConnection(conn);
			return true;
		}
			
		DBConnection.closeConnection(conn);
		return false;
	}
}
