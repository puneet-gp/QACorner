package com.qacorner.api.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.qacorner.api.manager.StudentInfoManager;
import com.qacorner.api.manager.GeneralOpertaionsManager;
import com.qacorner.api.manager.impl.StudentInfoManagerImpl;
import com.qacorner.api.manager.impl.GeneralOperationsManagerImpl;
import com.qacorner.api.model.StudentInfo;

/**
 * Servlet implementation class AccountServlet
 */

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GeneralOpertaionsManager vaildationManager;
	private StudentInfoManager studentInfoManager;
	private static final Logger LOGGER = Logger.getLogger(AccountServlet.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		LOGGER.debug("initializing Account servlet ");
		Connection connection = (Connection) config.getServletContext().getAttribute("connection");
		vaildationManager = new GeneralOperationsManagerImpl(connection);
		studentInfoManager = new StudentInfoManagerImpl(connection);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (StringUtils.isNotBlank(request.getParameter("login"))) {
			String emailId = request.getParameter("std_email");
			String password = request.getParameter("std_password");
			LOGGER.debug(String.format("Logging in with email Id : %s & password : %s", emailId, password));
			try {
				doLogin(request, response, request.getParameter("std_email"),
						request.getParameter("std_password"));
			} catch (Exception e) {
				System.out.println("Exception occured : " + e);
				e.printStackTrace();
			}
		} else if (StringUtils.isNotBlank(request.getParameter("join"))) {
			doRegister();
		}

	}

	private void doRegister() {

	}

	private void doLogin(HttpServletRequest request,
			HttpServletResponse response, String emailId, String password) throws Exception {
		if (!vaildationManager.isValidUser(emailId, password)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			request.setAttribute("login_error", "Username / Password incorrect. Please try again");
			dispatcher.forward(request, response);
		}
		else {
			StudentInfo info = studentInfoManager.getStudentInfoByEmailId(emailId);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Home.jsp");
			request.setAttribute("student_info", info);
			dispatcher.forward(request, response);
		}
	}
}