package org.testunited.webapi.data;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.testunited.webapi.*;

public interface TestGroupRepository extends CrudRepository<TestGroup, UUID> {
	public List<TestGroup> findByApplicationId(UUID applicationId);

}
