package org.testunited.webapi.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testunited.webapi.TestCase;
import org.testunited.webapi.TestGroup;
import org.testunited.webapi.data.TestGroupRepository;

@Service
public class TestGroupService {

	@Autowired
	private TestGroupRepository testGroupRepo;
	
	public void save(TestGroup testGroup) {
		if(testGroup.getId() == null)
			testGroup.setId(UUID.randomUUID());		
		
		this.testGroupRepo.save(testGroup);
	}
	
	public List<TestGroup> getAll() {
		return (List<TestGroup>) testGroupRepo.findAll();
	}
	
	public TestGroup getById(UUID id) {
		return this.testGroupRepo.findById(id).get();
	}
	public List<TestGroup> getByApplicationId(UUID id) {
		return testGroupRepo.findByApplicationId(id);
	}
}
