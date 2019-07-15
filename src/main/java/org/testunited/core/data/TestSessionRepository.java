package org.testunited.core.data;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.testunited.core.*;

public interface TestSessionRepository extends CrudRepository<TestSession, UUID> {

	public TestSession findByName(String name);

}
