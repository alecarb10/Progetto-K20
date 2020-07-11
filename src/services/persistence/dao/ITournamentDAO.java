package services.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import domain.tournament.Tournament;

public interface ITournamentDAO {

	public boolean storeTournament(Tournament t, String username) throws SQLException;
	
	public int getLastTournamentID() throws SQLException;
	
	public boolean checkUnique(String name, String username) throws SQLException;
	
	public List<Tournament> getAllTournaments() throws SQLException;
	
	public List<Tournament> getAllTournamentsByManager(String username) throws SQLException;
}
