package interfaces.fan.webapp.servlet;

import javax.servlet.http.HttpServlet;


/**
 * Abstract servlet for defining attributes used by concrete servlets
 * @param name base path of webapp
 * @param url base path of a servlet
 */

@SuppressWarnings("serial")
public abstract class WebServlet extends HttpServlet {
	private String name;
	private String url;

	public WebServlet(String name, String url) {
		super();
		this.name = name;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}