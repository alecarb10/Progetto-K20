package database.dao;

import java.sql.SQLException;

import java.util.Map;

import mvc.model.tournament.Tournament;

public interface ITournamentDAO {

	public boolean storeTournament(Tournament t, String username) throws SQLException;
	
	public int getLastTournamentID() throws SQLException;
	
	public Map<Integer, Tournament> getAllTournaments() throws SQLException;
	
	public Map<Integer, Tournament> getAllTournamentsByManager(String username) throws SQLException;
}
