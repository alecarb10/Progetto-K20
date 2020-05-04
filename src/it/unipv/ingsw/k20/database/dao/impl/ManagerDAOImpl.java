package it.unipv.ingsw.k20.database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unipv.ingsw.k20.database.dao.IManagerDAO;
import it.unipv.ingsw.k20.database.util.DBConnection;

public class ManagerDAOImpl implements IManagerDAO {

	private Connection conn;

	public ManagerDAOImpl() {
		super();
	}

	@Override
	public boolean storeManager(String username, String name, String surname, String password) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		int rs;

		String query = "INSERT INTO manager(Username, Name, Surname, Password) VALUES" + "('" + username + "', '" + name
				+ "', '" + surname + "', '" + password + "')";
		ps = conn.prepareStatement(query);
		rs = ps.executeUpdate(query);
		if (rs > 0)
			return true;

		DBConnection.closeConnection(conn);

		return false;
	}

	@Override
	public boolean removeManager(String username) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		int rs;

		String query = "DELETE from manager where Username='" + username + "'";
		ps = conn.prepareStatement(query);
		rs = ps.executeUpdate(query);
		if (rs > 0)
			return true;

		DBConnection.closeConnection(conn);

		return false;
	}

	@Override
	public boolean checkManagerLogin(String username, String password) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement st;
		ResultSet rs;

		String query = "SELECT * from manager where Username=? and Password=?";
		st = conn.prepareStatement(query);
		st.setString(1, username);
		st.setString(2, password);

		rs = st.executeQuery();

		rs.next();
		String usernameDB = rs.getString(1);
		String passwordDB = rs.getString(4);

		if (username.equals(usernameDB) && password.equals(passwordDB))
			return true;

		DBConnection.closeConnection(conn);

		return false;
	}

	/* TEST
	public static void main(String[] args) {
		ManagerDAOImpl m = new ManagerDAOImpl();
		boolean check = false;

		try {
			check = m.storeManager("bho1", "bho2", "bho3", "password");
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			if (check)
				System.out.println("Manager Inserito");
			else
				System.out.println("Manager Non inserito");
		}
		
		try {
			check = m.storeManager("bho2", "bho3", "bho4", "password");
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			if (check)
				System.out.println("Manager Inserito");
			else
				System.out.println("Manager Non inserito");
		}
		
		try {
			check = m.removeManager("bho2");
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			if (check)
				System.out.println("Manager Rimosso");
			else
				System.out.println("Manager Non rimosso");
		}

		try {
			check = m.checkManagerLogin("bho1", "password");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (check)
				System.out.println("Loggato");
			else
				System.out.println("Non Loggato");
		}
	}
	*/
}
