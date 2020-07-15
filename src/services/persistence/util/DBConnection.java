package services.persistence.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class used to manage the connection to the MySQL database
 * @param schema database's name
 * @param username database user's username
 * @param passowrd database user's password
 * @see Connection
 */
public class DBConnection {

	public static Connection startConnection(Connection conn) {

		String schema = "tournament";

		String DbDriver = "com.mysql.cj.jdbc.Driver";

		String DbURL = "jdbc:mysql://localhost:3306/" + schema + "?serverTimezone=UTC";

		String username = "root";

		String password = "admin";

		if (isOpen(conn))
			closeConnection(conn);

		try {
			Class.forName(DbDriver);

			conn = DriverManager.getConnection(DbURL, username, password);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return conn;
	}

	public static boolean isOpen(Connection conn) {
		if (conn == null)
			return false;
		else
			return true;
	}

	public static Connection closeConnection(Connection conn) {
		if (!isOpen(conn))
			return null;
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return conn;
	}
}
