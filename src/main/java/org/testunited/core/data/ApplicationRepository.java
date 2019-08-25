package org.testunited.core.data;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.testunited.core.*;

public interface ApplicationRepository extends CrudRepository<Application, UUID> {

	public Application findByName(String name);

}
