package org.testunited.webapi;

import java.util.List;


public class TestResultSubmission {
private String sessionName;
private String environmentName;
private Application application;

public String getSessionName() {
	return sessionName;
}
public String getEnvironmentName() {
	return environmentName;
}
public void setEnvironmentName(String environmentName) {
	this.environmentName = environmentName;
}
public void setSessionName(String sessionName) {
	this.sessionName = sessionName;
}
public List<TestResult> getTestResults() {
	return testResults;
}
public void setTestResults(List<TestResult> testResults) {
	this.testResults = testResults;
}

public Application getApplication() {
	return application;
}

public void setApplication(Application application) {
	this.application = application;
}
private List<TestResult> testResults;
}
