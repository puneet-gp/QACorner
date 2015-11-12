package com.qacorner.api.manager;

public interface ValidationManager {

	public boolean checkEmailIDExists(String emailId);
	
	public boolean checkScreenNameExists(String screenName);
}
