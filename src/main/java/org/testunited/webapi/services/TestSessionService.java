package org.testunited.webapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testunited.webapi.TestCase;
import org.testunited.webapi.TestGroup;
import org.testunited.webapi.TestSession;
import org.testunited.webapi.data.TestCaseRepository;
import org.testunited.webapi.data.TestSessionRepository;

@Service
public class TestSessionService {

	@Autowired
	private TestSessionRepository testSessionRepo;
	@Autowired
	TestRunService testRunService;
	
	public void save(TestSession testSession) {
		if(testSession.getId() == null)
			testSession.setId(UUID.randomUUID());		
		this.testSessionRepo.save(testSession);
	}
	
	public List<TestSession> getByApplicationId(UUID id) {
		return testSessionRepo.findByApplicationId(id);
	}
	
	public TestSession getById(UUID id) {
		return this.testSessionRepo.findById(id).get();
	}
	
	public TestSession get(UUID applicationId, String environment, String name) {
		return testSessionRepo.findByApplicationIdAndEnvironmentAndName(applicationId, environment, name);
	}
	
	public List<TestSession> getAll(){
		return (List<TestSession>)this.testSessionRepo.findAll();
	}
	
	public boolean getOverallResult(UUID id) {
		var testRuns = this.testRunService.getByTestSessionId(id);
		
		for(var testRun: testRuns) {
			if(!testRun.getResult()) {
				return false;
			}
		}
		
		return true;
	}
}
