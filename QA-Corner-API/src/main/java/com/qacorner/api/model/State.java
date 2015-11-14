package com.qacorner.api.model;

import java.util.List;

import com.qacorner.api.util.QACornerUtils;

public class State {

	private String name;
	private String stateId;
	private List<String> cities;

	public State() {
	}

	public State(String name, String stateId, List<String> cities) {
		this.name = name;
		this.stateId = stateId;
		this.cities = cities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}

	@Override
	public String toString() {
		return QACornerUtils.toJsonString(this);
	}
}
