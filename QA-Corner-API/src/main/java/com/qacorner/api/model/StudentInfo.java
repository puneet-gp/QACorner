package com.qacorner.api.model;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.apache.commons.lang3.StringUtils;

public class StudentInfo {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"dd/MMM/yyyy");
	private String firstName;
	private String lastName;
	private String password;
	private String schoolName;
	private String className;
	private String screenName;
	private Date dob;
	private String gender;
	private Date registrationDate;
	private String city;
	private String state;
	private String studentId;
	private String emailId;

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getFirstName() {
		return StringUtils.capitalize(firstName);
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return StringUtils.capitalize(lastName);
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getDob() {
		return DATE_FORMAT.format(dob);
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getRegistrationDate() {
		return DATE_FORMAT.format(registrationDate);
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}