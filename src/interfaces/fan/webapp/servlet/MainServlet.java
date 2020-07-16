package interfaces.fan.webapp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import domain.team.Team;
import domain.tournament.Tournament;
import services.persistence.dao.impl.FacadeImpl;

/**
 * Concrete servlet to manage get and post request of the entire webapp
 */

@SuppressWarnings("serial")
public class MainServlet extends WebServlet {

	private List<Tournament> tournaments;
	private Tournament tournament;
	private Team team;
	
	private final int FIRST_TEAMS_SIZE = 4;		// for board with 4 teams
	private final int SECOND_TEAMS_SIZE = 8;	// for board with 8 teams
	private final int THIRD_TEAMS_SIZE = 16;	// for board with 16 teams
	
	public MainServlet(String name, String url) {
		super(name, url);
	}
	
	/**
	 * Manage get requests
	 */
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (req.getPathInfo().equals("/") || req.getPathInfo().equals("/home.html")) {
			try {
				getTournaments();
				resp.getWriter().write(Rythm.render("home.html", tournaments));
			} 
			catch (SQLException | NullPointerException e) {
				resp.getWriter().write(Rythm.render("error.html"));
				e.printStackTrace();
			}
		}
		
		if (req.getPathInfo().equals("/teams") || req.getPathInfo().equals("/teams.html"))
			resp.getWriter().write(Rythm.render("teams.html", tournament));
		
		if (req.getPathInfo().equals("/group") || req.getPathInfo().equals("/group.html"))
			if (tournament.getGroup() != null)
				resp.getWriter().write(Rythm.render("group.html", tournament, null));
			
		if (req.getPathInfo().equals("/board") || req.getPathInfo().equals("/board.html")) {
			if (tournament.getBoard() != null) {
				if (tournament.getBoard().getTeamsList().size() == FIRST_TEAMS_SIZE)
					resp.getWriter().write(Rythm.render("board4.html", tournament));
				
				else if (tournament.getBoard().getTeamsList().size() == SECOND_TEAMS_SIZE)
					resp.getWriter().write(Rythm.render("board8.html", tournament));
				
				else if (tournament.getBoard().getTeamsList().size() == THIRD_TEAMS_SIZE)
					resp.getWriter().write(Rythm.render("board16.html", tournament));
			}
		}
	}
	
	/**
	 * Manage post requests
	 */
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		if (req.getPathInfo().equals("/view")) {
			findTournamentById(Integer.parseInt(req.getParameter("id")));
			
			try {
				initTournament();
				resp.getWriter().write(Rythm.render("tournament-detail.html", tournament));
			} 
			catch (SQLException | NullPointerException e) {
				resp.getWriter().write(Rythm.render("error.html"));
				e.printStackTrace();
			}
		} 
		
		if (req.getPathInfo().equals("/viewteams")) {
			findTeamById(Integer.parseInt(req.getParameter("id")));
			resp.getWriter().write(Rythm.render("team-detail.html", team));	
		} 
		
		if (req.getPathInfo().equals("/group")) {
			int day = Integer.parseInt(req.getParameter("daySelect"));
			resp.getWriter().write(Rythm.render("group.html", tournament, tournament.getGroupSchedule().get(day - 1)));
		}
	}
	
	/**
	 * Gets tournaments from db
	 * @throws SQLException
	 */
	private void getTournaments() throws SQLException {
		tournaments = FacadeImpl.getInstance().getAllTournaments();
	}
	
	/**
	 * Finds a tournament given id
	 * @param id
	 */
	private void findTournamentById(int id) {
		for (Tournament t: tournaments)
			if (t.getId() == id)
				this.tournament = t;
	}
	
	/**
	 * Inits all components of the tournament
	 * @throws SQLException
	 */
	private void initTournament() throws SQLException {
		if (this.tournament.getTeamsList().size() == 0)
			tournament.addTeams(FacadeImpl.getInstance().getTeamsByTournament(this.tournament));
		if (this.tournament.getGroupSchedule() == null)
			tournament.setGroupSchedule(FacadeImpl.getInstance().getGroupSchedule(this.tournament));
		if (this.tournament.getBoardSchedule() == null)
			tournament.setBoardSchedule(FacadeImpl.getInstance().getBoardSchedule(this.tournament));
	}
	
	/**
	 * Finds a team given id
	 * @param id
	 */
	private void findTeamById(int id) {
		for (Team t: tournament.getTeamsList())
			if (t.getId() == id)
				this.team = t;
	}
}
