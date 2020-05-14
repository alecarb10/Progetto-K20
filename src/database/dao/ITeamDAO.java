package database.dao;

import java.sql.SQLException;

import mvc.model.team.Player;
import mvc.model.team.Stadium;
import mvc.model.team.Team;

public interface ITeamDAO {
	
	// player, stadium, team
	public boolean storeTeam(Team t) throws SQLException;
	
	public boolean storePlayer(Player p) throws SQLException;
	
	public boolean storeStadium(Stadium s) throws SQLException;
	
	public boolean removeTeam(Team t) throws SQLException;
	
	public boolean removePlayer(Player p) throws SQLException;
	
	public boolean removeStadium(Stadium s) throws SQLException;

}
