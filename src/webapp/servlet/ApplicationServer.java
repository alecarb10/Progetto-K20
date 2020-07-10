package webapp.servlet;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.DefaultSessionCache;
import org.eclipse.jetty.server.session.FileSessionDataStore;
import org.eclipse.jetty.server.session.SessionCache;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.rythmengine.Rythm;

public class ApplicationServer {
	private int port;
	private List<WebServlet> servlet;
	private Server server;

	public ApplicationServer(int port, List<WebServlet> servlet) {
		this.port = port;
		this.servlet = servlet;
	}

	public void start(){
		initTemplateEngine();
		server = new Server(port);
		
		ServletContextHandler handler = new ServletContextHandler();		
		
		for (WebServlet servlet2 : servlet) {
			handler.addServlet(new ServletHolder(servlet2), servlet2.getUrl());
		}
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
		holderPwd.setInitParameter("resourceBase", "./src/main/resources/statics");
		holderPwd.setInitParameter("dirAllowed", "false");
		holderPwd.setInitParameter("pathInfoOnly", "true");
		handler.addServlet(holderPwd, "/statics/*");
	}

	private void initTemplateEngine() {
		Map<String, Object> conf = new HashMap<>();
		conf.put("home.template", "templates");
		Rythm.init(conf);
	}
}