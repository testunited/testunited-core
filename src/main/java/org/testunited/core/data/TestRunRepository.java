package org.testunited.core.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.testunited.core.*;

public interface TestRunRepository extends CrudRepository<TestRun, UUID> {

	public List<TestRun> findByTestCaseId(UUID testCaseId);
	public List<TestRun> findByTestSessionId(UUID testSessionId);
	public List<TestRun> findByTestCaseIdAndTestSessionId(UUID testCaseId, UUID testSessionId);

}
