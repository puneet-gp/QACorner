package com.qacorner.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class PreValidationServlet
 */
public class PreValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     * @throws NamingException 
     * @throws SQLException 
     */
	private final Connection connection;
    public PreValidationServlet() throws NamingException, SQLException {
    	Context context = new InitialContext();
		Context environmentContext = (Context) context
				.lookup("java:comp/env");
		String dataResourceName = "jdbc/JCGExampleDB";
		DataSource dataSource = (DataSource) environmentContext
				.lookup(dataResourceName);
		connection = dataSource.getConnection();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
