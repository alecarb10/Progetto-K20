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

@SuppressWarnings("serial")
public class HomeServlet extends WebServlet {

	private List<Tournament> tournaments;
	private Tournament tournament;
	private Team team;
	
	public HomeServlet(String name, String url) {
		super(name, url);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (req.getPathInfo().equals("/") || req.getPathInfo().equals("/home.html")) {
			try {
				tournaments = FacadeImpl.getInstance().getAllTournaments();
				resp.getWriter().write(Rythm.render("home.html", tournaments));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (req.getPathInfo().equals("/teams") || req.getPathInfo().equals("/teams.html")) {
			resp.getWriter().write(Rythm.render("teams.html", tournament));
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
			} catch (SQLException e) {
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
	}
}
