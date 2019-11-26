package org.testunited.webapi;

import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class TestGroup {
	@Id
//	@GeneratedValue
	@org.hibernate.annotations.Type(type="uuid-char")
	private UUID id;

	@ManyToOne
	@NotNull
	private Application application;

	@NotNull
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
