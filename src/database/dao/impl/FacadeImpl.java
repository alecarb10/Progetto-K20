package database.dao.impl;

import java.sql.SQLException;

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
	public boolean removeTeam(Team t) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.removeTeam(t);
	}

	@Override
	public boolean removePlayer(Player p, Team t, int IDTournament) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.removePlayer(p, t, IDTournament);
	}
}
