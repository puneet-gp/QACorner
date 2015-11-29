var QACorner = function() {
	this.baseUrl = "baseservlet";
	this.includeStatesFlag = "is";
	this.isCheckEmail = "ce";
	this.isCheckScreenName = "csn";
	this.stateInfo = [];
	this.populateStates();
};

QACorner.prototype.getBaseUrl = function() {
	return this.baseUrl;
};

QACorner.prototype.populateStates = function() {
	var me = this;
	$.ajax({
		url : this.getBaseUrl() + "?" + this.includeStatesFlag + "=true",
		method : "GET",
		success : function(data) {
			console.log(data);
			me.states = data;
		}
	});
};

QACorner.prototype.getStates = function() {
	return this.states;
};

QACorner.prototype.getCities = function(stateName) {
	var states = this.getStates();
	for ( var st in states) {
		if (states[st]["name"] === stateName) {
			return states[st]["cities"];
		}
	}
};

QACorner.prototype.eqauls = function(str1, str2) {
	return (typeof str1 === "string" && typeof str2 === "string" && str1 === str2);
};

QACorner.prototype.equalsIgnoreCase = function(str1, str2) {
	return (typeof str1 === "string" && typeof str2 === "string" && str1
			.toUpperCase() === str2.toUpperCase());
};