package com.qacorner.api.manager;

import java.util.List;

import com.qacorner.api.model.State;

public interface ValidationManager {

	public boolean checkEmailIDExists(String emailId) throws Exception;
	
	public boolean checkScreenNameExists(String screenName) throws Exception;
	
	public List<State> retrieveAllStateInfo() throws Exception;
	
	public boolean isValidUser(String emailId, String password) throws Exception;
}