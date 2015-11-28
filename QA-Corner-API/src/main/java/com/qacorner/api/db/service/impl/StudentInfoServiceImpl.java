package com.qacorner.api.db.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.qacorner.api.db.service.StudentInfoService;
import com.qacorner.api.exception.DbException;
import com.qacorner.api.model.StudentInfo;

public class StudentInfoServiceImpl implements StudentInfoService {

	private Connection connection;
	private String loadAllRecords;
	private String loadRecordByEmailId;
	private String loadRecordByUserId;
	private static final String[] COLUMN_NAMES = { "first_name", "last_name",
			"student_password", "school_name", "class_name", "screen_name",
			"dob", "gender", "registration_date", "city", "state",
			"qacorner_student_id", "email_id" };
	private static final Logger LOGGER = Logger.getLogger(StudentInfoServiceImpl.class);
	
	public StudentInfoServiceImpl(Connection connection) {
		LOGGER.debug("Initializing StudentInfoService");
		this.connection = connection;
		this.loadAllRecords = "SELECT * FROM student_info";
		this.loadRecordByEmailId = "SELECT * FROM student_info WHERE email_id=?";
		this.loadRecordByUserId = "SELECT * FROM student_info WHERE qacorner_student_id=?";
	}

	public Collection<StudentInfo> loadAllStudentInfo() throws DbException {
		try {
			PreparedStatement stmt = connection.prepareStatement(loadAllRecords);
			ResultSet rs = stmt.executeQuery();	
			List<StudentInfo> list = new ArrayList<StudentInfo>();
			while(rs.next()) {
				list.add(prepapreStudentInfo(rs));
			}
			rs.close();
			stmt.close();
			return list;
		} catch(SQLException e) {
			LOGGER.error("Failed to load all student info");
			throw new DbException(e);
		}
	}

	public StudentInfo loadStudentInfoByEmailId(String emailId) throws DbException {
		try {
			PreparedStatement stmt = connection.prepareStatement(loadRecordByEmailId);
			stmt.setString(1, emailId);
			ResultSet rs = stmt.executeQuery();
			StudentInfo info = null;
			if(rs.next()) {
				info = prepapreStudentInfo(rs);
			}
			rs.close();
			stmt.close();
			return info;
		} catch(SQLException e) {
			LOGGER.error("Failed to load student info by email Id : " + emailId);
			throw new DbException(e);
		}
	}

	public StudentInfo loadStudentInfoByUserId(String userId)throws DbException {
		try {
			PreparedStatement stmt = connection.prepareStatement(loadRecordByUserId);
			stmt.setString(1, userId);
			StudentInfo info = null;
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				info = prepapreStudentInfo(rs);
			}
			rs.close();
			stmt.close();
			return info;
		} catch(SQLException e) {
			LOGGER.error("Failed to load student info by userId : " + userId);
			throw new DbException(e);
		}
	}

	public StudentInfo saveStudentInfo(StudentInfo info) throws DbException {
		// TODO Auto-generated method stub
		return null;
	}

	private StudentInfo prepapreStudentInfo(ResultSet rs) throws SQLException {
		StudentInfo info = new StudentInfo();
		info.setFirstName(rs.getString("first_name"));
		info.setLastName(rs.getString("last_name"));
		info.setPassword(rs.getString("student_password"));
		info.setSchoolName(rs.getString("school_name"));
		info.setClassName(rs.getString("class_name"));
		info.setScreenName(rs.getString("screen_name"));
		info.setDob(rs.getDate("dob"));
		info.setGender(rs.getString("gender"));
		info.setRegistrationDate(rs.getDate("registration_date"));
		info.setCity(rs.getString("city"));
		info.setState(rs.getString("state"));
		info.setStudentId(rs.getString("qacorner_student_id"));
		info.setEmailId(rs.getString("email_id"));
		return info;
	}
}