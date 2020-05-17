package database.dao;

import java.sql.SQLException;

import mvc.model.team.Player;
import mvc.model.team.Stadium;
import mvc.model.team.Team;
import mvc.model.tournament.Tournament;

public interface ITeamDAO {
	
	public boolean storeTeam(Team team, Tournament t, int IDTournament) throws SQLException;
	
	public boolean storePlayer(Player p, Team t, int IDTournament) throws SQLException;
	
	public boolean storeStadium(Stadium s) throws SQLException;
	
	public boolean removeTeam(Team t) throws SQLException;
	
	public boolean removePlayer(Player p, Team t, int IDTournament) throws SQLException;
}
