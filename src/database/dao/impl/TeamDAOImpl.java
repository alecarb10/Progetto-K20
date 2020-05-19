package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import database.dao.ITeamDAO;
import database.util.DBConnection;
import mvc.model.team.Player;
import mvc.model.team.PlayerPositionType;
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
			ps.setInt(7, getIDGroup(IDTournament));
			ps.setNull(8, Types.INTEGER);
		}
		// board
		else {
			ps.setNull(7, Types.INTEGER);
			ps.setInt(8, getIDBoard(IDTournament));
		}

		rs = ps.execute();
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}

		DBConnection.closeConnection(conn);
		return false;
	}

	private int getIDGroup(int IDTournament) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;

		String query = "SELECT IDGroup from tournament.group where IDTournament=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, IDTournament);

		rs = ps.executeQuery();

		rs.next();
		int ID = rs.getInt(1);

		return ID;
	}

	private int getIDBoard(int IDTournament) throws SQLException {
		PreparedStatement ps;
		ResultSet rs;

		String query = "SELECT IDBoard from tournament.board where IDTournament=?";
		ps = conn.prepareStatement(query);
		ps.setInt(1, IDTournament);

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

	@Override
	public List<Player> getPlayersByTeam(Team t, int IDTournament) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		ResultSet rs;

		List<Player> players = new ArrayList<>();
		int IDTeam = getIDTeam(t, IDTournament);

		String query = "SELECT Name, Surname, Number, Position from player p, player_position_type pp where IDTeam=? and p.PlayerPositionType=pp.IDPlayerPositionType";
		ps = conn.prepareStatement(query);
		ps.setInt(1, IDTeam);

		rs = ps.executeQuery();

		while (rs.next()) {
			String name = rs.getString(1);
			String surname = rs.getString(2);
			int number = rs.getInt(3);
			PlayerPositionType type = PlayerPositionType.valueOf(rs.getString(4));
			players.add(new Player(name, surname, number, type));
		}

		DBConnection.closeConnection(conn);
		return players;
	}

	@Override
	public List<Team> getTeamsByTournament(int IDTournament) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		PreparedStatement ps2;
		ResultSet rs;
		ResultSet rs2;

		List<Team> teams = new ArrayList<>();
		Team t = null;

		String query = "SELECT Name, Stadium, Points, GoalsScored, GoalsConceded from team";
		ps = conn.prepareStatement(query);

		rs = ps.executeQuery();

		while (rs.next()) {
			String name = rs.getString(1);
			String stadium = rs.getString(2);

			int points = rs.getInt(3);
			int goalsScored = rs.getInt(4);
			int goalsConceded = rs.getInt(5);
			t = new Team(name);
			
			t.setGoalsConceded(goalsConceded);
			t.setGoalsScored(goalsScored);
			t.setPoints(points);
			
			if (stadium != null) {
				String query2 = "SELECT City, Capacity from stadium where Name=?";
				ps2 = conn.prepareStatement(query2);
				ps2.setString(1, stadium);
				rs2 = ps2.executeQuery();
				rs2.next();
				
				String stadiumCity = rs2.getString(1);
				int stadiumCapacity = rs2.getInt(2);
				t.setStadium(new Stadium(stadium, stadiumCity, stadiumCapacity));
			}
	
			teams.add(t);
		}

		DBConnection.closeConnection(conn);
		return teams;
	}
}
