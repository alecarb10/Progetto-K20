package database.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

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
	public List<Tournament> getAllTournaments() throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		ResultSet rs;
		
		List<Tournament> list = new ArrayList<>();
		Tournament t = null;

		String query = "SELECT t.Name, tp.Name from tournament t,tournament_type tp where t.TournamentType=tp.IDTournamentType";
		ps = conn.prepareStatement(query);

		rs = ps.executeQuery();
		
		while (rs.next()) {
			String name = rs.getString(1);
			TournamentType type = TournamentType.valueOf(rs.getString(2));
			switch(type) {
				case LEAGUE:
					t = new League(name);
					list.add(t);
					break;
				
				case MIXED:
					t = new MixedTournament(name);
					list.add(t);
					break;
					
				case KNOCKOUT_PHASE:
					t = new KnockoutPhase(name);
					list.add(t);
					break;
			}
		}
			
		DBConnection.closeConnection(conn);
		return list;
	}
	
	@Override
	public List<Tournament> getAllTournamentsByManager(String username) throws SQLException {
		conn = DBConnection.startConnection(conn);
		PreparedStatement ps;
		ResultSet rs;
		
		List<Tournament> list = new ArrayList<>();
		Tournament t = null;

		String query = "SELECT t.Name, tp.Name from tournament t,tournament_type tp where Manager=? and t.TournamentType=tp.IDTournamentType";
		ps = conn.prepareStatement(query);
		ps.setString(1, username);

		rs = ps.executeQuery();
		
		while (rs.next()) {
			String name = rs.getString(1);
			TournamentType type = TournamentType.valueOf(rs.getString(2));
			switch(type) {
				case LEAGUE:
					t = new League(name);
					list.add(t);
					break;
				
				case MIXED:
					t = new MixedTournament(name);
					list.add(t);
					break;
					
				case KNOCKOUT_PHASE:
					t = new KnockoutPhase(name);
					list.add(t);
					break;
			}
		}
			
		DBConnection.closeConnection(conn);
		return list;
	}
}
