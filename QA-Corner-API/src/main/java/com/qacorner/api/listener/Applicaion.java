package com.qacorner.api.listener;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

@WebListener
public class Applicaion implements ServletContextListener {
	
	private static final Logger LOGGER = Logger.getLogger(Applicaion.class);
	
	public void contextInitialized(ServletContextEvent sce) {
		LOGGER.info("Starting Appplication ");
		ServletContext servletContext = sce.getServletContext();
		try {
		Context context = new InitialContext();
		Context environmentContext = (Context) context
				.lookup("java:comp/env");
		String dataResourceName = "jdbc/JCGExampleDB";
		DataSource dataSource = (DataSource) environmentContext
				.lookup(dataResourceName);
		LOGGER.info("Setting up DB Connection");
		Connection connection = dataSource.getConnection();
		servletContext.setAttribute("connection", connection);
		} catch(SQLException exec) {
			LOGGER.error("Unable to start application cause of : {} ", exec);
		} catch (NamingException exec) {
			LOGGER.error("Unable to start application cause of : {}", exec);
		}
		LOGGER.info("Logging init parameters : ");
		System.out.println("===========================");
		Enumeration<String> params = servletContext.getInitParameterNames();
		while(params.hasMoreElements()) {
			String name = params.nextElement();
			String value = servletContext.getInitParameter(name);
			System.out.println("\n" + name + " = " + value + "\n");
		}
		System.out.println("===========================");
		LOGGER.info("Application started");
	}
	
	public void contextDestroyed(ServletContextEvent sce) {
		LOGGER.info("Destroying application instance at : " + new Date());
		Connection connection = (Connection)(sce.getServletContext().getAttribute("connection"));
		if(connection != null) {
			try {
				LOGGER.info("Closing DB connection");
				connection.close();
			} catch (SQLException e) {
				LOGGER.error("Failed to close DB connection");
			}
		}
	}
}