package com.qacorner.api.manager.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.qacorner.api.db.service.BasicJDBCService;
import com.qacorner.api.db.service.impl.BasicJDBCServiceImpl;
import com.qacorner.api.manager.ValidationManager;
import com.qacorner.api.model.State;

public class ValidationManagerImpl implements ValidationManager {

	private BasicJDBCService basicJDBCService;
	
	public ValidationManagerImpl(Connection connection) {
		basicJDBCService = new BasicJDBCServiceImpl(connection);
	}
	
	public boolean checkEmailIDExists(String emailId) throws SQLException {
		return basicJDBCService.emailExists(emailId);
	}

	public boolean checkScreenNameExists(String screenName) throws Exception {
		return basicJDBCService.screenNameExists(screenName);
	}

	public List<State> retrieveAllStateInfo() throws SQLException {
		return basicJDBCService.getAllStates();
	}

	public boolean isValidUser(String emailId, String password) throws SQLException {
		return basicJDBCService.isValidUser(emailId, password);
	}
}
