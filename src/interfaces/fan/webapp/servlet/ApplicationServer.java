package interfaces.fan.webapp.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.rythmengine.Rythm;

/**
 * Manages servlets and pages style.
 * @param port port used by Server to accept requests from clients (browser)
 * @param servlets list of servlets used to manage requests
 * @param server server to manage the webapp
 */

public class ApplicationServer {
	private int port;
	private List<WebServlet> servlets;
	private Server server;

	public ApplicationServer(int port, List<WebServlet> servlets) {
		this.port = port;
		this.servlets = servlets;
	}

	public void start(){
		initTemplateEngine();
		server = new Server(port);
		
		ServletContextHandler handler = new ServletContextHandler();		
		
		for (WebServlet servlet : servlets) 
			handler.addServlet(new ServletHolder(servlet), servlet.getUrl());
		
		addStaticFileServing(handler);
		server.setHandler(handler);		
		
		try {
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stop(){
		try {
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addStaticFileServing(ServletContextHandler handler) {
		ServletHolder holderPwd = new ServletHolder("default", new DefaultServlet());
		holderPwd.setInitParameter("resourceBase", "resources/");
		holderPwd.setInitParameter("dirAllowed", "false");
		holderPwd.setInitParameter("pathInfoOnly", "true");
		handler.addServlet(holderPwd, "/statics/*");
	}

	private void initTemplateEngine() {
		Map<String, Object> conf = new HashMap<>();
		conf.put("home.template", "interfaces/fan/webapp/pages");
		Rythm.init(conf);
	}
}