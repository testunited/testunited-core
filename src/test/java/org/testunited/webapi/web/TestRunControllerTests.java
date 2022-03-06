package org.testunited.webapi.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testunited.webapi.TestCase;
import org.testunited.webapi.TestGroup;
import org.testunited.webapi.TestRun;
import org.testunited.webapi.TestSession;
import org.testunited.webapi.services.TestRunService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
public class TestRunControllerTests {

	private MockMvc mockMvc;

	private final String jsonSingleTestRunGood = 
			"{\n" + 
			"    \"id\": \"672124b6-9894-11e5-be38-001d42e813fe\",\n" + 
			"    \"name\": \"my_test_group_1\",\n"+ 
			"    \"testCase\":{\"id\": \"672124b6-9894-11e5-be38-001d42e813fe\"},\n" + 
			"    \"testSession\":{\"id\": \"672124b6-9894-11e5-be38-001d42e813fe\"},\n" + 
			"    \"timeStamp\": \"2019-08-01\"\n"+ 
			"}";

	private final String jsonSingleTestGroupBad = 
			"{\n" + 
			"    \"id\": \"672124b6-9894-11e5-be38-001d42e813fe\",\n" + 
			"    \"name\"asdfaf \"my_test_group_2\"\n"+ 
			"}";

	private final TestSession testSession1 = new TestSession(UUID.fromString("3d50454f-0ba1-455b-8311-c7195c92e295"), "mysession", UUID.fromString("672124b6-9894-11e5-be38-001d42e813f1"), "prod");
	private final TestRun testRun1 = new TestRun(new TestCase(UUID.fromString("3d50454f-0ba1-455b-8311-c7195c92e291")), new Date(), true, null, testSession1);
	private final TestRun testRun2 = new TestRun(new TestCase(UUID.fromString("3d50454f-0ba1-455b-8311-c7195c92e292")), new Date(), true, null, testSession1);
	private final TestRun testRun3 = new TestRun(new TestCase(UUID.fromString("3d50454f-0ba1-455b-8311-c7195c92e293")), new Date(), false, null, testSession1);
	private final TestGroup testGroup1 = new TestGroup(UUID.fromString("672124b6-9894-11e5-be38-001d42e813fe"), "my_test_group_2", UUID.fromString("672124b6-9894-11e5-be38-001d42e813f1"));

	ArrayList<TestRun> testRuns_all = new ArrayList<TestRun>();
	ArrayList<TestRun> testRuns_passed = new ArrayList<TestRun>();
	ArrayList<TestRun> testRuns_failed = new ArrayList<TestRun>();
	private final ArrayList<TestGroup> testTargetList = new ArrayList<TestGroup>();

	@InjectMocks
	private TestRunController controller;

	@Mock
	private TestRunService serviceMock;

	@BeforeAll
	public void setUp() throws Exception {
		mockMvc = standaloneSetup(controller).build();
		ObjectMapper testRunObjectMapper = new ObjectMapper();
		String testRunJson = testRunObjectMapper.writeValueAsString(this.testRun1);
		System.out.println(testRunJson);
		
		this.testRuns_all.add(testRun1);
		this.testRuns_all.add(testRun2);
		this.testRuns_all.add(testRun3);
		
		this.testRuns_passed.add(testRun1);
		this.testRuns_passed.add(testRun2);
		
		this.testRuns_failed.add(testRun3);
	}

//	@Test
//	public void testGetById() throws Exception {
//		when(serviceMock.getById(1)).thenReturn(this.testTarget1);
//
//		this.mockMvc.perform(get("/testgroups/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andExpect(jsonPath("$.id", Matchers.is(1)))
//				.andExpect(jsonPath("$.name", Matchers.is("my_test_group_1")));
//	}
//	
//	@Test
//	public void testGetById_nonExistantId() throws Exception {
//		when(serviceMock.getById(1)).thenReturn(null);
//
//		this.mockMvc.perform(get("/testgroups/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
//	}
//	
//	@Test
//	public void testGetAll() throws Exception {
//
//		this.testTargetList.add(this.testTarget1);
//		this.testTargetList.add(this.testTarget2);
//
//		when(serviceMock.getAll()).thenReturn(this.testTargetList);
//
//		this.mockMvc.perform(get("/testgroups").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//				.andExpect(jsonPath("$.*", Matchers.hasSize(2)));
//	}
//
	@Test
	public void testAdd() throws Exception {
		this.mockMvc
				.perform(post("/testruns").content(this.jsonSingleTestRunGood).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
//	
//	@Test
//	public void testUpdate() throws Exception {
//			
//		this.mockMvc
//				.perform(put("/testgroups").content(this.jsonSingleTestGroupGood).contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isCreated());
//	}

	@Test
	public void testGetByTestSessionIdAndResult_all() throws Exception {
		when(this.serviceMock.getByTestSessionId(this.testSession1.getId())).thenReturn(this.testRuns_all);
		this.mockMvc
				.perform(get(String.format("/testsessions/%s/testruns", testSession1.getId())).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.*", Matchers.hasSize(3)));
	}
	
	@Test
	public void testGetByTestSessionIdAndResult_passed() throws Exception {
		when(this.serviceMock.getByTestSessionIdAndResult(this.testSession1.getId(), true)).thenReturn(this.testRuns_passed);
		this.mockMvc
				.perform(get(String.format("/testsessions/%s/testruns?result=passed", testSession1.getId())).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.*", Matchers.hasSize(2)));
	}
	
	@Test
	public void testGetByTestSessionIdAndResult_failed() throws Exception {
		when(this.serviceMock.getByTestSessionIdAndResult(this.testSession1.getId(), false)).thenReturn(this.testRuns_failed);
		this.mockMvc
				.perform(get(String.format("/testsessions/%s/testruns?result=failed", testSession1.getId())).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.*", Matchers.hasSize(1)));
	}	

//
//	@Test
//	public void testAddBadRequestNotJson() throws Exception {
//		this.mockMvc.perform(post("/booktitles").content("NotJason").contentType(MediaType.APPLICATION_JSON))
//				.andExpect(status().isBadRequest());
//	}

}
