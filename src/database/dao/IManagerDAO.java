package database.dao;

import java.sql.SQLException;
import java.util.List;

public interface IManagerDAO {

	public boolean storeManager(String username, String name, String surname, String password) throws SQLException;
	
	public boolean updateManager(String username, String name, String surname) throws SQLException;
	
	public boolean removeManager(String username) throws SQLException;
	
	public boolean checkUnique(String username) throws SQLException;
	
	public boolean checkManagerLogin(String username, String password) throws SQLException;	
	
	public List<String> getManagerByUsername(String username) throws SQLException;
}
