package com.qacorner.api.db.service;

import java.util.List;

import com.qacorner.api.exception.DbException;
import com.qacorner.api.model.State;

public interface GeneralOperationsService {

	public boolean emailExists(String emailId) throws DbException;
	
	public boolean screenNameExists(String screenName) throws DbException;
	
	public List<State> getAllStates() throws DbException;
	
	public boolean isValidUser(String emailId, String password) throws DbException;
	
	
}
