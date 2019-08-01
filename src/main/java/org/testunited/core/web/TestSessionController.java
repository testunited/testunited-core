package org.testunited.core.web;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.testunited.core.TestSession;
import org.testunited.core.services.TestSessionService;

@RestController
public class TestSessionController {

	@Autowired
	TestSessionService testSessionService;

	@GetMapping("/testsessions")
	public List<TestSession> getAll(){
		return this.testSessionService.getAll();
	}
	
	@GetMapping("/testsessions/{id}")
	public ResponseEntity<TestSession> getById(@PathVariable UUID id){
		var session = this.testSessionService.getById(id);
		
		if (session == null)
			return new ResponseEntity<TestSession>(HttpStatus.NOT_FOUND);
		
		session.setResult(this.testSessionService.getOverallResult(id));
		
		return new ResponseEntity<TestSession>(session, HttpStatus.OK);
	}
	
	@GetMapping("/testsessions/{id}/result")
	public ResponseEntity<Boolean> getResultById(@PathVariable UUID id){		
		var session = this.testSessionService.getById(id);
		
		if (session == null)
			return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
		
		session.setResult(this.testSessionService.getOverallResult(id));
		
		return new ResponseEntity<Boolean>(session.getResult(), HttpStatus.OK);	
	}
	
	@RequestMapping(path = "/testsessions", method = {RequestMethod.POST, RequestMethod.PUT})
	@ResponseStatus(HttpStatus.CREATED)
	public TestSession save(@Valid @RequestBody TestSession testSession) {
		this.testSessionService.save(testSession);
		return testSession;
	}
	
}
