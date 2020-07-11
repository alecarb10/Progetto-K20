package interfaces.fan.webapp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

import services.persistence.dao.impl.FacadeImpl;

import model.tournament.Tournament;

@SuppressWarnings("serial")
public class HomeServlet extends WebServlet {

	private List<Tournament> tournaments;
	
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
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (req.getPathInfo().equals("/view")) {
			Tournament tournament = null;;
			for (Tournament t: tournaments)
				if (t.getId() == Integer.parseInt(req.getParameter("id")))
					tournament = t;
			try {
				tournament.addTeams(FacadeImpl.getInstance().getTeamsByTournament(tournament));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			resp.getWriter().write(Rythm.render("tournamentdetail.html", tournament));	
		} 
	}
}
