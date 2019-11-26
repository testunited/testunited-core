package org.testunited.webapi.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.testunited.webapi.TestTarget;

public interface TestTargetRepository extends CrudRepository<TestTarget, UUID> {
	public List<TestTarget> findByComponentId(UUID componentId);

}
