package org.testunited.webapi.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testunited.webapi.Application;
import org.testunited.webapi.data.ApplicationRepository;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository appRepo;
	
	public void save(Application app) {
		if(app.getId() == null)
			app.setId(UUID.randomUUID());
		this.appRepo.save(app);
	}
	
	public Application getByName(String name) {
		return appRepo.findByName(name);
	}

	public Application getById(UUID id) {
		return this.appRepo.findById(id).get();
	}
	
	public List<Application> getAll(){
		return (List<Application>)this.appRepo.findAll();
	}
	
}
