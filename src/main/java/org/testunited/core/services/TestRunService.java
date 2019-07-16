package org.testunited.core.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testunited.core.TestRun;
import org.testunited.core.data.TestRunRepository;

@Service
public class TestRunService {

	@Autowired
	private TestRunRepository testRunRepo;
	
	public void save(TestRun testGroup) {
		this.testRunRepo.save(testGroup);
	}
	
	public void save(List<TestRun> testRuns) {
		this.testRunRepo.saveAll(testRuns);
	}
	
	public List<TestRun> getByTestCaseId(UUID testCaseId) {
		return testRunRepo.findByTestCaseId(testCaseId);
	}
	
	public List<TestRun> getByTestSessionId(UUID testSessionId) {
		return testRunRepo.findByTestSessionId(testSessionId);
	}	
	
	public List<TestRun> getByTestCaseIdAndTestSessionId(UUID testCaseId, UUID testSessionId) {
		return testRunRepo.findByTestCaseIdAndTestSessionId(testCaseId, testSessionId);
	}

	public List<TestRun> getByTestSessionIdAndResult(UUID testSessionId, boolean result) {
		return testRunRepo.findByTestSessionIdAndResult(testSessionId, result);
	}
	
	public TestRun getById(UUID id) {
		return this.testRunRepo.findById(id).get();
	}
	
	public List<TestRun> getAll(){
		return (List<TestRun>)this.testRunRepo.findAll();
	}
}
