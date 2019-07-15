package org.testunited.core.web;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.testunited.core.TestSession;
import org.testunited.core.TestTarget;
import org.testunited.core.services.TestSessionService;
import org.testunited.core.services.TestTargetService;

@RestController
@RequestMapping("/testsessions")
public class TestSessionController {

	@Autowired
	TestSessionService testSessionService;

	
	@GetMapping
	public List<TestSession> getAll(){
		return this.testSessionService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TestSession> getById(@PathVariable UUID id){
		var target = this.testSessionService.getById(id);
		if (target == null)
			return new ResponseEntity<TestSession>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<TestSession>(target, HttpStatus.OK);
	}
	
	@RequestMapping(method = {RequestMethod.POST, RequestMethod.PUT})
	@ResponseStatus(HttpStatus.CREATED)
	public TestSession save(@Valid @RequestBody TestSession testSession) {
		this.testSessionService.save(testSession);
		return testSession;
	}
	
}
