package org.testunited.core.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testunited.core.TestTarget;
import org.testunited.core.data.TestTargetRepository;

@Service
public class TestTargetService {

	@Autowired
	private TestTargetRepository testTargetRepo;
	
	public void save(TestTarget testTarget) {
		if(testTarget.getId() == null)
			testTarget.setId(UUID.randomUUID());	
		this.testTargetRepo.save(testTarget);
	}
	
	public List<TestTarget> getAll() {
		return (ArrayList<TestTarget>) testTargetRepo.findAll();
	}
	
	public TestTarget getById(UUID id) {
		return this.testTargetRepo.findById(id).get();
	}
	
	public List<TestTarget> getByComponentId(UUID id) {
		return this.testTargetRepo.findByComponentId(id);
	}
	
}
