package interfaces.fan.webapp;

import java.awt.Desktop;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import interfaces.fan.webapp.servlet.*;

/**
 * Class to run the webapp
 */

public class Main {
	
    public static void main(String[] argv) throws Exception {
    	List<WebServlet> servlets = new ArrayList<WebServlet>();
    	final String URL = "http://localhost:8080";
    	
    	servlets.add(new MainServlet("home", "/*"));
    	
    	new ApplicationServer(8080, servlets).start();
    	
    	/* automatic default browser opening */
    	if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
    	    Desktop.getDesktop().browse(new URI(URL));
    	else {
    		Runtime runtime = Runtime.getRuntime();
            runtime.exec("xdg-open " + URL);
    	}
    }
}