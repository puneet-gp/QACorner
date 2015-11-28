package com.qacorner.api.servlets;

import static com.qacorner.api.util.QACornerUtils.toJsonString;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.qacorner.api.manager.GeneralOpertaionsManager;
import com.qacorner.api.manager.impl.GeneralOperationsManagerImpl;
import com.qacorner.api.model.BasicSuccessResponse;
import com.qacorner.api.model.ErrorResponse;
import com.qacorner.api.model.State;

/**
 * Servlet implementation class PreValidationServlet
 */
@WebServlet("/baseservlet")
public class PreValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GeneralOpertaionsManager validationManager;
	private Collection<State> states = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		validationManager = new GeneralOperationsManagerImpl((Connection) config
				.getServletContext().getAttribute("connection"));
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		boolean isIncludeStates = StringUtils.isNotEmpty(req.getParameter("is"));
		resp.setContentType("application/json");
		try {
			if (isIncludeStates) {
				states = validationManager.retrieveAllStateInfo();
				includeStates(states, resp);
				return;
			}
			boolean isState = StringUtils.isNotEmpty(req.getParameter("s"));
			if (isState) {
				includeCities(URLDecoder.decode(req.getParameter("s"), "UTF-8"), states, resp);
				return;
			}
			boolean isCheckEmail = StringUtils.isNotEmpty(req
					.getParameter("ce"));
			if (isCheckEmail) {
				validateEmailId(req.getParameter("ce"), resp);
				return;
			}
			boolean isCheckScreenName = StringUtils.isNotEmpty(req
					.getParameter("csn"));
			if (isCheckScreenName) {
				validateScreenName(req.getParameter("csn"), resp);
				return;
			}

		} catch (Exception e) {
			System.out.println("EXCeption : " + e);
		}
	}

	private void validateEmailId(String emailId, HttpServletResponse resp)
			throws Exception {
		boolean isExist = validationManager.checkEmailIDExists(emailId);
		if (isExist) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write(
					toJsonString(new ErrorResponse("Email Id already exists",
							HttpServletResponse.SC_BAD_REQUEST)));
		} else {
			resp.getWriter().write(toJsonString(new BasicSuccessResponse()));
		}

	}

	private void validateScreenName(String screenName, HttpServletResponse resp)
			throws Exception {
		boolean isExist = validationManager.checkScreenNameExists(screenName);
		if (isExist) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			String finalResponse = toJsonString(new ErrorResponse(
					"Screen name already exists",
					HttpServletResponse.SC_BAD_REQUEST));
			resp.getWriter().write(finalResponse);
		} else {
			resp.getWriter().write(toJsonString(new BasicSuccessResponse()));
		}
	}

	private void includeCities(String reqStateName, Collection<State> states,
			HttpServletResponse resp) throws Exception {
		State st = null;
		for (State state : states) {
			if (StringUtils.trim(state.getName()).equals(reqStateName)) {
				st = state;
				break;
			}
		}
		if (null != st) {
			resp.getWriter().print(toJsonString(new TreeSet<String>(st.getCities())));
		}
	}

	private void includeStates(Collection<State> states,
			HttpServletResponse resp) throws Exception {
		List<String> stateNames = new ArrayList<String>(states.size());
		for (State state : states) {
			stateNames.add(state.getName());
		}
		resp.getWriter().print(toJsonString(stateNames));
	}
}