package database.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.model.tournament.Tournament;

public interface ITournamentDAO {

	public boolean storeTournament(Tournament t, String username) throws SQLException;
	
	public List<Tournament> getAllTournaments() throws SQLException;
	
	public List<Tournament> getAllTournamentsByManager(String username) throws SQLException;
}
