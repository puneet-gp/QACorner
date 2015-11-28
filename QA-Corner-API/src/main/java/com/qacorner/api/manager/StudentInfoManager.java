package com.qacorner.api.manager;

import java.util.Collection;

import com.qacorner.api.model.StudentInfo;

public interface StudentInfoManager {
	
	Collection<StudentInfo> getAllStudentInfo() ;
	
	StudentInfo getStudentInfoByEmailId(String emailId) ;
	
	StudentInfo getStudentInfoByUserId(String userId) ;
}