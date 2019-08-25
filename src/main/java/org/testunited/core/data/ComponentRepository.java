package org.testunited.core.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.testunited.core.*;

public interface ComponentRepository extends CrudRepository<Component, UUID> {

	public List<Component> findByApplicationId(UUID applicationId);
}
