package com.qacorner.api.db.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qacorner.api.db.service.GeneralOperationsService;
import com.qacorner.api.exception.DbException;
import com.qacorner.api.model.State;

public class GeneralOperationsServiceImpl implements GeneralOperationsService {

	private Connection connection;
	private String emailExistsStmt;
	private String screenNameExistsStmt;
	private String loadAllLocations;
	private String loadValidUser;
	private static final Logger LOGGER = Logger
			.getLogger(GeneralOperationsServiceImpl.class);

	public GeneralOperationsServiceImpl(Connection connection) {
		LOGGER.debug("Initializing BasicJDBCService");
		this.connection = connection;
		emailExistsStmt = "SELECT email_id FROM student_info WHERE email_id='%s'";
		screenNameExistsStmt = "SELECT screen_name FROM student_info where screen_name='%s'";
		loadAllLocations = "SELECT * FROM india_locations ORDER BY state";
		loadValidUser = "SELECT email_id, student_password FROM student_info where email_id=? AND student_password=?";
	}

	public boolean emailExists(String emailId) throws DbException {
		try {
			LOGGER.debug("Preparing statement for email existance for : " + emailId);
			PreparedStatement stmt = connection.prepareStatement(String.format(
					emailExistsStmt, emailId));
			boolean flag = stmt.executeQuery().next();
			stmt.close();
			return flag;
		} catch (SQLException e) {
			LOGGER.error("Failed to validate email : " + emailId, e);
			throw new DbException(e);
		}
	}

	public boolean screenNameExists(String screenName) throws DbException {
		try {
			LOGGER.debug("Preparing statement for screenName existance for : " + screenName);
			PreparedStatement stmt = connection.prepareStatement(String.format(
					screenNameExistsStmt, screenName));
			boolean flag = stmt.executeQuery().next();
			stmt.close();
			return flag;
		} catch (SQLException e) {
			LOGGER.error("Failed to validate screenName : " + screenName);
			throw new DbException(e);
		}

	}

	public List<State> getAllStates() throws DbException {
		try {
			LOGGER.debug("Preparing statement for fetching state information");
			PreparedStatement stmt = connection
					.prepareStatement(loadAllLocations);
			ResultSet rs = stmt.executeQuery();
			String currentStateName = null;
			String currentStateId = null;
			List<String> cityList = new ArrayList<String>();
			List<State> stateList = new ArrayList<State>();
			while (rs.next()) {
				String stateName = rs.getString("state");
				String stateId = rs.getString("state_id");
				String cityName = rs.getString("city");
				if (currentStateName == null) {
					currentStateName = stateName;
					currentStateId = stateId;
				}
				if (stateName.equals(currentStateName)) {
					cityList.add(cityName);
				}
				if (!stateName.equals(currentStateName) || rs.isLast()) {
					List<String> refCityList = new ArrayList<String>(cityList);
					State state = new State(currentStateName, currentStateId,
							refCityList);
					stateList.add(state);
					currentStateName = stateName;
					currentStateId = stateId;
					cityList.clear();
				}
			}
			stmt.close();
			rs.close();
			return stateList;
		} catch (SQLException e) {
			LOGGER.error("Failed to retrieve state information");
			throw new DbException(e);
		}
	}

	public boolean isValidUser(String emailId, String password)
			throws DbException {
		try {
			LOGGER.debug("Preparing statement to check valid user with emailId : " + emailId + " & Password : " + password);
			PreparedStatement stmt = connection.prepareStatement(loadValidUser);
			stmt.setString(1, emailId);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			boolean flag = rs.next();
			rs.close();
			stmt.close();
			return flag;
		} catch (SQLException e) {
			LOGGER.error("Failed to validate user with given emailId : "
					+ emailId + " and password : " + password);
			throw new DbException(e);
		}
	}
}