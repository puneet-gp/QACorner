package com.qacorner.api.listener;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@WebListener
public class Applicaion implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce) {
		try {
		Context context = new InitialContext();
		Context environmentContext = (Context) context
				.lookup("java:comp/env");
		String dataResourceName = "jdbc/JCGExampleDB";
		DataSource dataSource = (DataSource) environmentContext
				.lookup(dataResourceName);
		Connection connection = dataSource.getConnection();
		sce.getServletContext().setAttribute("connection", connection);
		} catch(Exception exec) {
			System.err.print("Error " + exec);
		}
		
	}
	
	
	public void contextDestroyed(ServletContextEvent sce) {}

	

}
