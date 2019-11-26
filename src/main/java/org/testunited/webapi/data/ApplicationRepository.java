package org.testunited.webapi.data;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.testunited.webapi.*;

public interface ApplicationRepository extends CrudRepository<Application, UUID> {

	public Application findByName(String name);

}
