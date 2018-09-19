package anil.agrawal.spring.cache;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileReader;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import anil.agrawal.spring.cache.entity.Employee;
import anil.agrawal.spring.cache.service.EmployeeService;
import net.minidev.json.parser.JSONParser;

/**
 * @author anil.agrawal
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCacheApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class EmployeeControllerTests {

	private MockMvc mockMvc;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private ResourceLoader resourceLoader;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void addEmployeeSuccess() throws Exception {
		String requestBody = readJsonFromFile("/employee/addEmployee200.json").toString();
		mockMvc.perform(
				MockMvcRequestBuilders.post("/employee").contentType(MediaType.APPLICATION_JSON).content(requestBody))
				.andExpect(status().isOk()).andDo(print()).andReturn();
	}

	@Test
	public void updateEmployeeSuccess() throws Exception {
		Employee addEmployee = addEmployee();
		String requestBody = readJsonFromFile("/employee/updateEmployee200.json").toString();
		mockMvc.perform(MockMvcRequestBuilders.put("/employee/" + addEmployee.getId())
				.contentType(MediaType.APPLICATION_JSON).content(requestBody)).andExpect(status().isOk()).andDo(print())
				.andReturn();
	}

	@Test
	public void getEmployeeSuccess() throws Exception {
		addEmployee();
		mockMvc.perform(MockMvcRequestBuilders.get("/employee/1")).andExpect(status().isOk()).andDo(print())
				.andReturn();
	}

	@Test
	public void deleteEmployeeSuccess() throws Exception {
		Employee addEmployee = addEmployee();
		mockMvc.perform(MockMvcRequestBuilders.delete("/employee/" + addEmployee.getId())).andExpect(status().isOk())
				.andDo(print()).andReturn();
	}

	@Test
	public void listEmployeeSuccess() throws Exception {
		for (int i = 0; i < 2; i++) {
			addEmployee();
		}
		mockMvc.perform(MockMvcRequestBuilders.get("/employee")).andExpect(status().isOk()).andDo(print()).andReturn();
	}

	/**
	 * This method used to addEmployee
	 * 
	 * @return
	 */
	private Employee addEmployee() {
		Employee employee = new Employee();
		employee.setName("test");
		Employee addEmployee = employeeService.addEmployee(employee);
		return addEmployee;
	}

	/**
	 * This method used to read JSON object from file
	 * 
	 * @param fileName
	 * @return
	 */
	private JSONObject readJsonFromFile(String fileName) {
		JSONParser parser = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
		JSONObject jsonObject = null;
		try {
			Resource resource = resourceLoader.getResource("classpath:" + fileName);
			File file = resource.getFile();
			Object obj = parser.parse(new FileReader(file));
			jsonObject = new JSONObject(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

}
