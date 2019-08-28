package org.testunited.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testunited.core.TestCase;
import org.testunited.core.TestGroup;
import org.testunited.core.data.TestGroupRepository;

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
