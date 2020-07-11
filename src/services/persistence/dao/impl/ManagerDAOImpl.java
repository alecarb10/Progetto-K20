package services.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import services.persistence.dao.IManagerDAO;
import services.persistence.util.DBConnection;
import services.persistence.util.MD5;

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
	public boolean updateManager(String username, String name, String surname) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		String query = "UPDATE manager SET Name=?, Surname=? WHERE Username=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, surname);
		ps.setString(3, username);
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

	@Override
	public List<String> getManagerByUsername(String username) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		ResultSet rs;
		List<String> info = new ArrayList<>();

		String query = "SELECT * from manager where Username=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, username);

		rs = ps.executeQuery();

		if (rs.next()) {
			String name = rs.getString(2);
			String surname = rs.getString(3);
			
			info.add(name);
			info.add(surname);
		}
			
		DBConnection.closeConnection(conn);
		return info;
	}
}