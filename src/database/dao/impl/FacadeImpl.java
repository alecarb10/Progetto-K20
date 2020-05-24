package database.dao.impl;

import java.sql.SQLException;
import java.util.List;

import database.dao.*;
import mvc.model.element.Day;
import mvc.model.match.Match;
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
	public boolean checkUnique(String name, String username) throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.checkUnique(name, username);
	}
	
	@Override
	public int getLastTournamentID() throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.getLastTournamentID();
	}
	
	@Override
	public List<Tournament> getAllTournaments() throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.getAllTournaments();
	}
	
	@Override
	public List<Tournament> getAllTournamentsByManager(String username) throws SQLException {
		ITournamentDAO td = new TournamentDAOImpl();
		return td.getAllTournamentsByManager(username);
	}

	@Override
	public boolean storeTeam(Team team, Tournament t) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.storeTeam(team, t);
	}

	@Override
	public boolean storePlayer(Player p, Team t) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.storePlayer(p, t);
	}
	
	@Override
	public int getLastTeamID() throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.getLastTeamID();
	}

	@Override
	public int getLastPlayerID() throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.getLastPlayerID();
	}

	@Override
	public boolean storeStadium(Stadium s) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.storeStadium(s);
	}

	@Override
	public boolean removePlayer(Player p, Team t) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.removePlayer(p, t);
	}

	@Override
	public List<Player> getPlayersByTeam(Team t) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.getPlayersByTeam(t);
	}

	@Override
	public List<Team> getTeamsByTournament(Tournament t) throws SQLException {
		ITeamDAO tmd = new TeamDAOImpl();
		return tmd.getTeamsByTournament(t);
	}
	
	@Override
	public boolean storeElement(Tournament t) throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.storeElement(t);
	}
	
	@Override
	public int getLastGroupID() throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.getLastGroupID();
	}

	@Override
	public int getLastBoardID() throws SQLException {
		IElementDAO ed = new ElementDAOImpl();
		return ed.getLastBoardID();
	}

	@Override
	public boolean storeMatch(Match m, Day d) throws SQLException {
		IMatchDAO md = new MatchDAOImpl();
		return md.storeMatch(m, d);
	}

	@Override
	public int getLastMatchID() throws SQLException {
		IMatchDAO md = new MatchDAOImpl();
		return md.getLastMatchID();
	}
}
