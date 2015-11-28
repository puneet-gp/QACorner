package com.qacorner.api.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.qacorner.api.email.sender.AEmailSender;
import com.qacorner.api.email.sender.HtmlEmailSender;
import com.qacorner.api.exception.AppException;

@WebServlet("/password_recovery")
public class EmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String EMAIL_FROM;
	private static String EMAIL_ID_PASSWORD;
	private static String SMTP_HOST;
	private static String SUBJECT;
	private static String GLOBAL_RANDOM_PASSWORD = "QACorner4";
	private static final Logger LOGGER = Logger.getLogger(EmailServlet.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		EMAIL_FROM = config.getServletContext().getInitParameter("email.config.sender.emailId");
		EMAIL_ID_PASSWORD = config.getServletContext().getInitParameter("email.config.sender.password");
		SMTP_HOST = config.getServletContext().getInitParameter("email.config.smptp.host");
		SUBJECT = "Password Recovery - QA Corner";
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String emailType = request.getParameter("email_type");
		if(StringUtils.isNotEmpty(emailType) && emailType.equals("password_recovery")) {
			String emailTo = request.getParameter("email_to");
			sendPasswordEmail(emailTo, request.getRequestURL().toString());
			RequestDispatcher dispatcher = request.getRequestDispatcher("/passwordrecovery.jsp");
			request.setAttribute("req_msg", "Password has been sent successfully to : <strong> " + emailTo + "</strong>");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().print("Password successfull resetted");
	}
	
	private void sendPasswordEmail(String emailTo, String servletPath) {
		String emailContent = null;
		String randomPassword = GLOBAL_RANDOM_PASSWORD;
		try {
			emailContent = IOUtils.toString(EmailServlet.class.getResourceAsStream("/PRTemplate.txt"));
			emailContent = String.format(emailContent,  randomPassword = RandomStringUtils.random(10, true, true), servletPath + "?id=hello");
		} catch (IOException e) {
			LOGGER.error("Failed to send password recovery email to : " + emailTo);
			throw new AppException("Failed to send password recovery email to : " + emailTo);
		}
		LOGGER.debug("Sending password recovery email to : " + emailTo);
		AEmailSender sender = new HtmlEmailSender(EMAIL_FROM, EMAIL_ID_PASSWORD, SMTP_HOST, SUBJECT, emailTo, emailContent);
		sender.sendEmail();
	}
}