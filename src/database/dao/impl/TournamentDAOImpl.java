package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.HashMap;

import database.dao.ITournamentDAO;
import database.util.DBConnection;
import mvc.model.tournament.*;

public class TournamentDAOImpl implements ITournamentDAO {

	private Connection conn;
	
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
	
	@Override
	public Map<Integer,Tournament> getAllTournaments() throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		ResultSet rs;
		
		Map<Integer, Tournament> map = new HashMap<>();
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
					map.put(ID, t);
					break;
				
				case MIXED:
					t = new MixedTournament(name);
					map.put(ID, t);
					break;
					
				case KNOCKOUT_PHASE:
					t = new KnockoutPhase(name);
					map.put(ID, t);
					break;
			}
		}
			
		DBConnection.closeConnection(conn);
		return map;
	}
	
	@Override
	public Map<Integer, Tournament> getAllTournamentsByManager(String username) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		ResultSet rs;
		
		Map<Integer, Tournament> map = new HashMap<>();
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
					map.put(ID, t);
					break;
				
				case MIXED:
					t = new MixedTournament(name);
					map.put(ID, t);
					break;
					
				case KNOCKOUT_PHASE:
					t = new KnockoutPhase(name);
					map.put(ID, t);
					break;
			}
		}
			
		DBConnection.closeConnection(conn);
		return map;
	}
}
