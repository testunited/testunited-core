package org.testunited.core.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.testunited.core.*;

public interface TestSessionRepository extends CrudRepository<TestSession, UUID> {
	public List<TestSession> findByApplicationId(UUID applicationId);
	public TestSession findByApplicationIdAndEnvironmentAndName(UUID applicationId, String environment, String name);

}
