package com.qacorner.api.db.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qacorner.api.db.service.BasicJDBCService;
import com.qacorner.api.model.State;

public class BasicJDBCServiceImpl implements BasicJDBCService {

	private Connection connection;
	private String emailExistsStmt;
	private String screenNameExistsStmt;
	private String loadAllLocations;
	private PreparedStatement loadValidUser;

	public BasicJDBCServiceImpl(Connection connection) {
		this.connection = connection;
		emailExistsStmt = "SELECT email_id FROM student_info WHERE email_id='%s'";
		screenNameExistsStmt = "SELECT screen_name FROM student_info where screen_name='%s'";
		loadAllLocations = "SELECT * FROM india_locations ORDER BY state";
		try {
			loadValidUser = connection.prepareStatement("SELECT email_id, student_password FROM student_info where email_id=? AND student_password=?");
		} catch (SQLException e) {
			System.out.println("Exception occured : " + e);
		}

	}

	public boolean emailExists(String emailId) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(String.format(emailExistsStmt, emailId));
		boolean flag = stmt.executeQuery().next();
		stmt.close();
		return flag;
	}

	public boolean screenNameExists(String screenName) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(String.format(screenNameExistsStmt, screenName));
		boolean flag = stmt.executeQuery().next();
		stmt.close();
		return flag;
	}

	public List<State> getAllStates() throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(loadAllLocations);
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
	}

	public boolean isValidUser(String emailId, String password)
			throws SQLException {
		loadValidUser.setString(1, emailId);
		loadValidUser.setString(2, password);
		boolean flag = loadValidUser.executeQuery().next();
		loadValidUser.close();
		return flag;
	}
}