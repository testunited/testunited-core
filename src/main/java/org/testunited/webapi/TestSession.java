package org.testunited.webapi;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name", "application_id", "environment"})})
public class TestSession {
	@Id
//	@GeneratedValue
	@org.hibernate.annotations.Type(type="uuid-char")
	private UUID id;
	
	@ManyToOne
	@NotNull
	private Application application;
	
	@NotNull
	private String environment;
	
	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	@NotNull
	private String name;
	
	@Transient
	private boolean result;
	
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	public boolean getResult() {
		return this.result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	} 
	public TestSession(String name, UUID applicationId, String environment) {
		super();
		this.name = name;
		this.application = new Application(applicationId);
		this.environment = environment;
	}
	public TestSession(UUID id, @NotNull String name, UUID applicationId, String environment) {
		super();
		this.id = id;
		this.name = name;
		this.application = new Application(applicationId);
		this.environment = environment;
	}
	public TestSession() {
		super();
	}
}
