package com.qacorner.api.db.service;

import java.util.Collection;

import com.qacorner.api.exception.DbException;
import com.qacorner.api.model.StudentInfo;

public interface StudentInfoService {

	Collection<StudentInfo> loadAllStudentInfo() throws DbException;
	
	StudentInfo loadStudentInfoByEmailId(String emailId) throws DbException;
	
	StudentInfo loadStudentInfoByUserId(String userId) throws DbException;
	
	StudentInfo saveStudentInfo(StudentInfo info) throws DbException;
}