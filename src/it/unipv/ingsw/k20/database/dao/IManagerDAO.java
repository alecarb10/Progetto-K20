package it.unipv.ingsw.k20.database.dao;

import java.sql.SQLException;

public interface IManagerDAO {

	public boolean storeManager(String username, String name, String surname, String password) throws SQLException;
	
	public boolean removeManager(String username) throws SQLException;
	
	public boolean checkManagerLogin(String username, String password) throws SQLException;
	
}
