package services.persistence.dao;

import java.sql.SQLException;
import java.util.List;

import model.team.Player;
import model.team.Stadium;
import model.team.Team;
import model.tournament.Tournament;

public interface ITeamDAO {
	
	public boolean storeTeam(Team team, Tournament t) throws SQLException;
	
	public int getLastTeamID() throws SQLException;
	
	public boolean updateTeam(Team t) throws SQLException;
	
	public boolean storePlayer(Player p, Team t) throws SQLException;
	
	public int getLastPlayerID() throws SQLException;
	
	public boolean storeStadium(Stadium s) throws SQLException;
	
	public boolean checkUniqueStadium(Stadium s) throws SQLException;
	
	public boolean updateStadium(Stadium s) throws SQLException;
	
	public boolean removePlayer(Player p) throws SQLException;
	
	public boolean updatePlayer(Player p) throws SQLException;
	
	public List<Stadium> getStadiums() throws SQLException;
	
	public List<Team> getTeamsByTournament(Tournament t) throws SQLException;

}
