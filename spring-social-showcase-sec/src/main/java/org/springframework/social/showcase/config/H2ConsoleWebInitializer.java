package org.springframework.social.showcase.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;

public class H2ConsoleWebInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		WebServlet h2Servlet = new WebServlet();
		Dynamic dynamic = container.addServlet("H2Console", h2Servlet);
		dynamic.addMapping("/admin/h2/*");
	}
	
}
