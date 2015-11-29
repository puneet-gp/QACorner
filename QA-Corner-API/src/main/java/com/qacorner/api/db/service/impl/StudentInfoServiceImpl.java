package com.qacorner.api.db.service.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.qacorner.api.db.service.StudentInfoService;
import com.qacorner.api.exception.DbException;
import com.qacorner.api.model.StudentInfo;
import com.qacorner.api.util.QACornerUtils;

public class StudentInfoServiceImpl implements StudentInfoService {

	private Connection connection;
	private String loadAllRecords;
	private String loadRecordByEmailId;
	private String loadRecordByUserId;
	private String saveStudentInfo;

	private static final String[] COLUMN_NAMES = { "first_name", "last_name",
			"student_password", "registration_date", "qacorner_student_id",
			"email_id" };
	private static final Logger LOGGER = Logger.getLogger(StudentInfoServiceImpl.class);
	
	public StudentInfoServiceImpl(Connection connection) {
		LOGGER.debug("Initializing StudentInfoService");
		this.connection = connection;
		this.loadAllRecords = "SELECT * FROM student_info";
		this.loadRecordByEmailId = "SELECT * FROM student_info WHERE email_id=?";
		this.loadRecordByUserId = "SELECT * FROM student_info WHERE qacorner_student_id=?";
		this.saveStudentInfo = "INSERT INTO student_info(" + StringUtils.join(COLUMN_NAMES, ",") + ") VALUES(?,?,?,?,?,?)";
	}

	public Collection<StudentInfo> loadAllStudentInfo() throws DbException {
		try {
			LOGGER.debug("Loading all student info");
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
			LOGGER.debug("Loading student info for email id : " + emailId);
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
			LOGGER.debug("Loading student info for user id : " + userId);
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

	public void saveStudentInfo(StudentInfo info) throws DbException {
		try {
			LOGGER.debug("Saving student info : " + QACornerUtils.toJsonString(info));
			PreparedStatement stmt = connection.prepareStatement(saveStudentInfo);
			stmt.setString(1, info.getFirstName());
			stmt.setString(2, info.getLastName());
			stmt.setString(3, info.getPassword());
			stmt.setDate(4, new Date(info.getRegistrationDate().getTime()));
			stmt.setString(5, info.getStudentId());
			stmt.setString(6, info.getEmailId());
			stmt.execute();
		} catch (SQLException e) {
			LOGGER.error("Failed to save student info for emailId : " + info.getEmailId());
			throw new DbException(e);
		}
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