package database.dao;

import java.sql.SQLException;

public interface IElementDAO {
	
	public boolean storeGroup(int IDTournament) throws SQLException;
	
	public boolean storeBoard(int IDTournament) throws SQLException;

}
