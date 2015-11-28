package com.qacorner.api.manager;

import java.util.List;

import com.qacorner.api.model.State;

public interface GeneralOpertaionsManager {

	public boolean checkEmailIDExists(String emailId);
	
	public boolean checkScreenNameExists(String screenName);
	
	public List<State> retrieveAllStateInfo();
	
	public boolean isValidUser(String emailId, String password);
}