package org.testunited.webapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testunited.webapi.TestCase;
import org.testunited.webapi.data.TestCaseRepository;

@Service
public class TestCaseService {

	@Autowired
	private TestCaseRepository testCaseRepo;
	
	public void save(TestCase testCase) {
		if(testCase.getId() == null)
			testCase.setId(UUID.randomUUID());		

		this.testCaseRepo.save(testCase);
	}
	
	public List<TestCase> getByApplicationId(UUID id) {
		return testCaseRepo.findByApplicationId(id);
	}
	
	public List<TestCase> getByTestTargetId(UUID id) {
		return testCaseRepo.findByTestTargetId(id);
	}
	public List<TestCase> getByTestTargetIdTestGroupId(UUID testTargetId, UUID testGroupId) {
		return testCaseRepo.findByTestTargetIdAndTestGroupId(testTargetId, testGroupId);
	}
	public TestCase getById(UUID id) {
		return this.testCaseRepo.findById(id).get();
	}
	
	public TestCase getByTestSourceId(String sourceId) {
		return testCaseRepo.findByTestSourceId(sourceId);
	}
	
	public List<TestCase> getAll(){
		return (List<TestCase>)this.testCaseRepo.findAll();
	}
	
}
