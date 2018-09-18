package anil.agrawal.spring.cache.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import anil.agrawal.spring.cache.entity.Employee;
import anil.agrawal.spring.cache.exception.NoContentException;
import anil.agrawal.spring.cache.service.EmployeeService;

/**
 * @author anil.agrawal
 *
 */
@RestController
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	private EmployeeService employeeService;

	/**
	 * Constructor
	 * 
	 * @param employeeService
	 */
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * API used to add Employee
	 * 
	 * @param requestBody
	 * @return
	 */
	@PostMapping("employee")
	public Employee addEmployee(@Valid @RequestBody Employee requestBody) {
		LOGGER.info("addEmployee method called");
		return employeeService.addEmployee(requestBody);
	}

	/**
	 * API used to update Employee
	 * 
	 * @param employeeId
	 * @param requestBody
	 * @return
	 */
	@PutMapping("employee/{employeeId}")
	public Employee updateEmployee(@PathVariable("employeeId") Long employeeId,
			@Valid @RequestBody Employee requestBody) {
		LOGGER.info("updateEmployee method called");
		Employee employee = employeeService.getEmployee(employeeId);
		if (employee == null) {
			throw new NoContentException();
		}
		return employeeService.updateEmployee(requestBody, employee);

	}

	/**
	 * API used to get Employee
	 * 
	 * @param employeeId
	 * @return
	 */
	@GetMapping("employee/{employeeId}")
	public Employee getEmployee(@PathVariable("employeeId") Long employeeId) {
		LOGGER.info("getEmployee method called");
		Employee employee = employeeService.getEmployee(employeeId);
		if (employee == null) {
			throw new NoContentException();
		}
		return employee;

	}

	/**
	 * API used to delete Employee
	 * 
	 * @param employeeId
	 * @return
	 */
	@DeleteMapping("employee/{employeeId}")
	public void deleteEmployee(@PathVariable("employeeId") Long employeeId) {
		LOGGER.info("deleteEmployee method called");
		Employee employee = employeeService.getEmployee(employeeId);
		if (employee == null) {
			throw new NoContentException();
		}
		employeeService.deleteEmployee(employee);

	}

	/**
	 * API used to list Employees
	 * 
	 * @return
	 */
	@GetMapping("employee")
	public List<Employee> listEmployee() {
		LOGGER.info("listEmployee method called");
		List<Employee> employees = employeeService.listEmployee();
		if (employees == null || employees.isEmpty()) {
			throw new NoContentException();
		}

		return employees;
	}

}
