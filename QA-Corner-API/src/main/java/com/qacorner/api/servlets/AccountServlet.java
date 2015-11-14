package com.qacorner.api.servlets;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.qacorner.api.manager.ValidationManager;
import com.qacorner.api.manager.impl.ValidationManagerImpl;

/**
 * Servlet implementation class AccountServlet
 */

@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ValidationManager vaildationManager;

	@Override
	public void init(ServletConfig config) throws ServletException {
		vaildationManager = new ValidationManagerImpl((Connection) config.getServletContext().getAttribute("connection"));
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (StringUtils.isNotBlank(request.getParameter("login"))) {
			
			System.out.println("Context Path : " + request.getContextPath());
			System.out.println("REQUEST URI : " + request.getRequestURI());
			System.out.println("Servlet Path :" + request.getServletPath());
			try {
				doLogin(request,response,request.getParameter("std_email"),
						request.getParameter("std_password"));
			} catch (Exception e) {
				System.out.println("Exception occured : " + e);
			}
		} else if (StringUtils.isNotBlank(request.getParameter("join"))) {
			doRegister();
		}

	}

	private void doRegister() {

	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response, String emailId, String password) throws Exception {
		if(!vaildationManager.isValidUser(emailId, password)) {
	}
	}
}
