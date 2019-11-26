package org.testunited.webapi;

import java.util.UUID;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name", "component_id"})})
public class TestTarget {

	@Id
//	@GeneratedValue
	@org.hibernate.annotations.Type(type = "uuid-char")
	private UUID id;

	@ManyToOne
	@NotNull
	private Component component;

	@NotNull
	private String name;

	public TestTarget(UUID id, Component component, String name) {
		super();
		this.id = id;
		this.component = component;
		this.name = name;
	}

	public TestTarget(Component component, String name) {
		super();
		this.component = component;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(UUID id) {
		this.id = id;
	}

}
