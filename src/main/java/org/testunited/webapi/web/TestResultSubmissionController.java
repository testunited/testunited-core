package org.testunited.webapi.web;

import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.testunited.webapi.TestCase;
import org.testunited.webapi.TestResult;
import org.testunited.webapi.TestResultSubmission;
import org.testunited.webapi.TestRun;
import org.testunited.webapi.TestSession;
import org.testunited.webapi.services.TestCaseService;
import org.testunited.webapi.services.TestGroupService;
import org.testunited.webapi.services.TestRunService;
import org.testunited.webapi.services.TestSessionService;
import org.testunited.webapi.services.TestTargetService;

@RestController
@CrossOrigin
public class TestResultSubmissionController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	TestRunService testRunService;
	
	@Autowired
	TestTargetService testTargetService;
	
	@Autowired
	TestCaseService testCaseService;
	
	@Autowired
	TestSessionService testSessionService;
	
	@Autowired
	TestGroupService testGroupService;

//	@PostMapping("/testresults")
//	@ResponseStatus(HttpStatus.CREATED)
//	public TestResult save(@Valid @RequestBody TestResult testResult) {
//
//		if (logger.isInfoEnabled()) {
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.enable(SerializationFeature.INDENT_OUTPUT);
//			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//			// StdDateFormat is ISO8601 since jackson 2.9
//			mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
//			try {
//				String results = mapper.writeValueAsString(testResult);
//				logger.info(results);
//			} catch (JsonProcessingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		this.saveTestResult(testResult);
//		
//		return testResult;
//	}
//
//	@PostMapping("/testresults/bulk")
//	@ResponseStatus(HttpStatus.CREATED)
//	public List<TestResult> save(@RequestBody List<TestResult> testResults) {
//
//		if (logger.isInfoEnabled()) {
//			ObjectMapper mapper = new ObjectMapper();
//			mapper.enable(SerializationFeature.INDENT_OUTPUT);
//			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//			// StdDateFormat is ISO8601 since jackson 2.9
//			mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
//			try {
//				String results = mapper.writeValueAsString(testResults);
//				logger.info(results);
//			} catch (JsonProcessingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		for (TestResult r : testResults)
//			this.saveTestResult(r);
//		
//		return testResults;
//	}
	
	@PostMapping("/testresultsubmissions")
	@ResponseStatus(HttpStatus.CREATED)
	public TestResultSubmissionSummary save(@RequestBody TestResultSubmission submission) {

		if (logger.isInfoEnabled()) {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			// StdDateFormat is ISO8601 since jackson 2.9
			mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
			try {
				String results = mapper.writeValueAsString(submission);
				logger.info(results);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		TestSession testSession = this.testSessionService.get(submission.getApplication().getId(), submission.getEnvironmentName(), submission.getSessionName());
		
		if(testSession == null) {
			testSession = new TestSession(submission.getSessionName(), submission.getApplication().getId(), submission.getEnvironmentName());
			this.testSessionService.save(testSession);
		}
		
		for (TestResult r : submission.getTestResults())
			this.saveTestResult(r, testSession);
		
		return new TestResultSubmissionSummary(testSession.getId(), submission.getTestResults().size());
	}
	
	private void saveTestResult(TestResult testResult, TestSession testSession) {
		
		TestCase testCase = testCaseService.getByTestSourceId(testResult.getTestSourceId());
		
		if (testCase == null) {
			testCase = new TestCase(testResult.getTestSourceId(), testResult.getTestSourceId(), null, testSession.getApplication().getId());
			this.testCaseService.save(testCase);
		}
		
		TestRun testRun = new TestRun(testCase, testResult.getTimeStamp(), testResult.getResult(),
				testResult.getReason(), testSession);

		this.testRunService.save(testRun);
	}
	
	class TestResultSubmissionSummary{
		
		private UUID testSessionId;
		private int testCaseCount;
		
		TestResultSubmissionSummary(UUID testSessionId, int testCaseCount){
			this.testSessionId = testSessionId;
			this.testCaseCount = testCaseCount;
		}

		public UUID getTestSessionId() {
			return testSessionId;
		}

		public void setTestSessionId(UUID testSessionId) {
			this.testSessionId = testSessionId;
		}

		public int getTestCaseCount() {
			return testCaseCount;
		}

		public void setTestCaseCount(int testCaseCount) {
			this.testCaseCount = testCaseCount;
		}
		
		
	}
}
