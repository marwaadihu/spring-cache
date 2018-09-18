package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import anil.agrawal.spring.cache.SpringCacheApplication;
import anil.agrawal.spring.cache.entity.Employee;
import anil.agrawal.spring.cache.service.EmployeeService;

/**
 * @author anil.agrawal
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCacheApplication.class)
@TestPropertySource("classpath:application-test.properties")
public class SpringCacheApplicationServiceTests {

	@Autowired
	private EmployeeService employeeService;

	@Test
	public void addEmployeeCacheTest() {
		List<Employee> listEmployee = employeeService.listEmployee();

		Employee employee = new Employee();
		employee.setName("testCache");
		Employee addEmployee = employeeService.addEmployee(employee);

		List<Employee> differentEmployee = employeeService.listEmployee();

		// check that list of employee is different than previous
		assertNotEquals(listEmployee, differentEmployee);

		// check that new employee is added to cache
		assertEquals(addEmployee, employeeService.getEmployee(addEmployee.getId()));
	}

	@Test
	public void getEmployeeCacheTest() {
		Employee employee = employeeService.getEmployee(10l);
		Employee sameEmployee = employeeService.getEmployee(10l);
		assertEquals(employee, sameEmployee);
	}

	@Test
	public void updateEmployeeCacheTest() {
		List<Employee> listEmployee = employeeService.listEmployee();

		Employee employee = new Employee();
		employee.setName("panipuri");
		Employee updateEmployee = employeeService.updateEmployee(employee, listEmployee.get(0));

		List<Employee> differentEmployee = employeeService.listEmployee();

		// check that list of employee is different than previous
		assertNotEquals(listEmployee, differentEmployee);

		// check that employee is updated to cache
		assertEquals(updateEmployee, employeeService.getEmployee(updateEmployee.getId()));
	}

	@Test
	public void listEmployeeCacheTest() {
		List<Employee> listEmployee = employeeService.listEmployee();
		List<Employee> sameListEmployee = employeeService.listEmployee();
		assertEquals(listEmployee, sameListEmployee);
	}

}
