package database.dao;

import java.sql.SQLException;
import java.util.List;

import mvc.model.team.Player;
import mvc.model.team.Stadium;
import mvc.model.team.Team;
import mvc.model.tournament.Tournament;

public interface ITeamDAO {
	
	public boolean storeTeam(Team team, Tournament t, int IDTournament) throws SQLException;
	
	public boolean storePlayer(Player p, Team t, int IDTournament) throws SQLException;
	
	public boolean storeStadium(Stadium s) throws SQLException;
	
	public boolean removePlayer(Player p, Team t, int IDTournament) throws SQLException;
	
	public List<Player> getPlayersByTeam(Team t, int IDTournament) throws SQLException;
	
	public List<Team> getTeamsByTournament(int IDTournament) throws SQLException;

}
