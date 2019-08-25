package org.testunited.core;

import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class TestTarget {

	@Id
	@GeneratedValue
	@org.hibernate.annotations.Type(type = "uuid-char")
	private UUID id;

	@ManyToOne
	@NotNull
	private Component component;

	@NotNull
	private String function;

	public TestTarget(UUID id, Component component, String function) {
		super();
		this.id = id;
		this.component = component;
		this.function = function;
	}

	public TestTarget(Component component, String function) {
		super();
		this.component = component;
		this.function = function;
	}

	public TestTarget(UUID id) {
		this.id = id;
	}

	public TestTarget() {
	}

	public UUID getId() {
		return id;
	}

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	public void setId(UUID id) {
		this.id = id;
	}

}
