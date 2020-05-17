package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import database.dao.ITeamDAO;
import database.util.DBConnection;
import mvc.model.team.Player;
import mvc.model.team.Stadium;
import mvc.model.team.Team;
import mvc.model.tournament.Tournament;

public class TeamDAOImpl implements ITeamDAO {

	private Connection conn;
	
	@Override
	public boolean storeTeam(Team team, Tournament t, int IDTournament) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		String query = "INSERT INTO team(Name, IDTournament, Stadium, Points, GoalsScored, GoalsConceded, team.Group, Board) VALUES(?,?,?,?,?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setString(1, team.getName());
		ps.setInt(2, IDTournament);
		ps.setNull(3, Types.INTEGER);
		ps.setInt(4, team.getPoints());
		ps.setInt(5, team.getGoalsScored());
		ps.setInt(6, team.getGoalsConceded());
		
		// group		
		if (t.getTournamentElement().getTournamentElementType().ordinal() == 1) {
			ps.setInt(7, getIDGroup(t.getTournamentElement().getName(), IDTournament));
			ps.setNull(8, Types.INTEGER);
		}
		// board
		else {
			ps.setNull(7, Types.INTEGER);
			ps.setInt(8, getIDBoard(t.getTournamentElement().getName(), IDTournament));
		}
			
		rs = ps.execute();
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}	

		DBConnection.closeConnection(conn);
		return false;
	}
	
	private int getIDGroup(String name, int IDTournament) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;

		String query = "SELECT IDGroup from tournament.group where Name=? and IDTournament=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt(2, IDTournament);

		rs = ps.executeQuery();

		rs.next();
		int ID = rs.getInt(1);
			
		return ID;
	}
	
	private int getIDBoard(String name, int IDTournament) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;

		String query = "SELECT IDBoard from tournament.board where Name=? and IDTournament=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, name);
		ps.setInt(2, IDTournament);

		rs = ps.executeQuery();

		rs.next();
		int ID = rs.getInt(1);
			
		return ID;
	}

	@Override
	public boolean storePlayer(Player p, Team t, int IDTournament) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		int IDTeam = getIDTeam(t, IDTournament);
		
		String query = "INSERT INTO player(IDTeam, Number, Name, Surname, PlayerPositionType) VALUES(?,?,?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setInt(1, IDTeam);
		ps.setInt(2, p.getNumber());
		ps.setString(3, p.getName());
		ps.setString(4, p.getSurname());
		ps.setInt(5, p.getPosition().ordinal() + 1);
		
		rs = ps.execute();
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}	

		DBConnection.closeConnection(conn);
		return false;
	}
	
	private int getIDTeam(Team t, int IDTournament) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;

		String query = "SELECT IDTeam from team where Name=? and IDTournament=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, t.getName());
		ps.setInt(2, IDTournament);

		rs = ps.executeQuery();

		rs.next();
		int ID = rs.getInt(1);
			
		return ID;
	}

	@Override
	public boolean storeStadium(Stadium s) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		String query = "INSERT INTO stadium(Name, City, Capacity) VALUES(?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setString(1, s.getName());
		ps.setString(2, s.getCity());
		ps.setInt(3, s.getCapacity());
		
		rs = ps.execute();
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}	

		DBConnection.closeConnection(conn);
		return false;
	}

	@Override
	public boolean removeTeam(Team t) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removePlayer(Player p, Team t, int IDTournament) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;

		int IDTeam = getIDTeam(t, IDTournament);
		
		String query = "DELETE from player where Surname=? and Number=? and IDTeam=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, p.getSurname());
		ps.setInt(2, p.getNumber());
		ps.setInt(3, IDTeam);
		rs = ps.execute();
		
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}
			
		DBConnection.closeConnection(conn);
		return false;
	}	
}
