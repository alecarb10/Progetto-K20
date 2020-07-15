package services.persistence.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import domain.tournament.*;

import services.persistence.dao.ITournamentDAO;
import services.persistence.util.DBConnection;

/**
 * Implementation of ITournamentDAO
 * @param conn Connection object to manage the access to the db
 * @see ITournamentDAO
 * @see Tournament
 */
public class TournamentDAOImpl implements ITournamentDAO {

	private Connection conn;
	
	/**
	 * Stores a tournament into the db
	 * @param t the tournament to store
	 * @param username the username of this tournament's manager
	 * @return a boolean that indicates success/insuccess
	 */
	@Override
	public boolean storeTournament(Tournament t, String username) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		boolean rs;
		
		String query = "INSERT INTO tournament(Name, Manager, TournamentType) VALUES(?,?,?)";
		ps = conn.prepareStatement(query);
		ps.setString(1, t.getName());
		ps.setString(2, username);
		ps.setInt(3, t.getTournamentType().ordinal() + 1);
		
		rs = ps.execute();
		if (!rs) {
			DBConnection.closeConnection(conn);
			return true;
		}	

		DBConnection.closeConnection(conn);
		return false;
	}
	
	/**
	 * Gets the id of the last tournament stored
	 * @return tournament's id
	 */
	@Override
	public int getLastTournamentID() throws SQLException {
		conn = DBConnection.startConnection(conn);
		
		ResultSet rs;

		String query = "SELECT MAX(IDTournament) FROM tournament";

		Statement st1;
		
		st1 = conn.createStatement();
		rs = st1.executeQuery(query);

		rs.next();
		int ID = rs.getInt(1);
			
		DBConnection.closeConnection(conn);
		return ID;
	}
	
	/**
	 * Checks if a tournament name, for a specific manager, is already present into the db
	 * @param name the name of the tournament
	 * @param username the username of the tournament's manager
	 * @return a boolean that indicates success/insuccess
	 */
	@Override
	public boolean checkUnique(String name, String username) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		ResultSet rs;

		String query = "SELECT count(*) from tournament where Name=? and Manager=?";
		ps = conn.prepareStatement(query);
		ps.setString(1, name);
		ps.setString(2, username);

		rs = ps.executeQuery();

		rs.next();
		int count = rs.getInt(1);
		
		if (count == 0) {
			DBConnection.closeConnection(conn);
			return true;
		}
			
		DBConnection.closeConnection(conn);
		return false;
	}
	
	/**
	 * Gets all tournaments
	 * @return a list containing tournaments
	 */
	@Override
	public List<Tournament> getAllTournaments() throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		ResultSet rs;
		
		List<Tournament> list = new ArrayList<>();
		Tournament t = null;

		String query = "SELECT t.IDTournament, t.Name, tp.Name from tournament t,tournament_type tp where t.TournamentType=tp.IDTournamentType";
		ps = conn.prepareStatement(query);

		rs = ps.executeQuery();
		
		while (rs.next()) {
			int ID = rs.getInt(1);
			String name = rs.getString(2);
			TournamentType type = TournamentType.valueOf(rs.getString(3));
			switch(type) {
				case LEAGUE:
					t = new League(name);
					t.setId(ID);
					list.add(t);
					break;
				
				case MIXED:
					t = new MixedTournament(name);
					t.setId(ID);
					list.add(t);
					break;
					
				case KNOCKOUT_PHASE:
					t = new KnockoutPhase(name);
					t.setId(ID);
					list.add(t);
					break;
			}
		}
			
		DBConnection.closeConnection(conn);
		return list;
	}
	
	/**
	 * Gets all tournaments of a specific manager
	 * @param username the username of the manager
	 * @return a list containing teams
	 */
	@Override
	public List<Tournament> getAllTournamentsByManager(String username) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		ResultSet rs;
		
		List<Tournament> list = new ArrayList<>();
		Tournament t = null;

		String query = "SELECT t.IDTournament, t.Name, tp.Name from tournament t,tournament_type tp where Manager=? and t.TournamentType=tp.IDTournamentType";
		ps = conn.prepareStatement(query);
		ps.setString(1, username);

		rs = ps.executeQuery();
		
		while (rs.next()) {
			int ID = rs.getInt(1);
			String name = rs.getString(2);
			TournamentType type = TournamentType.valueOf(rs.getString(3));
			switch(type) {
				case LEAGUE:
					t = new League(name);
					t.setId(ID);
					list.add(t);
					break;
				
				case MIXED:
					t = new MixedTournament(name);
					t.setId(ID);
					list.add(t);
					break;
					
				case KNOCKOUT_PHASE:
					t = new KnockoutPhase(name);
					t.setId(ID);
					list.add(t);
					break;
			}
		}
			
		DBConnection.closeConnection(conn);
		return list;
	}
}
