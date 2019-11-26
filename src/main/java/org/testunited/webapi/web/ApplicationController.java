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
import org.testunited.webapi.Application;
import org.testunited.webapi.services.ApplicationService;

@RestController
@CrossOrigin
public class ApplicationController {

	@Autowired
	ApplicationService appService;
	
	@GetMapping("/applications/{id}")
	public ResponseEntity<Application> getById(@PathVariable UUID id){
		var target = this.appService.getById(id);
		if (target == null)
			return new ResponseEntity<Application>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Application>(target, HttpStatus.OK);
	}

	@GetMapping("/applications")
	public List<Application> getAll(){
		return this.appService.getAll();
	}
	
	@PostMapping("/applications")
	@ResponseStatus(HttpStatus.CREATED)
	public Application save(@Valid @RequestBody Application app) {
		this.appService.save(app);
		return app;
	}
	
	@PostMapping("/applications/bulk")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Application> save(@RequestBody List<Application> apps) {
		for(Application app: apps)
			this.appService.save(app);
		return apps;
	}
}
