package database.dao;

import java.sql.SQLException;

import mvc.model.tournament.Tournament;

public interface IElementDAO {
	
	public boolean storeElement(Tournament t) throws SQLException;

}
