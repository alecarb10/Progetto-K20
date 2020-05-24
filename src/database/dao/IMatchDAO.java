package database.dao;

import java.sql.SQLException;

import mvc.model.element.Day;
import mvc.model.match.Match;

public interface IMatchDAO {

	public boolean storeMatch(Match m, Day d) throws SQLException;
	
	public int getLastMatchID() throws SQLException;
	
}
