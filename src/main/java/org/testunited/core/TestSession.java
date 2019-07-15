package org.testunited.core;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name"})})
public class TestSession {
	@Id
	@GeneratedValue
	@org.hibernate.annotations.Type(type="uuid-char")
	private UUID id;
	
	@NotNull
	private String name;
	
	@Transient
	private boolean result;
	
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
	public TestSession(String name) {
		super();
		this.name = name;
	}
	public TestSession() {
		super();
	}
}
