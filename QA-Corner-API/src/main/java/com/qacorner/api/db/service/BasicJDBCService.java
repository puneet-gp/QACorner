package com.qacorner.api.db.service;

import java.sql.SQLException;
import java.util.List;

import com.qacorner.api.model.State;

public interface BasicJDBCService {

	public boolean emailExists(String emailId) throws SQLException;
	
	public boolean screenNameExists(String screenName) throws SQLException;
	
	public List<State> getAllStates() throws SQLException;
	
	public boolean isValidUser(String emailId, String password) throws SQLException;
	
	
}
