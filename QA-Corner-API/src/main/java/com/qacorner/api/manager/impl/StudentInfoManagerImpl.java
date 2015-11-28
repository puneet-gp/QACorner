package com.qacorner.api.manager.impl;

import java.sql.Connection;
import java.util.Collection;

import com.qacorner.api.db.service.StudentInfoService;
import com.qacorner.api.db.service.impl.StudentInfoServiceImpl;
import com.qacorner.api.exception.AppException;
import com.qacorner.api.exception.DbException;
import com.qacorner.api.manager.StudentInfoManager;
import com.qacorner.api.model.StudentInfo;

public class StudentInfoManagerImpl implements StudentInfoManager {

	private final Connection connection;
	private final StudentInfoService studentInfoService;
	
	public StudentInfoManagerImpl(Connection connection) {
		this.connection = connection;
		studentInfoService = new StudentInfoServiceImpl(this.connection);
	}
	
	public Collection<StudentInfo> getAllStudentInfo() {
		try {
			return studentInfoService.loadAllStudentInfo();
		} catch(DbException e) {
			throw new AppException(e);
		}
	}

	public StudentInfo getStudentInfoByEmailId(String emailId) {
		try {
			return studentInfoService.loadStudentInfoByEmailId(emailId);
		} catch(DbException e) {
			throw new AppException(e);
		}
	}

	public StudentInfo getStudentInfoByUserId(String userId) {
		try {
			return studentInfoService.loadStudentInfoByUserId(userId);
		} catch(DbException e) {
			throw new AppException(e);
		}
	}
}