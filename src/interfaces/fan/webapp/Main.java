package interfaces.fan.webapp;

import java.util.ArrayList;
import java.util.List;

import interfaces.fan.webapp.servlet.*;

public class Main {
    public static void main(String[] argv) throws Exception {
    	List<WebServlet> servlets = new ArrayList<WebServlet>();
    	
    	servlets.add(new HomeServlet("home", "/*"));
    	
    	new ApplicationServer(8080, servlets).start();
    }
}