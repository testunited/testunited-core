package org.testunited.core;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name","application_id"})})
public class Component {
	
	@Id
	@GeneratedValue
	@org.hibernate.annotations.Type(type="uuid-char")
	private UUID id;
	
	@NotNull
	private String name;
	private String description;
	
	@ManyToOne//(cascade=CascadeType.ALL)
	@NotNull
	private Application application;
	
	public Component() {
	}

	public Component(UUID id) {
		super();
		this.id = id;
	}
	public Component(String name, UUID applicationId) {
		super();
		this.name = name;
		this.application = new Application(applicationId);
	}

	public Component(String name, String description, UUID applicationId) {
		super();
		this.name = name;
		this.description = description;
		this.application = new Application(applicationId);
	}
	
	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public UUID getId() {
		return id;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(UUID uuid) {
		this.id = uuid;
	}
}
