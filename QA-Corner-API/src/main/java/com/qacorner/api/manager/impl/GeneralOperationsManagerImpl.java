package com.qacorner.api.manager.impl;

import java.sql.Connection;
import java.util.List;

import com.qacorner.api.db.service.GeneralOperationsService;
import com.qacorner.api.db.service.impl.GeneralOperationsServiceImpl;
import com.qacorner.api.exception.AppException;
import com.qacorner.api.exception.DbException;
import com.qacorner.api.manager.GeneralOpertaionsManager;
import com.qacorner.api.model.State;

public class GeneralOperationsManagerImpl implements GeneralOpertaionsManager {

	private GeneralOperationsService basicJDBCService;
	
	public GeneralOperationsManagerImpl(Connection connection) {
		basicJDBCService = new GeneralOperationsServiceImpl(connection);
	}
	
	public boolean checkEmailIDExists(String emailId) {
		try {
			return basicJDBCService.emailExists(emailId);
		} catch(DbException e) {
			throw new AppException(e);
		}
	}

	public boolean checkScreenNameExists(String screenName)  { 
		try {
			return basicJDBCService.screenNameExists(screenName);
		} catch(DbException e) {
			throw new AppException(e);
		}
	}

	public List<State> retrieveAllStateInfo() {
		try {
			return basicJDBCService.getAllStates();
		} catch(DbException e) {
			throw new AppException(e);
		}
	}

	public boolean isValidUser(String emailId, String password) {
		try {
			return basicJDBCService.isValidUser(emailId, password);
		} catch(DbException e) {
			throw new AppException(e);
		}
	}
}