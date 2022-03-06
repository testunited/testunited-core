package org.testunited.webapi.web;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.testunited.webapi.Component;
import org.testunited.webapi.services.ComponentService;

@RestController
@CrossOrigin
public class ComponentController {

	@Autowired
	ComponentService componentService;
	
	@GetMapping("/applications/{applicationId}/components")
	public List<Component> getByApplicationId(@PathVariable UUID applicationId){
		return this.componentService.getByApplicationId(applicationId);
	}
	
	@GetMapping("/components/{id}")
	public ResponseEntity<Component> getById(@PathVariable UUID id){
		var target = this.componentService.getById(id);
		if (target == null)
			return new ResponseEntity<Component>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Component>(target, HttpStatus.OK);
	}

	@GetMapping("/components")
	public List<Component> getAll(){
		return this.componentService.getAll();
	}
	
	@PostMapping("/components")
	@ResponseStatus(HttpStatus.CREATED)
	public Component save(@Valid @RequestBody Component comp) {
		this.componentService.save(comp);
		return comp;
	}
	
	@PostMapping("/components/bulk")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Component> save(@RequestBody List<Component> comps) {
		for(Component comp: comps)
			this.componentService.save(comp);
		return comps;
	}
}
