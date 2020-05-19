package database.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import database.dao.*;
import mvc.model.team.Player;
import mvc.model.team.Stadium;
import mvc.model.team.Team;
import mvc.model.tournament.Tournament;

public class FacadeImpl implements IFacade {

	@Override
	public boolean storeManager(String username, String name, String surname, String password) throws SQLException {
		IManagerDAO md = new ManagerDAOImpl();
		return md.storeManager(username, name, surname, password);
	}

	@Override
	public boolean updateManager(String username, String name, String surname, String password) throws SQLException {
		IManagerDAO md = new ManagerDAOImpl();
		return md.updateManager(username, name, surname, password);
	}
	
	@Override
	public boolean removeManager(String username) throws SQLException {
		IManagerDAO md = new ManagerDAOImpl();
		return md.removeManager(username);
	}

	@Override
	public boolean checkManagerLogin(String username, String password) throws SQLException {
		IManagerDAO md = new ManagerDAOImpl();
		return md.checkManagerLogin(username, password);
	}

	@Override
	public boolean checkUnique(String username) throws SQLException {
		IManagerDAO md = new ManagerDAOImpl();
		return md.checkUnique(username);
	}

	@Override
	public boolean storeTournament(Tournament t, String username) throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.storeTournament(t, username);
	}
	
	@Override
	public int getLastTournamentID() throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.getLastTournamentID();
	}
	
	@Override
	public Map<Integer, Tournament> getAllTournaments() throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.getAllTournaments();
	}
	
	@Override
	public Map<Integer, Tournament> getAllTournamentsByManager(String username) throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.getAllTournamentsByManager(username);
	}

	@Override
	public boolean storeTeam(Team team, Tournament t, int IDTournament) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.storeTeam(team, t, IDTournament);
	}

	@Override
	public boolean storePlayer(Player p, Team t, int IDTournament) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.storePlayer(p, t, IDTournament);
	}

	@Override
	public boolean storeStadium(Stadium s) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.storeStadium(s);
	}

	@Override
	public boolean removePlayer(Player p, Team t, int IDTournament) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.removePlayer(p, t, IDTournament);
	}

	@Override
	public List<Player> getPlayersByTeam(Team t, int IDTournament) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.getPlayersByTeam(t, IDTournament);
	}

	@Override
	public List<Team> getTeamsByTournament(int IDTournament) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.getTeamsByTournament(IDTournament);
	}
	
	@Override
	public boolean storeGroup(int IDTournament) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.storeGroup(IDTournament);
	}

	@Override
	public boolean storeBoard(int IDTournament) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.storeBoard(IDTournament);
	}
}
