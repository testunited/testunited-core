package org.testunited.core;

import java.util.UUID;

import javax.persistence.*;

@Entity
public class TestGroup {
	@Id
	@GeneratedValue
	@org.hibernate.annotations.Type(type="uuid-char")
	private UUID id;

	@ManyToOne
	private Application application;

	private String name;

	public TestGroup(UUID id, String name, UUID applicationId) {
		super();
		this.id = id;
		this.name = name;
		this.application = new Application(applicationId);
	}

	public TestGroup(String name, UUID applicationId ) {
		super();
		this.name = name;
		this.application = new Application(applicationId);
	}

	public TestGroup() {
	}
	public TestGroup(UUID id) {
		this.id = id;
	}
	public UUID getId() {
		return id;
	}
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
	public String getName() {
		return name;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
