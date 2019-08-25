package org.testunited.core.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.testunited.core.TestSession;
import org.testunited.core.TestTarget;

public interface TestTargetRepository extends CrudRepository<TestTarget, UUID> {
	public List<TestTarget> findByComponentId(UUID componentId);

}
