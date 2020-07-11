package interfaces.fan.webapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.rythmengine.Rythm;

@SuppressWarnings("serial")
public class HomeServlet extends WebServlet {

	public HomeServlet(String name, String url) {
		super(name, url);
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if (req.getPathInfo().equals("/") || req.getPathInfo().equals("/home.html"))
			resp.getWriter().write(Rythm.render("home.html"));
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.sendRedirect("/");
	}
}
