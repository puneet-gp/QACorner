package com.qacorner.api.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.qacorner.api.manager.GeneralOpertaionsManager;
import com.qacorner.api.manager.StudentInfoManager;
import com.qacorner.api.manager.impl.GeneralOperationsManagerImpl;
import com.qacorner.api.manager.impl.StudentInfoManagerImpl;
import com.qacorner.api.model.StudentInfo;
import com.qacorner.api.util.QACornerUtils;

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
		Connection connection = (Connection) config.getServletContext()
				.getAttribute("connection");
		vaildationManager = new GeneralOperationsManagerImpl(connection);
		studentInfoManager = new StudentInfoManagerImpl(connection);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (StringUtils.isNotBlank(request.getParameter("login"))) {
			String emailId = request.getParameter("std_email");
			String password = request.getParameter("std_password");
			LOGGER.debug(String.format(
					"Logging in with email Id : %s & password : %s", emailId,
					password));
			doLogin(request, response, request.getParameter("std_email"),
					request.getParameter("std_password"));

		} else if (StringUtils.isNotBlank(request.getParameter("join"))) {
			String firstName = request.getParameter("reg_fname");
			String lastName = request.getParameter("reg_lname");
			String emailId = request.getParameter("reg_email_id");
			String password = request.getParameter("reg_password");
			if (vaildationManager.checkEmailIDExists(emailId)) {
				LOGGER.error("Recieved duplicate email id : " + emailId);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("/");
				request.setAttribute("email_id_error",
						"EmailId already exist. Please try with different one");
				dispatcher.forward(request, response);
				return;
			}
			LOGGER.debug("Registering a user with emailId : " + emailId);
			StudentInfo info = doRegister(firstName, lastName, emailId, password);
			HttpSession session = request.getSession();
			session.setAttribute("logged_in_user", info);
			response.sendRedirect("profile.jsp?reg=t");
			return;
		}
	}

	private StudentInfo doRegister(String firstName, String lastName, String emailId, String password) {
		StudentInfo info = new StudentInfo();
		info.setFirstName(firstName);
		info.setLastName(lastName);
		info.setEmailId(emailId);
		info.setPassword(password);
		info.setRegistrationDate(new Date());
		info.setStudentId(QACornerUtils.getUID());
		studentInfoManager.save(info);
		return info;
	}

	private void doLogin(HttpServletRequest request,
			HttpServletResponse response, String emailId, String password)
			throws ServletException, IOException {
		if (!vaildationManager.isValidUser(emailId, password)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/");
			request.setAttribute("login_error",
					"Username / Password incorrect. Please try again");
			dispatcher.forward(request, response);
			return;
		} else {
			StudentInfo info = studentInfoManager
					.getStudentInfoByEmailId(emailId);
			HttpSession session = request.getSession();
			session.setAttribute("logged_in_user", info);
			response.sendRedirect("Home.jsp");
			return;
		}
	}
}