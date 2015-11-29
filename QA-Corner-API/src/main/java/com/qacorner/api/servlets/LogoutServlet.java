package com.qacorner.api.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.qacorner.api.model.StudentInfo;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String BUTTON_CSS = "background:none!important;border:none; padding:0!important;font: inherit;border-bottom:1px solid #444; cursor: pointer;";
  	private static final Logger LOGGEER = Logger.getLogger(LogoutServlet.class);
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGEER.debug("LogoutServlet#doGet");
		response.getWriter()
				.write("<html><body><form method='POST' action='logout'><input name='logout_button' style='"+ BUTTON_CSS +"' type='submit' value='Logout'/></form></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LOGGEER.debug("LogoutServlet#doPost");
		if(StringUtils.isNotEmpty(request.getParameter("logout_button"))) {
			HttpSession session = request.getSession(false);
			if(session != null) {
				StudentInfo info = (StudentInfo)session.getAttribute("logged_in_user");
				LOGGEER.info("Removing session for emailId : " + info.getEmailId());
				session.removeAttribute("logged_in_user");
				session.invalidate();
				response.sendRedirect("index.jsp?lo=true");
				return;
			}
		}
	}
}
