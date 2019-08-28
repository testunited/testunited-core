package org.testunited.core.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testunited.core.Component;
import org.testunited.core.data.ComponentRepository;

@Service
public class ComponentService {

	@Autowired
	private ComponentRepository componentRepo;
	
	public void save(Component comp) {
		if(comp.getId() == null)
			comp.setId(UUID.randomUUID());		
		this.componentRepo.save(comp);
	}
	
	public List<Component> getByApplicationId(UUID id) {
		return componentRepo.findByApplicationId(id);
	}

	public Component getById(UUID id) {
		return this.componentRepo.findById(id).get();
	}
	
	public List<Component> getAll(){
		return (List<Component>)this.componentRepo.findAll();
	}
	
}
