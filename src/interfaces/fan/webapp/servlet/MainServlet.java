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
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (req.getPathInfo().equals("/") || req.getPathInfo().equals("/home.html")) {
			try {
				tournaments = FacadeImpl.getInstance().getAllTournaments();
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
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (req.getPathInfo().equals("/view")) {
			for (Tournament t: tournaments)
				if (t.getId() == Integer.parseInt(req.getParameter("id")))
					tournament = t;
			try {
				if (tournament.getTeamsList().size() == 0)
					tournament.addTeams(FacadeImpl.getInstance().getTeamsByTournament(tournament));
				if (tournament.getGroupSchedule() == null)
					tournament.setGroupSchedule(FacadeImpl.getInstance().getGroupSchedule(tournament));
				if (tournament.getBoardSchedule() == null)
					tournament.setBoardSchedule(FacadeImpl.getInstance().getBoardSchedule(tournament));
			} 
			catch (SQLException | NullPointerException e) {
				resp.getWriter().write(Rythm.render("error.html"));
				e.printStackTrace();
			}
			
			resp.getWriter().write(Rythm.render("tournament-detail.html", tournament));	
		} 
		
		if (req.getPathInfo().equals("/viewteams")) {
			for (Team t: tournament.getTeamsList())
				if (t.getId() == Integer.parseInt(req.getParameter("id")))
					team = t;
		
			resp.getWriter().write(Rythm.render("team-detail.html", team));	
		} 
		
		if (req.getPathInfo().equals("/group")) {
			int day = Integer.parseInt(req.getParameter("daySelect"));
			resp.getWriter().write(Rythm.render("group.html", tournament, tournament.getGroupSchedule().get(day - 1)));
		}
	}
}
